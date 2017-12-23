package ru.nsu.fit.g14203;

import ru.nsu.fit.g14203.engine.RealEngine;
import ru.nsu.fit.g14203.engine.api.Engine;
import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.engine.initializer.NormalEngineInitializer;
import ru.nsu.fit.g14203.view.InitMainWindow;

public class Main {

    public static void main(String[] args) {

        Engine eng = new RealEngine(new NormalEngineInitializer());
        InitMainWindow mainWindow = new InitMainWindow(eng, Color.WHITE);
        eng.registerObserver(mainWindow.getMainPanel());
    }
}
