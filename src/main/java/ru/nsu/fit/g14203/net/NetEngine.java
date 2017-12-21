package ru.nsu.fit.g14203.net;

import ru.nsu.fit.g14203.engine.api.Engine;
import ru.nsu.fit.g14203.engine.api.Observer;
import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.engine.api.utils.EngineResponse;
import ru.nsu.fit.g14203.engine.api.utils.Way;
import ru.nsu.fit.g14203.net.game.GameThread;
import ru.nsu.fit.g14203.net.game.GameThreadFactory;
import ru.nsu.fit.g14203.net.channel.DisconnectMessage;
import ru.nsu.fit.g14203.net.channel.Message;
import ru.nsu.fit.g14203.net.channel.MessageReceiver;
import ru.nsu.fit.g14203.net.channel.StepMessage;

import java.io.IOException;
import java.net.SocketAddress;
import java.util.function.Consumer;

public class NetEngine implements Engine, MessageReceiver {

    public static final int TYPE_SERVER = GameThreadFactory.TYPE_SERVER;
    public static final int TYPE_CLIENT = GameThreadFactory.TYPE_CLIENT;

    private final Engine engine;
    private final GameThread thread;

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
    public NetEngine(int type, SocketAddress address, Engine engine,
                     Consumer<Color> connectHandler, Runnable disconnectHandler)
            throws IOException {
        this.engine = engine;

        thread = GameThreadFactory.createGame(type, address,
                connectHandler, disconnectHandler, this);
        thread.start();
    }

    @Override
    public EngineResponse doCapture(Color color, Way turn) {
        final EngineResponse response = engine.doCapture(color, turn);

        thread.getMessageChannel().send(new StepMessage(Message.TYPE_CAPTURE, color, turn));

        return response;
    }

    @Override
    public EngineResponse doMove(Color color, Way turn) {
        final EngineResponse response = engine.doMove(color, turn);

        thread.getMessageChannel().send(new StepMessage(Message.TYPE_MOVE, color, turn));

        return response;
    }

    @Override
    public void registerObserver(Observer observer) {
        engine.registerObserver(observer);
    }

    @Override
    public void deleteObserver(Observer observer) {
        engine.deleteObserver(observer);
    }

    @Override
    public void receive(Message message) {
        if (message instanceof DisconnectMessage) {
            thread.interrupt();
            return;
        }

        if (message instanceof StepMessage) {
            final StepMessage step = (StepMessage) message;
            if (step.getType() == Message.TYPE_CAPTURE)
                engine.doCapture(step.getColor(), step.getWay());
            else
                engine.doMove(step.getColor(), step.getWay());
        }
    }
}
