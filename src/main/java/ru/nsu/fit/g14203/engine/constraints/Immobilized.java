package ru.nsu.fit.g14203.engine.constraints;

import ru.nsu.fit.g14203.engine.api.utils.Dot3D;

import java.util.List;

public class Immobilized implements Constraint{
    @Override
    public void changeMove(List<Dot3D> ends, Dot3D start) {
        ends.clear();
    }

    @Override
    public void changeCapture(List<Dot3D> ends, Dot3D start) {
        ends.clear();
    }
}
