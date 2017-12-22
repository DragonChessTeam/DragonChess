package ru.nsu.fit.g14203.engine.pieces;

import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.engine.constraints.StartLevelConstraint;
import ru.nsu.fit.g14203.engine.movelanguage.Or;
import ru.nsu.fit.g14203.engine.movelanguage.moves.StepMove;

import static ru.nsu.fit.g14203.engine.api.utils.Dot3D.*;
import static ru.nsu.fit.g14203.engine.constraints.ConstraintFormer.formCaptureConstraint;
import static ru.nsu.fit.g14203.engine.constraints.ConstraintFormer.formMoveConstraint;

public class Unicorn extends BasicPiece {
    public Unicorn(Color c) {
        color = c;
        move = new Or(
                new StepMove(LEFT.sum(FORWARD).sum(FORWARD)),
                new StepMove(LEFT.sum(FORWARD).sum(LEFT)),
                new StepMove(RIGHT.sum(FORWARD).sum(FORWARD)),
                new StepMove(RIGHT.sum(FORWARD).sum(RIGHT)),
                new StepMove(LEFT.sum(BACKWARD).sum(BACKWARD)),
                new StepMove(LEFT.sum(BACKWARD).sum(LEFT)),
                new StepMove(RIGHT.sum(BACKWARD).sum(BACKWARD)),
                new StepMove(RIGHT.sum(BACKWARD).sum(RIGHT))
        ).addConstraint(formMoveConstraint(new StartLevelConstraint(2)));
        capture = new Or(
                new StepMove(LEFT.sum(FORWARD).sum(FORWARD)),
                new StepMove(LEFT.sum(FORWARD).sum(LEFT)),
                new StepMove(RIGHT.sum(FORWARD).sum(FORWARD)),
                new StepMove(RIGHT.sum(FORWARD).sum(RIGHT)),
                new StepMove(LEFT.sum(BACKWARD).sum(BACKWARD)),
                new StepMove(LEFT.sum(BACKWARD).sum(LEFT)),
                new StepMove(RIGHT.sum(BACKWARD).sum(BACKWARD)),
                new StepMove(RIGHT.sum(BACKWARD).sum(RIGHT))
        ).addConstraint(formCaptureConstraint(new StartLevelConstraint(2)));
    }
}
