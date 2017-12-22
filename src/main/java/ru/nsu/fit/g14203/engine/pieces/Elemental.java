package ru.nsu.fit.g14203.engine.pieces;


import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.engine.constraints.CheckConstraint;
import ru.nsu.fit.g14203.engine.constraints.StartLevelConstraint;
import ru.nsu.fit.g14203.engine.movelanguage.Follow;
import ru.nsu.fit.g14203.engine.movelanguage.Or;
import ru.nsu.fit.g14203.engine.movelanguage.UpTo;
import ru.nsu.fit.g14203.engine.movelanguage.moves.OneOrthogonal;
import ru.nsu.fit.g14203.engine.movelanguage.moves.StepMove;

import static ru.nsu.fit.g14203.engine.api.utils.Dot3D.*;
import static ru.nsu.fit.g14203.engine.constraints.ConstraintFormer.formCaptureConstraint;
import static ru.nsu.fit.g14203.engine.constraints.ConstraintFormer.formMoveConstraint;

public class Elemental extends BasicPiece {
    public Elemental(Color c) {
        color = c;
        move = new Or(
                new Or(
                        new UpTo(2, new StepMove(FORWARD)).addConstraint(formMoveConstraint(new StartLevelConstraint(1))),
                        new UpTo(2, new StepMove(BACKWARD)).addConstraint(formMoveConstraint(new StartLevelConstraint(1))),
                        new UpTo(2, new StepMove(LEFT)).addConstraint(formMoveConstraint(new StartLevelConstraint(1))),
                        new UpTo(2, new StepMove(RIGHT)).addConstraint(formMoveConstraint(new StartLevelConstraint(1))),
                        new StepMove(LEFT.sum(FORWARD)),
                        new StepMove(RIGHT.sum(FORWARD)),
                        new StepMove(LEFT.sum(BACKWARD)),
                        new StepMove(RIGHT.sum(BACKWARD))
                ).addConstraint(formMoveConstraint(new StartLevelConstraint(1))),
                new Follow(new StepMove(DOWN), new OneOrthogonal()).addConstraint(formMoveConstraint(new StartLevelConstraint(2)))
        ).addConstraint(new CheckConstraint());

        capture = new Or (
                new Or(
                        new UpTo(2, new StepMove(FORWARD)).addConstraint(formCaptureConstraint(new StartLevelConstraint(1))),
                        new UpTo(2, new StepMove(BACKWARD)).addConstraint(formCaptureConstraint(new StartLevelConstraint(1))),
                        new UpTo(2, new StepMove(LEFT)).addConstraint(formCaptureConstraint(new StartLevelConstraint(1))),
                        new UpTo(2, new StepMove(RIGHT)).addConstraint(formCaptureConstraint(new StartLevelConstraint(1))),
                        new Follow(new OneOrthogonal(), new StepMove(UP)).addConstraint(formCaptureConstraint(new StartLevelConstraint(1)))
                ),
                new Follow(new StepMove(DOWN), new OneOrthogonal()).addConstraint(formCaptureConstraint(new StartLevelConstraint(2)))
        ).addConstraint(new CheckConstraint());

    }
}
