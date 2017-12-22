package ru.nsu.fit.g14203.engine.pieces;

import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.engine.constraints.CheckConstraint;
import ru.nsu.fit.g14203.engine.constraints.StartLevelConstraint;
import ru.nsu.fit.g14203.engine.movelanguage.Or;
import ru.nsu.fit.g14203.engine.movelanguage.moves.StepMove;

import static ru.nsu.fit.g14203.engine.api.utils.Color.WHITE;
import static ru.nsu.fit.g14203.engine.api.utils.Dot3D.*;
import static ru.nsu.fit.g14203.engine.constraints.ConstraintFormer.formCaptureConstraint;
import static ru.nsu.fit.g14203.engine.constraints.ConstraintFormer.formMoveConstraint;

public class Warrior extends BasicPiece {
    public Warrior(Color c) {
        color = c;

        if (color == WHITE) {
            move = new StepMove(FORWARD).addConstraint(formMoveConstraint(new StartLevelConstraint(2))).addConstraint(new CheckConstraint());
            capture = new Or(
                    new StepMove(LEFT.sum(FORWARD)),
                    new StepMove(RIGHT.sum(FORWARD))
            ).addConstraint(formCaptureConstraint(new StartLevelConstraint(2))).addConstraint(new CheckConstraint());
        } else {
            move = new StepMove(BACKWARD).addConstraint(formMoveConstraint(new StartLevelConstraint(2))).addConstraint(new CheckConstraint());
            capture = new Or(
                    new StepMove(LEFT.sum(BACKWARD)),
                    new StepMove(RIGHT.sum(BACKWARD))
            ).addConstraint(formCaptureConstraint(new StartLevelConstraint(2))).addConstraint(new CheckConstraint());
        }
    }
}
