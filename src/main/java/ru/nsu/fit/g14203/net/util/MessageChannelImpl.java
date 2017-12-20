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
    public void send(@NotNull Message message) {
        final byte[] bytes;
        try {
            bytes = mapper.writeValueAsBytes(message);
        } catch (JsonProcessingException e) {
            throw new MessageFormatException();
        }
        sendQueue.add(ByteBuffer.wrap(bytes));

        LOG.info("send message : " + message);

        int interestOpts = key.interestOps();
        key.interestOps(interestOpts | SelectionKey.OP_WRITE);
    }

    private void receive(Message message) {
        receivers.forEach(receiver -> receiver.receive(message));

        LOG.info("receive message : " + message);
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
            sendLengthBuffer.flip();
        }

        if (sendLengthBuffer.hasRemaining()) {
            channel.write(sendLengthBuffer);
            if (sendLengthBuffer.hasRemaining())
                return;
        }

        channel.write(sendBuffer);
        if (sendBuffer.hasRemaining())
            return;

        sendBuffer = null;
        if (sendQueue.isEmpty()) {
            int interestOpts = key.interestOps();
            key.interestOps(interestOpts & ~SelectionKey.OP_WRITE);
        }
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
            receiveLengthBuffer.clear();
        }

        channel.read(receiveBuffer);
        if (receiveBuffer.hasRemaining())
            return;

        try {
            receive(readMessage());
        } catch (MessageFormatException e) {
            LOG.warn("invalid message format");
            throw e;
        }
    }

    @SuppressWarnings("unchecked")
    private Message readMessage() throws IOException {
        int type;
        try {
            final Map<String, Object> tmp = mapper.readValue(receiveBuffer.array(), Map.class);
            type = (int) tmp.get("type");
        } catch (Exception e) {
            throw new MessageFormatException(e);
        }

        Class<? extends Message> messageClass;
        switch (type) { //  FIXME: replace with special class
            case Message.TYPE_CONNECT:
                messageClass = ConnectMessage.class;
                break;
            case Message.TYPE_ACCEPT:
                messageClass = AcceptMessage.class;
                break;
            case Message.TYPE_DISCONNECT:
                messageClass = DisconnectMessage.class;
                break;
            case Message.TYPE_MOVE:
            case Message.TYPE_CAPTURE:
                messageClass = StepMessage.class;
                break;
            default:
                throw new MessageFormatException();
        }

        try {
            return mapper.readValue(receiveBuffer.array(), messageClass);
        } catch (Exception e) {
            throw new MessageFormatException(e);
        }
    }

    @Override
    public void close() throws IOException {
        receive(DISCONNECT);

        key.cancel();
        key.channel().close();
    }
}
