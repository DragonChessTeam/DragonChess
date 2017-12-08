package ru.nsu.fit.g14203.engine.utils;

public class Way {

    public Dot3D end;

    public Dot3D start;

    public Way(Dot3D pos, Dot3D endPoint) {
        end = endPoint;
        start = pos;
    }
}
