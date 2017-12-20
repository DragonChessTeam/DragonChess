package ru.nsu.fit.g14203.net;

import ru.nsu.fit.g14203.engine.api.Engine;
import ru.nsu.fit.g14203.engine.api.Observer;
import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.engine.api.utils.EngineResponse;
import ru.nsu.fit.g14203.engine.api.utils.Way;
import ru.nsu.fit.g14203.net.util.*;

import java.io.IOException;
import java.net.SocketAddress;
import java.util.function.Consumer;

public class NetEngine implements Engine, MessageReceiver {

    public enum Type {
        SERVER,
        CLIENT
    }

    private final Engine engine;
    private final GameThread thread;

    public NetEngine(Type type, SocketAddress address, Engine engine,
                     Consumer<Color> connectHandler, Runnable disconnectHandler)
            throws IOException {
        this.engine = engine;

        final Consumer<Color> __connect = color -> {
            subscribe();
            connectHandler.accept(color);
        };

        if (type == Type.SERVER)
            thread = new ServerThread(address, __connect, disconnectHandler);
        else
            thread = new ClientThread(address, __connect, disconnectHandler);

        thread.start();
    }

    private void subscribe() {
        thread.getMessageChannel().addReceiver(this);
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
                doCapture(step.getColor(), step.getWay());
            else
                doMove(step.getColor(), step.getWay());
        }
    }
}
