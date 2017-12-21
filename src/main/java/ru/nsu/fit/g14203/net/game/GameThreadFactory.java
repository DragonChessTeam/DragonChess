package ru.nsu.fit.g14203.net.game;

import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.net.channel.MessageReceiver;

import java.io.IOException;
import java.net.SocketAddress;
import java.util.function.Consumer;

public final class GameThreadFactory {

    public static final int TYPE_SERVER = 0;
    public static final int TYPE_CLIENT = 1;

    public static GameThread createGame(int type, SocketAddress address,
                                        Consumer<Color> connectHandler, Runnable disconnectHandler,
                                        MessageReceiver receiver) throws IOException {
        switch (type) {
            case TYPE_SERVER:
                return new ServerThread(address, connectHandler, disconnectHandler, receiver);
            case TYPE_CLIENT:
                return new ClientThread(address, connectHandler, disconnectHandler, receiver);
            default:
                throw new IllegalArgumentException("Expected TYPE_SERVER or TYPE_CLIENT");
        }
    }
}
