package ru.nsu.fit.g14203.engine.moveLanguage;

import ru.nsu.fit.g14203.engine.api.utils.Dot3D;
import ru.nsu.fit.g14203.engine.moveLanguage.constraints.Constraint;

import java.util.List;

public abstract class BasicMove implements Move{

    protected Constraint[] constraints;

    public BasicMove updateConstraints(Constraint... constrs) {
        constraints = constrs;
        return this;
    }

    protected List<Dot3D> doMove(Dot3D pos) { return null;};

    public List<Dot3D> getMovesFrom(Dot3D pos) {
        List<Dot3D> out = doMove(pos);

        for (Constraint c : constraints) {
            c.changeValidMoves(pos, out);
        }

        return out;
    }

}
