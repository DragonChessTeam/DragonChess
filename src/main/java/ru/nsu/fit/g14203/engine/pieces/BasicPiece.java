package ru.nsu.fit.g14203.engine.pieces;

import ru.nsu.fit.g14203.engine.api.Piece;
import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.engine.api.utils.Dot3D;
import ru.nsu.fit.g14203.engine.moveLanguage.constraints.Constraint;
import ru.nsu.fit.g14203.engine.moveLanguage.Move;

import java.util.*;

public class BasicPiece implements Piece {

    protected Color color;
    protected List<Constraint> constraints = new ArrayList<>();

    protected Move capture;
    protected Move move;


    public Color getColor() {
        return color;
    }

    @Override
    public List<Dot3D> getAvailableMoves(Dot3D position, Piece[][][] boards) {
        return null;
    }

    @Override
    public List<Dot3D> getAvailableCaptures(Dot3D position, Piece[][][] boards) {
        return null;
    }


    public void addConstraint(Constraint effect) {
        constraints.add(effect);
    }

    public void delConstraint(Constraint effect) {
        constraints.remove(effect);
    }
}
