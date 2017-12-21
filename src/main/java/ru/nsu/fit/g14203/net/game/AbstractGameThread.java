package ru.nsu.fit.g14203.net.game;

import org.apache.log4j.Logger;
import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.net.channel.MessageChannel;
import ru.nsu.fit.g14203.net.channel.MessageChannelImpl;
import ru.nsu.fit.g14203.net.channel.MessageReceiver;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.function.Consumer;

abstract class AbstractGameThread extends Thread implements GameThread {

    private static final Logger LOG = Logger.getLogger(AbstractGameThread.class);

    final Selector selector;

    SelectionKey key;
    MessageChannelImpl messageChannel;

    final Consumer<Color> connectHandler;
    private final Runnable disconnectHandler;

    AbstractGameThread(Consumer<Color> connectHandler, Runnable disconnectHandler,
                       MessageReceiver receiver) throws IOException {
        selector = Selector.open();

        this.connectHandler = color -> {
            messageChannel.addReceiver(receiver);
            connectHandler.accept(color);
        };
        this.disconnectHandler = disconnectHandler;
    }

    public MessageChannel getMessageChannel() {
        return messageChannel;
    }

    @Override
    public void run() {
        try {
            beforeLoop();
            networkLoop();
            messageChannel.close();
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

                insideLoop();

                if (key.isWritable())
                    messageChannel.writeChannel();
                if (key.isReadable())
                    messageChannel.readChannel();

                selector.selectedKeys().remove(key);
            } catch (IOException e) {
                interrupt();
            }
        }
    }

    abstract void beforeLoop() throws IOException;
    abstract void insideLoop() throws IOException;
}
