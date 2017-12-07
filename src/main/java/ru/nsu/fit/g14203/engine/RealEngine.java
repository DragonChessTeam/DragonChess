package ru.nsu.fit.g14203.engine;

import java.util.List;

public class RealEngine implements Engine {

    private Playground boards;

    private GameState state;

    private List<Observer> observers;


    /**
     * @see Engine#doTurn(Color, Way)
     */
    public EngineResponse doTurn(Color color, Way turn) {
        return null;
    }


    /**
     * @see Engine#registerObserver(Observer)
     */
    public void registerObserver(Observer watcher) {

    }


    /**
     * @see Engine#doCapture(Color, Way)
     */
    public EngineResponse doCapture(Color color, Way turn) {
        return null;
    }

}
