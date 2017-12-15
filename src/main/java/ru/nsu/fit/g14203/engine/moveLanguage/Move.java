package ru.nsu.fit.g14203.engine.moveLanguage;

import ru.nsu.fit.g14203.engine.api.utils.Dot3D;
import ru.nsu.fit.g14203.engine.constraints.Constraint;

import java.util.List;

public interface Move {
    List<Dot3D> getMovesFrom(Dot3D pos);
    Move addConstraint(Constraint... c);
    Move removeConstraint(Constraint... c);
    Move removeConstraint(Class constraintClass);
}
