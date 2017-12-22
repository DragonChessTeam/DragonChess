package ru.nsu.fit.g14203.engine.pieces;

import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.engine.movelanguage.Or;
import ru.nsu.fit.g14203.engine.movelanguage.moves.RoundMove;
import ru.nsu.fit.g14203.engine.movelanguage.moves.StepMove;

import static ru.nsu.fit.g14203.engine.api.utils.Dot3D.DOWN;
import static ru.nsu.fit.g14203.engine.api.utils.Dot3D.UP;
import static ru.nsu.fit.g14203.engine.constraints.ConstraintFormer.formCaptureConstraint;
import static ru.nsu.fit.g14203.engine.constraints.ConstraintFormer.formMoveConstraint;

public class Cleric extends BasicPiece {
    public Cleric(Color c) {
        color = c;
        move = new Or(
                new RoundMove().addConstraint(formMoveConstraint()),
                new StepMove(UP).addConstraint(formMoveConstraint()),
                new StepMove(DOWN).addConstraint(formMoveConstraint())
        );
        capture = new Or(
                new RoundMove().addConstraint(formCaptureConstraint()),
                new StepMove(UP).addConstraint(formCaptureConstraint()),
                new StepMove(DOWN).addConstraint(formCaptureConstraint())
        );


    }
}
