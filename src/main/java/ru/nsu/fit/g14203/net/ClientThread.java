package ru.nsu.fit.g14203.net;

import org.apache.log4j.Logger;
import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.net.util.*;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.function.Consumer;

class ClientThread extends GameThread {

    private static final Logger LOG = Logger.getLogger(ClientThread.class);

    private final SelectionKey key;
    private final MessageChannelImpl messageChannel;

    private final boolean connected;

    private final MessageReceiver acceptReceiver = this::accept;

    ClientThread(SocketAddress address,
                 Consumer<Color> connectHandler, Runnable disconnectHandler) throws IOException {
        super(connectHandler, disconnectHandler);

        final SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        connected = channel.connect(address);

        key = channel.register(selector, connected ? 0 : SelectionKey.OP_CONNECT);
        messageChannel = new MessageChannelImpl(key);
    }

    @Override
    MessageChannel getMessageChannel() {
        return messageChannel;
    }

    @Override
    public void run() {
        if (connected)
            connect();

        try {
            networkLoop();
        } catch (IOException e) {
            LOG.warn(e);
        } finally {
            LOG.info("disconnect");
            disconnectHandler.run();
        }
    }

    private void networkLoop() throws IOException {
        while (!interrupted()) {
            try {
                selector.select();

                if (key.isConnectable())
                    connect();
                if (key.isWritable())
                    messageChannel.writeChannel();
                if (key.isReadable())
                    messageChannel.readChannel();

                selector.selectedKeys().remove(key);
            } catch (IOException e) {
                messageChannel.close();
            }
        }
    }

    private void connect() {
        LOG.info("connect");

        int interestOpts = key.interestOps();
        key.interestOps(interestOpts & ~SelectionKey.OP_CONNECT);

        messageChannel.send(new ConnectMessage());
        messageChannel.addReceiver(acceptReceiver);
    }

    private void accept(Message message) {
        if (!(message instanceof AcceptMessage))
            return;

        messageChannel.removeReceiver(acceptReceiver);

        connectHandler.accept(((AcceptMessage) message).getColor());
    }
}
