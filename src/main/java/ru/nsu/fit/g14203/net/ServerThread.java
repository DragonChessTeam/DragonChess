package ru.nsu.fit.g14203.net;

import org.apache.log4j.Logger;
import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.net.util.AcceptMessage;
import ru.nsu.fit.g14203.net.util.MessageChannel;
import ru.nsu.fit.g14203.net.util.MessageChannelImpl;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.function.Consumer;

class ServerThread extends GameThread {

    private static final Logger LOG = Logger.getLogger(ServerThread.class);

    private final ServerSocketChannel serverChannel;
    private final SelectionKey serverKey;

    private SelectionKey key;
    private MessageChannelImpl messageChannel;

    ServerThread(SocketAddress address,
                 Consumer<Color> connectHandler, Runnable disconnectHandler) throws IOException {
        super(connectHandler, disconnectHandler);

        serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.bind(address);

        serverKey = serverChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    @Override
    MessageChannel getMessageChannel() {
        return messageChannel;
    }

    @Override
    public void run() {
        try {
            accept();
            networkLoop();
        } catch (IOException e) {
            LOG.warn(e);
        } finally {
            LOG.info("disconnect");
            disconnectHandler.run();
        }
    }

    private void accept() throws IOException {
        try {
            selector.select();

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

    private void networkLoop() throws IOException {
        while (!interrupted()) {
            try {
                selector.select();

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
}
