package ru.nsu.fit.g14203.net;

import ru.nsu.fit.g14203.engine.api.Engine;
import ru.nsu.fit.g14203.engine.api.Observer;
import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.engine.api.utils.EngineResponse;
import ru.nsu.fit.g14203.engine.api.utils.Way;

import java.io.IOException;
import java.net.SocketAddress;
import java.util.function.Consumer;

public class NetEngine implements Engine {

    public enum Type {
        SERVER,
        CLIENT
    }

    /**
     * Creates Engine for network game.
     *
     * @param type can be SERVER for start game or CLIENT for join game
     * @param address local address for SERVER of remote address for CLIENT
     * @param engine engine to use in game
     * @param connectHandler method to be called after accept (SERVER) or connect (CLIENT)
     * @param disconnectHandler method to be called after opponent disconnect
     * @throws IOException if some IO errors with sockets occurs during initialisation
     */
    public NetEngine(Type type, SocketAddress address, Engine engine,
                     Consumer<Color> connectHandler, Runnable disconnectHandler)
            throws IOException {
    }

    @Override
    public EngineResponse doCapture(Color color, Way turn) {
        return null;
    }

    @Override
    public EngineResponse doMove(Color color, Way turn) {
        return null;
    }

    @Override
    public void registerObserver(Observer observer) {
    }

    @Override
    public void deleteObserver(Observer observer) {
    }
}
