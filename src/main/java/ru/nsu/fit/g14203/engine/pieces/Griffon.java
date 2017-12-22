package ru.nsu.fit.g14203.engine.pieces;

import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.engine.constraints.CheckConstraint;
import ru.nsu.fit.g14203.engine.constraints.StartLevelConstraint;
import ru.nsu.fit.g14203.engine.movelanguage.Or;
import ru.nsu.fit.g14203.engine.movelanguage.moves.StepMove;
import ru.nsu.fit.g14203.engine.movelanguage.moves.TriagonalMove;

import static ru.nsu.fit.g14203.engine.api.utils.Dot3D.*;
import static ru.nsu.fit.g14203.engine.constraints.ConstraintFormer.formCaptureConstraint;
import static ru.nsu.fit.g14203.engine.constraints.ConstraintFormer.formMoveConstraint;

public class Griffon extends BasicPiece{
    public Griffon(Color c) {
        color = c;
        move = new Or (
                new Or(
                        new StepMove(LEFT.sum(FORWARD).sum(LEFT).sum(FORWARD).sum(FORWARD)),
                        new StepMove(LEFT.sum(FORWARD).sum(LEFT).sum(FORWARD).sum(LEFT)),
                        new StepMove(RIGHT.sum(FORWARD).sum(RIGHT).sum(FORWARD).sum(FORWARD)),
                        new StepMove(RIGHT.sum(FORWARD).sum(RIGHT).sum(FORWARD).sum(RIGHT)),
                        new StepMove(RIGHT.sum(BACKWARD).sum(RIGHT).sum(BACKWARD).sum(BACKWARD)),
                        new StepMove(RIGHT.sum(BACKWARD).sum(RIGHT).sum(BACKWARD).sum(RIGHT)),
                        new StepMove(LEFT.sum(BACKWARD).sum(LEFT).sum(BACKWARD).sum(BACKWARD)),
                        new StepMove(LEFT.sum(BACKWARD).sum(LEFT).sum(BACKWARD).sum(LEFT)),
                        new StepMove(DOWN.sum(LEFT).sum(FORWARD)),
                        new StepMove(DOWN.sum(RIGHT).sum(FORWARD)),
                        new StepMove(DOWN.sum(LEFT).sum(BACKWARD)),
                        new StepMove(DOWN.sum(RIGHT).sum(BACKWARD))
                ).addConstraint(formMoveConstraint(new StartLevelConstraint(3))),
                new Or(
                        new StepMove(LEFT.sum(FORWARD)),
                        new StepMove(RIGHT.sum(FORWARD)),
                        new StepMove(LEFT.sum(BACKWARD)),
                        new StepMove(RIGHT.sum(BACKWARD)),
                        new StepMove(UP.sum(LEFT).sum(FORWARD)),
                        new StepMove(UP.sum(RIGHT).sum(FORWARD)),
                        new StepMove(UP.sum(LEFT).sum(BACKWARD)),
                        new StepMove(UP.sum(RIGHT).sum(BACKWARD))
                ).addConstraint(formMoveConstraint(new StartLevelConstraint(2)))
        ).addConstraint(new CheckConstraint());


        capture = new Or (
                new Or(
                        new StepMove(LEFT.sum(FORWARD).sum(LEFT).sum(FORWARD).sum(FORWARD)),
                        new StepMove(LEFT.sum(FORWARD).sum(LEFT).sum(FORWARD).sum(LEFT)),
                        new StepMove(RIGHT.sum(FORWARD).sum(RIGHT).sum(FORWARD).sum(FORWARD)),
                        new StepMove(RIGHT.sum(FORWARD).sum(RIGHT).sum(FORWARD).sum(RIGHT)),
                        new StepMove(RIGHT.sum(BACKWARD).sum(RIGHT).sum(BACKWARD).sum(BACKWARD)),
                        new StepMove(RIGHT.sum(BACKWARD).sum(RIGHT).sum(BACKWARD).sum(RIGHT)),
                        new StepMove(LEFT.sum(BACKWARD).sum(LEFT).sum(BACKWARD).sum(BACKWARD)),
                        new StepMove(LEFT.sum(BACKWARD).sum(LEFT).sum(BACKWARD).sum(LEFT)),
                        new TriagonalMove(false)
                ).addConstraint(formCaptureConstraint(new StartLevelConstraint(3))),
                new Or(
                        new StepMove(LEFT.sum(FORWARD)),
                        new StepMove(RIGHT.sum(FORWARD)),
                        new StepMove(LEFT.sum(BACKWARD)),
                        new StepMove(RIGHT.sum(BACKWARD)),
                        new TriagonalMove(true)
                ).addConstraint(formCaptureConstraint(new StartLevelConstraint(2)))
        ).addConstraint(new CheckConstraint());
    }
}
