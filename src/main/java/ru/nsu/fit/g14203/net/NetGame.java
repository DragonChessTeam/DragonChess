package ru.nsu.fit.g14203.net;

import org.jetbrains.annotations.Nullable;
import ru.nsu.fit.g14203.engine.api.Engine;
import ru.nsu.fit.g14203.engine.api.Observer;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class NetGame {

    private final ServerSocketChannel serverChannel;

    public NetGame(@Nullable SocketAddress localAddress) throws IOException {
        if (localAddress != null) {
            serverChannel = ServerSocketChannel.open();
            serverChannel.bind(localAddress);
        } else {
            serverChannel = null;
        }

        final Selector selector = Selector.open();
    }

    public Observer startGame() throws ServerDisabledException {
        if (serverChannel == null)
            throw new ServerDisabledException();

        return null;
    }

    public Engine joinGame(SocketAddress remoteAddress) {
        return null;
    }

    private void loop(int port) {

    }
}
