package ru.nsu.fit.g14203.engine.pieces;

import ru.nsu.fit.g14203.engine.api.Piece;
import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.engine.api.utils.Dot3D;
import ru.nsu.fit.g14203.engine.moveLanguage.Move;
import ru.nsu.fit.g14203.engine.moveLanguage.Or;
import ru.nsu.fit.g14203.engine.moveLanguage.UpToBorder;
import ru.nsu.fit.g14203.engine.moveLanguage.constraints.Constraint;
import ru.nsu.fit.g14203.engine.moveLanguage.constraints.LevelConstraint;
import ru.nsu.fit.g14203.engine.moveLanguage.moves.FullDiagonalMove;
import ru.nsu.fit.g14203.engine.moveLanguage.moves.RoundMove;
import ru.nsu.fit.g14203.engine.moveLanguage.moves.StepMove;
import ru.nsu.fit.g14203.engine.moveLanguage.moves.TriagonalMove;

import static ru.nsu.fit.g14203.engine.api.utils.Dot3D.*;

public class Dragon extends BasicPiece {

    public Dragon(Color c, Piece[][][] pg) {
        color = c;
        move = new Or(
                new RoundMove(),
                new UpToBorder(pg, new StepMove(FORWARD.sum(LEFT))),
                new UpToBorder(pg, new StepMove(FORWARD.sum(RIGHT))),
                new UpToBorder(pg, new StepMove(BACKWARD.sum(LEFT))),
                new UpToBorder(pg, new StepMove(BACKWARD.sum(RIGHT)))
        ).updateConstraints(new LevelConstraint(3));
        capture = new Or(
                move,
                new TriagonalMove(DOWN),
                new StepMove(DOWN)).updateConstraints(new LevelConstraint(3));
    }

}
