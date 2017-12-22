package ru.nsu.fit.g14203.engine.movelanguage;

import ru.nsu.fit.g14203.engine.api.Piece;
import ru.nsu.fit.g14203.engine.api.utils.Dot3D;
import ru.nsu.fit.g14203.engine.constraints.Constraint;

import java.util.List;

public interface Move {
    List<Dot3D> getMovesFrom(Dot3D pos, Piece[][][] boards);
    Move addConstraint(Constraint... c);
    Move removeConstraint(Constraint... c);
    Move removeConstraint(Class constraintClass);
}
