package ru.nsu.fit.g14203.net.game;

import org.apache.log4j.Logger;
import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.net.channel.AcceptMessage;
import ru.nsu.fit.g14203.net.channel.MessageChannelImpl;
import ru.nsu.fit.g14203.net.channel.MessageReceiver;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.function.Consumer;

class ServerThread extends AbstractGameThread {

    private static final Logger LOG = Logger.getLogger(ServerThread.class);

    private final SelectionKey serverKey;
    private final ServerSocketChannel serverChannel;

    ServerThread(SocketAddress address,
                 Consumer<Color> connectHandler, Runnable disconnectHandler,
                 MessageReceiver receiver) throws IOException {
        super(connectHandler, disconnectHandler, receiver);

        serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.bind(address);

        serverKey = serverChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    @Override
    void beforeLoop() throws IOException {
        try {
            selector.select();
            serverKey.cancel();

            final SocketChannel channel = serverChannel.accept();
            channel.configureBlocking(false);

            key = channel.register(selector, 0);
            messageChannel = new MessageChannelImpl(key);
        } catch (IOException e) {
            messageChannel.close();
        }

        LOG.info("connect");

        messageChannel.send(new AcceptMessage(Color.WHITE));    //  TODO: get color(?)
        connectHandler.accept(Color.WHITE);
    }

    @Override
    void insideLoop() throws IOException {
    }
}