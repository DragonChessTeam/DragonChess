package ru.nsu.fit.g14203.engine.pieces;

import ru.nsu.fit.g14203.engine.api.Piece;
import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.engine.api.utils.Dot3D;
import ru.nsu.fit.g14203.engine.movelanguage.Move;

import java.util.*;

public class BasicPiece implements Piece {

    protected Color color;

    protected Move capture;
    protected Move move;


    public Color getColor() {
        return color;
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName().substring(0,3);
    }

    @Override
    public List<Dot3D> getAvailableMoves(Dot3D position, Piece[][][] boards) {
        return move.getMovesFrom(position, boards);
    }

    @Override
    public List<Dot3D> getAvailableCaptures(Dot3D position, Piece[][][] boards) {
        return capture.getMovesFrom(position,boards );

    }

    @Override
    public Move getMoveAlg() {
        return move;
    }

    @Override
    public Move getCaptureAlg() {
        return capture;
    }
}
