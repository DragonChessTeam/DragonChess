package ru.nsu.fit.g14203.net;

import org.jetbrains.annotations.Nullable;
import ru.nsu.fit.g14203.engine.api.Engine;
import ru.nsu.fit.g14203.engine.api.Observer;

import java.io.IOException;
import java.net.SocketAddress;

public class NetGame {

    public NetGame() throws IOException {
        this(null);
    }

    public NetGame(@Nullable SocketAddress localAddress) throws IOException {
    }

    public Observer startGame() throws ServerDisabledException {
        return null;
    }

    public Engine joinGame(SocketAddress remoteAddress) {
        return null;
    }
}
