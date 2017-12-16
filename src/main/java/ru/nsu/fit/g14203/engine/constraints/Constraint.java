package ru.nsu.fit.g14203.engine.constraints;

import ru.nsu.fit.g14203.engine.api.Piece;
import ru.nsu.fit.g14203.engine.api.utils.Dot3D;

import java.util.List;

public interface Constraint {
    void changeMove(List<Dot3D> ends, Dot3D start, Piece[][][] boards);
    void changeCapture(List<Dot3D> ends, Dot3D start, Piece[][][] boards);
}
