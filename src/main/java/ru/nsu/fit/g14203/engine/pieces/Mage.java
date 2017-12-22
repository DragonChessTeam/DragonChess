package ru.nsu.fit.g14203.engine.pieces;

import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.engine.constraints.BlockConstraint;
import ru.nsu.fit.g14203.engine.constraints.StartLevelConstraint;
import ru.nsu.fit.g14203.engine.moveLanguage.Or;
import ru.nsu.fit.g14203.engine.moveLanguage.UpToBorder;
import ru.nsu.fit.g14203.engine.moveLanguage.moves.OneOrthogonal;
import ru.nsu.fit.g14203.engine.moveLanguage.moves.StepMove;

import static ru.nsu.fit.g14203.engine.api.utils.Dot3D.*;
import static ru.nsu.fit.g14203.engine.constraints.ConstraintFormer.formCaptureConstraint;
import static ru.nsu.fit.g14203.engine.constraints.ConstraintFormer.formMoveConstraint;

public class Mage extends BasicPiece {
    public Mage(Color c) {
        color = c;
        move = new Or(
                new UpToBorder(new StepMove(LEFT.sum(FORWARD))).addConstraint(formMoveConstraint(new StartLevelConstraint(2))),
                new UpToBorder(new StepMove(LEFT.sum(BACKWARD))).addConstraint(formMoveConstraint(new StartLevelConstraint(2))),
                new UpToBorder(new StepMove(RIGHT.sum(FORWARD))).addConstraint(formMoveConstraint(new StartLevelConstraint(2))),
                new UpToBorder(new StepMove(RIGHT.sum(BACKWARD))).addConstraint(formMoveConstraint(new StartLevelConstraint(2))),
                new UpToBorder(new StepMove(LEFT)).addConstraint(formMoveConstraint(new StartLevelConstraint(2))),
                new UpToBorder(new StepMove(BACKWARD)).addConstraint(formMoveConstraint(new StartLevelConstraint(2))),
                new UpToBorder(new StepMove(RIGHT)).addConstraint(formMoveConstraint(new StartLevelConstraint(2))),
                new UpToBorder(new StepMove(BACKWARD)).addConstraint(formMoveConstraint(new StartLevelConstraint(2))),
                new OneOrthogonal().addConstraint(formMoveConstraint(new StartLevelConstraint(1,3))),
                new UpToBorder(new StepMove(UP)).addConstraint(formMoveConstraint()),
                new UpToBorder(new StepMove(DOWN)).addConstraint(formMoveConstraint())
        );

        capture = new Or(
                new UpToBorder(new StepMove(LEFT.sum(FORWARD))).addConstraint(formCaptureConstraint(new StartLevelConstraint(2))),
                new UpToBorder(new StepMove(LEFT.sum(BACKWARD))).addConstraint(formCaptureConstraint(new StartLevelConstraint(2))),
                new UpToBorder(new StepMove(RIGHT.sum(FORWARD))).addConstraint(formCaptureConstraint(new StartLevelConstraint(2))),
                new UpToBorder(new StepMove(RIGHT.sum(BACKWARD))).addConstraint(formCaptureConstraint(new StartLevelConstraint(2))),
                new UpToBorder(new StepMove(LEFT)).addConstraint(formCaptureConstraint(new StartLevelConstraint(2))),
                new UpToBorder(new StepMove(BACKWARD)).addConstraint(formCaptureConstraint(new StartLevelConstraint(2))),
                new UpToBorder(new StepMove(RIGHT)).addConstraint(formCaptureConstraint(new StartLevelConstraint(2))),
                new UpToBorder(new StepMove(BACKWARD)).addConstraint(formCaptureConstraint(new StartLevelConstraint(2))),
                new UpToBorder(new StepMove(UP)).addConstraint(formCaptureConstraint()),
                new UpToBorder(new StepMove(DOWN)).addConstraint(formCaptureConstraint())
        );
    }
}
