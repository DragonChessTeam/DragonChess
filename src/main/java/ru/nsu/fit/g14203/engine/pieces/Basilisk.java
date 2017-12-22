package ru.nsu.fit.g14203.engine.pieces;

import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.engine.constraints.StartLevelConstraint;
import ru.nsu.fit.g14203.engine.moveLanguage.Or;
import ru.nsu.fit.g14203.engine.moveLanguage.moves.StepMove;

import static ru.nsu.fit.g14203.engine.api.utils.Color.WHITE;
import static ru.nsu.fit.g14203.engine.api.utils.Dot3D.*;
import static ru.nsu.fit.g14203.engine.constraints.ConstraintFormer.formCaptureConstraint;
import static ru.nsu.fit.g14203.engine.constraints.ConstraintFormer.formMoveConstraint;

public class Basilisk extends BasicPiece{
    public Basilisk(Color c) {
        color = c;
        if (color == WHITE) {
            move = new Or(
                    new StepMove(FORWARD),
                    new StepMove(FORWARD.sum(LEFT)),
                    new StepMove(FORWARD.sum(RIGHT)),
                    new StepMove(BACKWARD)
            ).addConstraint(formMoveConstraint(new StartLevelConstraint(1)));

            capture = new Or(
                    new StepMove(FORWARD),
                    new StepMove(FORWARD.sum(LEFT)),
                    new StepMove(FORWARD.sum(RIGHT))
            ).addConstraint(formCaptureConstraint(new StartLevelConstraint(1)));
        } else {
            move = new Or(
                    new StepMove(BACKWARD),
                    new StepMove(BACKWARD.sum(LEFT)),
                    new StepMove(BACKWARD.sum(RIGHT)),
                    new StepMove(FORWARD)
            ).addConstraint(formMoveConstraint(new StartLevelConstraint(1)));

            capture = new Or(
                    new StepMove(BACKWARD),
                    new StepMove(BACKWARD.sum(LEFT)),
                    new StepMove(BACKWARD.sum(RIGHT))
            ).addConstraint(formCaptureConstraint(new StartLevelConstraint(1)));
        }


    }
}
