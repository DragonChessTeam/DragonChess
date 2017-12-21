package ru.nsu.fit.g14203.net.game;

import org.apache.log4j.Logger;
import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.net.channel.*;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.function.Consumer;

class ClientThread extends AbstractGameThread {

    private static final Logger LOG = Logger.getLogger(ClientThread.class);

    private final boolean connected;

    private final MessageReceiver acceptReceiver = this::accept;

    ClientThread(SocketAddress address,
                 Consumer<Color> connectHandler, Runnable disconnectHandler,
                 MessageReceiver receiver) throws IOException {
        super(connectHandler, disconnectHandler, receiver);

        final SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        connected = channel.connect(address);

        key = channel.register(selector, connected ? 0 : SelectionKey.OP_CONNECT);
        messageChannel = new MessageChannelImpl(key);
    }

    @Override
    void beforeLoop() throws IOException {
        if (connected)
            connect();
    }

    @Override
    void insideLoop() throws IOException {
        if (key.isConnectable())
            connect();
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
