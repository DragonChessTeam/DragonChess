package ru.nsu.fit.g14203.engine;

public interface Engine {

    public abstract EngineResponse doTurn(Color color, Way turn);

    public abstract void registerObserver(Observer watcher);

    public abstract EngineResponse doCapture(Color color, Way turn);

}
