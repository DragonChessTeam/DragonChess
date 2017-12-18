package ru.nsu.fit.g14203.net.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.util.*;

//  TODO: (1): Add incorrect message format handling.

public class MessageChannelImpl implements MessageChannel {

    private static final Logger LOG = Logger.getLogger(MessageChannel.class);

    private final Set<MessageReceiver> receivers = new HashSet<>();
    private static final Message DISCONNECT = new DisconnectMessage();

    private final ObjectMapper mapper = new ObjectMapper();

    private final SelectionKey key;

    private final Queue<ByteBuffer> sendQueue = new ArrayDeque<>();
    private final ByteBuffer sendLengthBuffer = ByteBuffer.allocate(Integer.BYTES);
    private ByteBuffer sendBuffer;

    private final ByteBuffer receiveLengthBuffer = ByteBuffer.allocate(Integer.BYTES);
    private ByteBuffer receiveBuffer;

    public MessageChannelImpl(SelectionKey key) {
        this.key = key;
    }

    @Override
    public boolean addReceiver(MessageReceiver messageReceiver) {
        final boolean add = receivers.add(messageReceiver);
        if (add) {
            LOG.info("add receiver : " + messageReceiver);
            int interestOpts = key.interestOps();
            key.interestOps(interestOpts | SelectionKey.OP_READ);
        }

        return add;
    }

    @Override
    public boolean removeReceiver(MessageReceiver messageReceiver) {
        final boolean remove = receivers.remove(messageReceiver);
        if (remove && receivers.isEmpty()) {
            LOG.info("remove receiver : " + messageReceiver);
            int interestOpts = key.interestOps();
            key.interestOps(interestOpts & ~SelectionKey.OP_READ);
        }

        return remove;
    }

    @Override
    public void send(@NotNull Message message) throws JsonProcessingException {
        final byte[] bytes = mapper.writeValueAsBytes(message);
        sendQueue.add(ByteBuffer.wrap(bytes));

        LOG.info("send message : " + message);

        int interestOpts = key.interestOps();
        key.interestOps(interestOpts | SelectionKey.OP_WRITE);
    }

    private void receive(Message message) {
        receivers.forEach(receiver -> receiver.receive(message));

        LOG.info("receive message : " + message);

        if (sendQueue.isEmpty()) {
            int interestOpts = key.interestOps();
            key.interestOps(interestOpts & ~SelectionKey.OP_WRITE);
        }
    }

    /**
     * Write message to connected ByteChannel.
     *
     * @throws IOException if IO error occurs
     */
    public void writeChannel() throws IOException {
        final ByteChannel channel = (ByteChannel) key.channel();
        if (sendBuffer == null) {
            sendBuffer = sendQueue.poll();
            if (sendBuffer == null)
                return;

            sendLengthBuffer.clear();
            sendLengthBuffer.putInt(sendBuffer.limit());
        }

        if (sendLengthBuffer.hasRemaining()) {
            channel.write(sendLengthBuffer);
            if (sendLengthBuffer.hasRemaining())
                return;
        }

        channel.write(sendBuffer);
        if (!sendBuffer.hasRemaining())
            sendBuffer = null;
    }

    /**
     * Read message from connected ByteChannel.
     *
     * @throws IOException if IO error occurs
     */
    public void readChannel() throws IOException {
        final ByteChannel channel = (ByteChannel) key.channel();
        if (receiveBuffer == null) {
            channel.read(receiveLengthBuffer);
            if (receiveLengthBuffer.hasRemaining())
                return;

            receiveLengthBuffer.flip();
            receiveBuffer = ByteBuffer.allocate(receiveLengthBuffer.getInt());
        }

        channel.read(receiveBuffer);
        if (receiveBuffer.hasRemaining())
            return;

        receive(readMessage());
    }

    @SuppressWarnings("unchecked")
    private Message readMessage() throws IOException {
        final Map<String, Object> tmp = mapper.readValue(receiveBuffer.array(), Map.class); //  TODO: (1)
        int type;
        try {
            type = (int) tmp.get("type");
        } catch (Exception e) {
            throw new IOException(e);   //  TODO: (1)
        }

        Class<? extends Message> messageClass;
        switch (type) {
            case Message.TYPE_CONNECT:
                messageClass = ConnectMessage.class;
                break;
            case Message.TYPE_ACCEPT:
                messageClass = AcceptMessage.class;
                break;
            case Message.TYPE_UPDATE:
                messageClass = UpdateMessage.class;
                break;
            case Message.TYPE_DISCONNECT:
                messageClass = DisconnectMessage.class;
                break;
            default:
                throw new IOException();    //  TODO: (1)
        }

        return mapper.readValue(receiveBuffer.array(), messageClass);   //  TODO: (1)
    }

    @Override
    public void close() throws IOException {
        receive(DISCONNECT);

        key.cancel();
        key.channel().close();
    }
}
