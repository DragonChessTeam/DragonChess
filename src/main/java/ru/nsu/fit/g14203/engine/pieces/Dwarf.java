package ru.nsu.fit.g14203.engine.pieces;

import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.engine.constraints.StartLevelConstraint;
import ru.nsu.fit.g14203.engine.movelanguage.Or;
import ru.nsu.fit.g14203.engine.movelanguage.moves.StepMove;

import static ru.nsu.fit.g14203.engine.api.utils.Color.WHITE;
import static ru.nsu.fit.g14203.engine.api.utils.Dot3D.*;
import static ru.nsu.fit.g14203.engine.constraints.ConstraintFormer.formCaptureConstraint;
import static ru.nsu.fit.g14203.engine.constraints.ConstraintFormer.formMoveConstraint;

public class Dwarf extends BasicPiece{
    public Dwarf(Color c) {
        color = c;

        if (color == WHITE) {
            move = new Or(
                    new Or(
                            new StepMove(FORWARD),
                            new StepMove(RIGHT),
                            new StepMove(LEFT)
                    ).addConstraint(formMoveConstraint(new StartLevelConstraint(1,2))),
                    new StepMove(DOWN).addConstraint(formMoveConstraint(new StartLevelConstraint(2)))
            );
            capture = new Or (
                    new StepMove(LEFT.sum(FORWARD)),
                    new StepMove(RIGHT.sum(FORWARD)),
                    new StepMove(UP)
            ).addConstraint(formCaptureConstraint(new StartLevelConstraint(1)));
        }
        else {
            move = new Or(
                    new Or(
                            new StepMove(BACKWARD),
                            new StepMove(RIGHT),
                            new StepMove(LEFT)
                    ).addConstraint(formMoveConstraint(new StartLevelConstraint(1,2))),
                    new StepMove(DOWN).addConstraint(formMoveConstraint(new StartLevelConstraint(2)))
            );
            capture = new Or (
                    new StepMove(LEFT.sum(BACKWARD)),
                    new StepMove(RIGHT.sum(BACKWARD)),
                    new StepMove(UP)
            ).addConstraint(formCaptureConstraint(new StartLevelConstraint(1)));
        }



    }
}
