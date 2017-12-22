package ru.nsu.fit.g14203.engine.pieces;

import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.engine.api.utils.Dot3D;
import ru.nsu.fit.g14203.engine.constraints.CheckConstraint;
import ru.nsu.fit.g14203.engine.constraints.StartLevelConstraint;
import ru.nsu.fit.g14203.engine.movelanguage.Or;
import ru.nsu.fit.g14203.engine.movelanguage.moves.AbsoluteMove;
import ru.nsu.fit.g14203.engine.movelanguage.moves.StepMove;

import static ru.nsu.fit.g14203.engine.api.utils.Color.WHITE;
import static ru.nsu.fit.g14203.engine.api.utils.Dot3D.*;
import static ru.nsu.fit.g14203.engine.constraints.ConstraintFormer.formCaptureConstraint;
import static ru.nsu.fit.g14203.engine.constraints.ConstraintFormer.formMoveConstraint;

public class Sylph extends BasicPiece{
    public Sylph(Color c) {
        color = c;

        if (color == WHITE) {
            move = new Or (
                    new Or(
                            new StepMove(LEFT.sum(FORWARD)),
                            new StepMove(RIGHT.sum(FORWARD))
                    ).addConstraint(formMoveConstraint(new StartLevelConstraint(3))),
                    new Or (
                            new StepMove(UP),
                            new AbsoluteMove(new Dot3D(0, 6, 2)),
                            new AbsoluteMove(new Dot3D(2, 6, 2)),
                            new AbsoluteMove(new Dot3D(4, 6, 2)),
                            new AbsoluteMove(new Dot3D(6, 6, 2)),
                            new AbsoluteMove(new Dot3D(8, 6, 2)),
                            new AbsoluteMove(new Dot3D(10, 6, 2))
                    ).addConstraint(formMoveConstraint(new StartLevelConstraint(2)))
            ).addConstraint(new CheckConstraint());

            capture = new Or(
                    new StepMove(FORWARD),
                    new StepMove(DOWN)
            ).addConstraint(formCaptureConstraint(new StartLevelConstraint(3))).addConstraint(new CheckConstraint());
        } else {
            move = new Or (
                    new Or(
                            new StepMove(LEFT.sum(BACKWARD)),
                            new StepMove(RIGHT.sum(BACKWARD))
                            ).addConstraint(formMoveConstraint(new StartLevelConstraint(3))),
                    new Or (
                            new StepMove(UP),
                            new AbsoluteMove(new Dot3D(0, 1, 2)),
                            new AbsoluteMove(new Dot3D(2, 1, 2)),
                            new AbsoluteMove(new Dot3D(4, 1, 2)),
                            new AbsoluteMove(new Dot3D(6, 1, 2)),
                            new AbsoluteMove(new Dot3D(8, 1, 2)),
                            new AbsoluteMove(new Dot3D(10, 1, 2))
                    ).addConstraint(formMoveConstraint(new StartLevelConstraint(2)))
            ).addConstraint(new CheckConstraint());

            capture = new Or(
                    new StepMove(BACKWARD),
                    new StepMove(DOWN)
            ).addConstraint(formCaptureConstraint(new StartLevelConstraint(3))).addConstraint(new CheckConstraint());
        }

    }
}
