package ru.nsu.fit.g14203.engine.movelanguage;

import ru.nsu.fit.g14203.engine.api.Piece;
import ru.nsu.fit.g14203.engine.api.utils.Dot3D;
import ru.nsu.fit.g14203.engine.constraints.Constraint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class BasicMove implements Move{

    protected List<Constraint> constraints = new ArrayList<>();

    protected abstract List<Dot3D> doMove(Dot3D pos, Piece[][][] boards);

    public List<Dot3D> getMovesFrom(Dot3D pos, Piece[][][] boards) {
        List<Dot3D> out = doMove(pos,boards);

        if (constraints != null) {
            for (Constraint c : constraints) {
                c.changeMove(out, pos, boards);
            }
        }

        return out;
    }

    @Override
    public Move addConstraint(Constraint... c) {

        constraints.addAll(Arrays.asList(c));
        return this;
    }


    @Override
    public Move removeConstraint(Constraint... c) {
        constraints.removeAll(Arrays.asList(c));
        return this;
    }

    @Override
    public Move removeConstraint(Class constraintClass) {
        for (Constraint c : constraints) {
            if (c.getClass().equals(constraintClass)) {
                constraints.remove(c);
                break;
            }
        }
        return this;
    }

}
