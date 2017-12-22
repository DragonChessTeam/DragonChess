package ru.nsu.fit.g14203.engine.pieces;

import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.engine.constraints.CheckConstraint;
import ru.nsu.fit.g14203.engine.constraints.StartLevelConstraint;
import ru.nsu.fit.g14203.engine.movelanguage.Or;
import ru.nsu.fit.g14203.engine.movelanguage.UpToBorder;
import ru.nsu.fit.g14203.engine.movelanguage.moves.StepMove;

import static ru.nsu.fit.g14203.engine.api.utils.Dot3D.*;
import static ru.nsu.fit.g14203.engine.constraints.ConstraintFormer.formCaptureConstraint;
import static ru.nsu.fit.g14203.engine.constraints.ConstraintFormer.formMoveConstraint;

public class Thief extends BasicPiece {
    public Thief(Color c) {
        color = c;
        move = new Or(
                new UpToBorder(new StepMove(LEFT.sum(FORWARD))).addConstraint(formMoveConstraint(new StartLevelConstraint(2))),
                new UpToBorder(new StepMove(LEFT.sum(BACKWARD))).addConstraint(formMoveConstraint(new StartLevelConstraint(2))),
                new UpToBorder(new StepMove(RIGHT.sum(FORWARD))).addConstraint(formMoveConstraint(new StartLevelConstraint(2))),
                new UpToBorder(new StepMove(RIGHT.sum(BACKWARD))).addConstraint(formMoveConstraint(new StartLevelConstraint(2)))
        ).addConstraint(new CheckConstraint());
        capture = new Or(
                new UpToBorder(new StepMove(LEFT.sum(FORWARD))).addConstraint(formCaptureConstraint(new StartLevelConstraint(2))),
                new UpToBorder(new StepMove(LEFT.sum(BACKWARD))).addConstraint(formCaptureConstraint(new StartLevelConstraint(2))),
                new UpToBorder(new StepMove(RIGHT.sum(FORWARD))).addConstraint(formCaptureConstraint(new StartLevelConstraint(2))),
                new UpToBorder(new StepMove(RIGHT.sum(BACKWARD))).addConstraint(formCaptureConstraint(new StartLevelConstraint(2)))
        ).addConstraint(new CheckConstraint());
    }
}
