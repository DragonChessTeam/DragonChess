package ru.nsu.fit.g14203.engine.api;

import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.engine.api.utils.Dot3D;
import ru.nsu.fit.g14203.engine.constraints.Constraint;
import ru.nsu.fit.g14203.engine.constraints.Immobilized;
import ru.nsu.fit.g14203.engine.moveLanguage.Move;

import java.util.List;

public interface Piece {

    Color getColor();
    List<Dot3D> getAvailableMoves(Dot3D position, Piece[][][] boards);
    List<Dot3D> getAvailableCaptures(Dot3D position, Piece[][][] boards);

    Move getMoveAlg();
    Move getCaptureAlg();
}
