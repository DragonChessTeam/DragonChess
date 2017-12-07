package ru.nsu.fit.g14203.engine;

import ru.nsu.fit.g14203.engine.guiapi.Observer;
import ru.nsu.fit.g14203.engine.utils.Color;
import ru.nsu.fit.g14203.engine.utils.Way;

public interface Engine {

    public abstract EngineResponse doTurn(Color color, Way turn);

    public abstract void registerObserver(Observer watcher);

    public abstract EngineResponse doCapture(Color color, Way turn);

}
