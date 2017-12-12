package ru.nsu.fit.g14203.engine.pieces;

import ru.nsu.fit.g14203.engine.api.Piece;
import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.engine.moveLanguage.Or;
import ru.nsu.fit.g14203.engine.moveLanguage.UpTo;
import ru.nsu.fit.g14203.engine.moveLanguage.UpToBorder;
import ru.nsu.fit.g14203.engine.moveLanguage.constraints.LevelConstraint;
import ru.nsu.fit.g14203.engine.moveLanguage.moves.FullOrthogonalMove;
import ru.nsu.fit.g14203.engine.moveLanguage.moves.StepMove;

import static ru.nsu.fit.g14203.engine.api.utils.Dot3D.*;

public class Mage extends BasicPiece {


    public Mage(Color c, Piece[][][] pg) {
        color = c;
        move = new Or (
                new Or(
                    new UpToBorder(pg, new StepMove(FORWARD)),
                    new UpToBorder(pg, new StepMove(FORWARD.sum(LEFT))),
                    new UpToBorder(pg, new StepMove(LEFT)),
                    new UpToBorder(pg, new StepMove(BACKWARD.sum(LEFT))),
                    new UpToBorder(pg, new StepMove(BACKWARD)),
                    new UpToBorder(pg, new StepMove(BACKWARD.sum(RIGHT))),
                    new UpToBorder(pg, new StepMove(RIGHT)),
                    new UpToBorder(pg, new StepMove(FORWARD.sum(RIGHT)))
                ).updateConstraints(new LevelConstraint(2)),
                new FullOrthogonalMove().updateConstraints(new LevelConstraint(1,3)),
                new UpTo(2, new StepMove(UP)),
                new UpTo(2, new StepMove(DOWN))
        );

        capture = new Or (
                new Or(
                        new UpToBorder(pg, new StepMove(FORWARD)),
                        new UpToBorder(pg, new StepMove(FORWARD.sum(LEFT))),
                        new UpToBorder(pg, new StepMove(LEFT)),
                        new UpToBorder(pg, new StepMove(BACKWARD.sum(LEFT))),
                        new UpToBorder(pg, new StepMove(BACKWARD)),
                        new UpToBorder(pg, new StepMove(BACKWARD.sum(RIGHT))),
                        new UpToBorder(pg, new StepMove(RIGHT)),
                        new UpToBorder(pg, new StepMove(FORWARD.sum(RIGHT)))
                ).updateConstraints(new LevelConstraint(2)),
                new UpTo(2, new StepMove(UP)),
                new UpTo(2, new StepMove(DOWN))
        );

    }
}
