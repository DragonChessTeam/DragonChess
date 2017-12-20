package ru.nsu.fit.g14203.net;

import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.net.util.MessageChannel;

import java.io.IOException;
import java.nio.channels.Selector;
import java.util.function.Consumer;

abstract class GameThread extends Thread {

    protected final Selector selector;

    protected final Consumer<Color> connectHandler;
    protected final Runnable disconnectHandler;

    GameThread(Consumer<Color> connectHandler, Runnable disconnectHandler) throws IOException {
        selector = Selector.open();

        this.connectHandler = connectHandler;
        this.disconnectHandler = disconnectHandler;
    }

    abstract MessageChannel getMessageChannel();
}
