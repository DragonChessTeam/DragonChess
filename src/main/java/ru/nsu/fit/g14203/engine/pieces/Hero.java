package ru.nsu.fit.g14203.engine.pieces;

import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.engine.moveLanguage.Move;
import ru.nsu.fit.g14203.engine.moveLanguage.Or;
import ru.nsu.fit.g14203.engine.moveLanguage.UpTo;
import ru.nsu.fit.g14203.engine.moveLanguage.constraints.LevelConstraint;
import ru.nsu.fit.g14203.engine.moveLanguage.moves.StepMove;
import ru.nsu.fit.g14203.engine.moveLanguage.moves.TriagonalMove;

import static ru.nsu.fit.g14203.engine.api.utils.Dot3D.*;

public class Hero extends BasicPiece {

    public Hero(Color c) {
        color = c;
        move = new Or(
                new UpTo(2, new StepMove(FORWARD.sum(LEFT))),
                new UpTo(2, new StepMove(FORWARD.sum(RIGHT))),
                new UpTo(2, new StepMove(BACKWARD.sum(LEFT))),
                new UpTo(2, new StepMove(BACKWARD.sum(RIGHT))),
                new TriagonalMove(UP),
                new TriagonalMove(DOWN)
        ).updateConstraints(new LevelConstraint(2));

        //TODO: add return to previous
        capture = move;
    }
}
