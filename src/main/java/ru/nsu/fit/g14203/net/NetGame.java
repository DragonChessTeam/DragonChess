package ru.nsu.fit.g14203.net;

import org.jetbrains.annotations.Nullable;
import ru.nsu.fit.g14203.engine.api.Engine;
import ru.nsu.fit.g14203.engine.api.Observer;
import ru.nsu.fit.g14203.net.util.MessageChannelImpl;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;

//  TODO: (1): Log uncommon exceptions.

public class NetGame {

    private final Object lock = new Object();

    private final Selector selector;

    private final SelectionKey serverKey;
    private final ServerSocketChannel serverChannel;

    private final Map<SelectionKey, MessageChannelImpl> channels = new HashMap<>();

    public NetGame() throws IOException {
        this(null);
    }

    public NetGame(@Nullable SocketAddress localAddress) throws IOException {
        selector = Selector.open();

        if (localAddress != null) {
            serverChannel = ServerSocketChannel.open();
            serverChannel.bind(localAddress);

            serverKey = serverChannel.register(selector, 0);
        } else {
            serverChannel = null;
            serverKey = null;
        }
    }

    public Observer startGame() throws ServerDisabledException {
        if (serverChannel == null)
            throw new ServerDisabledException();

        SocketChannel socketChannel;
        synchronized (lock) {
            selector.wakeup();
            try {
                lock.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);  //  TODO: (1)
            }
        }

        return null;
    }

    public Engine joinGame(SocketAddress remoteAddress) {
        return null;
    }

    private void loop(int port) {

    }
}
