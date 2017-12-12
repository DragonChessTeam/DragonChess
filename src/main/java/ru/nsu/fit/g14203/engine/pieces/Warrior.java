package ru.nsu.fit.g14203.engine.pieces;

import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.engine.moveLanguage.Or;
import ru.nsu.fit.g14203.engine.moveLanguage.constraints.LevelConstraint;
import ru.nsu.fit.g14203.engine.moveLanguage.moves.StepMove;

import static ru.nsu.fit.g14203.engine.api.utils.Dot3D.FORWARD;
import static ru.nsu.fit.g14203.engine.api.utils.Dot3D.LEFT;
import static ru.nsu.fit.g14203.engine.api.utils.Dot3D.RIGHT;

public class Warrior extends BasicPiece {
    public Warrior(Color c) {
        color = c;
        move = new StepMove(FORWARD).updateConstraints(new LevelConstraint(2));
        capture = new Or(
                new StepMove(FORWARD.sum(LEFT)),
                new StepMove(FORWARD.sum(RIGHT))
        ).updateConstraints(new LevelConstraint(2));

    }

}
