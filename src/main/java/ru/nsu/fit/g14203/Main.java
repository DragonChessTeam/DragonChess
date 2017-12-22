package ru.nsu.fit.g14203;

import ru.nsu.fit.g14203.engine.RealEngine;
import ru.nsu.fit.g14203.engine.api.Engine;
import ru.nsu.fit.g14203.engine.initializer.NormalEngineInitializer;

public class Main {

    public static void main(String[] args) {
        Engine eng = new RealEngine(new NormalEngineInitializer());
    }
}
