package ru.nsu.fit.g14203.engine.api;

import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.engine.api.utils.Dot3D;
import ru.nsu.fit.g14203.engine.movelanguage.Move;

import java.util.List;

public interface Piece {

    Color getColor();
    String getName();
    List<Dot3D> getAvailableMoves(Dot3D position, Piece[][][] boards);
    List<Dot3D> getAvailableCaptures(Dot3D position, Piece[][][] boards);

    Move getMoveAlg();
    Move getCaptureAlg();
}
