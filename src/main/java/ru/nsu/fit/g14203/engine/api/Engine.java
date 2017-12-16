package ru.nsu.fit.g14203.engine.api;

import ru.nsu.fit.g14203.engine.api.utils.EngineResponse;
import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.engine.api.utils.Way;

public interface Engine {

    EngineResponse doCapture(Color color, Way turn);

    EngineResponse doMove(Color color, Way turn);

    void registerObserver(Observer watcher);

    void deleteObserver(Observer watcher);
}
