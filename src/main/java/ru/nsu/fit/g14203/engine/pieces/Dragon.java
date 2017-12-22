package ru.nsu.fit.g14203.engine.pieces;

import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.engine.constraints.CheckConstraint;
import ru.nsu.fit.g14203.engine.constraints.StartLevelConstraint;
import ru.nsu.fit.g14203.engine.movelanguage.Or;
import ru.nsu.fit.g14203.engine.movelanguage.UpToBorder;
import ru.nsu.fit.g14203.engine.movelanguage.moves.RoundMove;
import ru.nsu.fit.g14203.engine.movelanguage.moves.StepMove;

import static ru.nsu.fit.g14203.engine.api.utils.Dot3D.*;
import static ru.nsu.fit.g14203.engine.constraints.ConstraintFormer.formCaptureConstraint;
import static ru.nsu.fit.g14203.engine.constraints.ConstraintFormer.formMoveConstraint;

public class Dragon extends BasicPiece{
    public Dragon(Color c) {
        color = c;
        move = new Or(
                new RoundMove().addConstraint(formMoveConstraint(new StartLevelConstraint(3))),
                new UpToBorder(new StepMove(LEFT.sum(FORWARD))).addConstraint(formMoveConstraint(new StartLevelConstraint(3))),
                new UpToBorder(new StepMove(RIGHT.sum(FORWARD))).addConstraint(formMoveConstraint(new StartLevelConstraint(3))),
                new UpToBorder(new StepMove(LEFT.sum(BACKWARD))).addConstraint(formMoveConstraint(new StartLevelConstraint(3))),
                new UpToBorder(new StepMove(RIGHT.sum(BACKWARD))).addConstraint(formMoveConstraint(new StartLevelConstraint(3)))
        ).addConstraint(new CheckConstraint());

        capture = new Or(
                new RoundMove().addConstraint(formCaptureConstraint(new StartLevelConstraint(3))),
                new UpToBorder(new StepMove(LEFT.sum(FORWARD))).addConstraint(formCaptureConstraint(new StartLevelConstraint(3))),
                new UpToBorder(new StepMove(RIGHT.sum(FORWARD))).addConstraint(formCaptureConstraint(new StartLevelConstraint(3))),
                new UpToBorder(new StepMove(LEFT.sum(BACKWARD))).addConstraint(formCaptureConstraint(new StartLevelConstraint(3))),
                new UpToBorder(new StepMove(RIGHT.sum(BACKWARD))).addConstraint(formCaptureConstraint(new StartLevelConstraint(3))),
                new StepMove(DOWN).addConstraint(formCaptureConstraint(new StartLevelConstraint(3))),
                new StepMove(DOWN.sum(FORWARD)).addConstraint(formCaptureConstraint(new StartLevelConstraint(3))),
                new StepMove(DOWN.sum(LEFT)).addConstraint(formCaptureConstraint(new StartLevelConstraint(3))),
                new StepMove(DOWN.sum(RIGHT)).addConstraint(formCaptureConstraint(new StartLevelConstraint(3))),
                new StepMove(DOWN.sum(BACKWARD)).addConstraint(formCaptureConstraint(new StartLevelConstraint(3)))
        ).addConstraint(new CheckConstraint());
    }
}
