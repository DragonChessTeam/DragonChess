package ru.nsu.fit.g14203.engine.pieces;

import ru.nsu.fit.g14203.engine.constraints.Constraint;

import java.util.ArrayList;
import java.util.List;

public abstract  class BasicPiece implements Piece{
    protected List<Constraint> constraints = new ArrayList<>();

    public void addConstraint(Constraint effect) {
        constraints.add(effect);
    }

    public void delConstraint(Constraint effect) {
        constraints.remove(effect);
    }
}
