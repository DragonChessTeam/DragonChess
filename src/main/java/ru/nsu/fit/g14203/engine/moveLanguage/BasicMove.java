package ru.nsu.fit.g14203.engine.moveLanguage;

import ru.nsu.fit.g14203.engine.api.utils.Dot3D;
import ru.nsu.fit.g14203.engine.constraints.Constraint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class BasicMove implements Move{

    protected List<Constraint> constraints = new ArrayList<>();

    abstract protected List<Dot3D> doMove(Dot3D pos);

    public List<Dot3D> getMovesFrom(Dot3D pos) {
        List<Dot3D> out = doMove(pos);

        if (constraints != null)
        for (Constraint c : constraints) {
            c.changeCapture(out, pos);
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
