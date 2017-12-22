package ru.nsu.fit.g14203.engine.pieces;

import ru.nsu.fit.g14203.engine.api.Piece;
import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.engine.api.utils.Dot3D;
import ru.nsu.fit.g14203.engine.constraints.*;
import ru.nsu.fit.g14203.engine.movelanguage.Or;
import ru.nsu.fit.g14203.engine.movelanguage.moves.RoundMove;
import ru.nsu.fit.g14203.engine.movelanguage.moves.StepMove;

import java.util.List;

import static ru.nsu.fit.g14203.engine.api.utils.Dot3D.DOWN;
import static ru.nsu.fit.g14203.engine.api.utils.Dot3D.UP;
import static ru.nsu.fit.g14203.engine.constraints.ConstraintFormer.formCaptureConstraint;
import static ru.nsu.fit.g14203.engine.constraints.ConstraintFormer.formMoveConstraint;

public class TestPiece extends BasicPiece{
    private Dot3D previousPos;
    public TestPiece(Color c) {
        color = c;
        move = new Or(
                new RoundMove(),
                new StepMove(DOWN),
                new StepMove(UP)
        ).addConstraint(formMoveConstraint(new StartLevelConstraint(2)));
        capture = new RoundMove().addConstraint(formCaptureConstraint(new StartLevelConstraint(2)));
    }

    @Override
    public List<Dot3D> getAvailableMoves(Dot3D position, Piece[][][] boards) {
        List<Dot3D> out = super.getAvailableMoves(position, boards);
        if (position.z == 1) previousPos = position;
        else out.add(previousPos);
        return out;
    }

}
