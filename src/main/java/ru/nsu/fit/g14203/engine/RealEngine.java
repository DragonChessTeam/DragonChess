package ru.nsu.fit.g14203.engine;

import ru.nsu.fit.g14203.engine.guiapi.Observer;
import ru.nsu.fit.g14203.engine.triggers.Trigger;
import ru.nsu.fit.g14203.engine.utils.Color;
import ru.nsu.fit.g14203.engine.utils.Playground;
import ru.nsu.fit.g14203.engine.utils.Way;

import java.util.List;

public class RealEngine implements Engine {

    private Playground boards;

    private GameState state;

    private List<Observer> observers;

    private List<Trigger> triggers;


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
