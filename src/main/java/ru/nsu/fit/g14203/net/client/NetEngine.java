package ru.nsu.fit.g14203.net.client;

import ru.nsu.fit.g14203.engine.api.Engine;
import ru.nsu.fit.g14203.engine.api.Observer;
import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.engine.api.utils.EngineResponse;
import ru.nsu.fit.g14203.engine.api.utils.Way;
import ru.nsu.fit.g14203.net.util.MessageChannel;

public class NetEngine implements Engine {

    public NetEngine(MessageChannel channel) {

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
    public void registerObserver(Observer watcher) {

    }

    @Override
    public void deleteObserver(Observer watcher) {

    }
}
