package ru.nsu.fit.g14203.engine.movelanguage.moves;

import ru.nsu.fit.g14203.engine.api.Piece;
import ru.nsu.fit.g14203.engine.api.utils.Dot3D;
import ru.nsu.fit.g14203.engine.movelanguage.BasicMove;
import ru.nsu.fit.g14203.engine.movelanguage.Move;
import ru.nsu.fit.g14203.engine.movelanguage.Or;

import java.util.List;

import static ru.nsu.fit.g14203.engine.api.utils.Dot3D.*;
import static ru.nsu.fit.g14203.engine.api.utils.Dot3D.BACKWARD;

public class TriagonalMove extends BasicMove {
    private Move move;

    public TriagonalMove(boolean isUp) {

        if (isUp) {
            move = new Or(
                    new StepMove(UP.sum(LEFT).sum(FORWARD)),
                    new StepMove(UP.sum(RIGHT).sum(FORWARD)),
                    new StepMove(UP.sum(LEFT).sum(BACKWARD)),
                    new StepMove(UP.sum(RIGHT).sum(BACKWARD))
            );

        } else {
            move = new Or(
                    new StepMove(DOWN.sum(LEFT).sum(FORWARD)),
                    new StepMove(DOWN.sum(RIGHT).sum(FORWARD)),
                    new StepMove(DOWN.sum(LEFT).sum(BACKWARD)),
                    new StepMove(DOWN.sum(RIGHT).sum(BACKWARD))
            );
        }
    }

    @Override
    protected List<Dot3D> doMove(Dot3D pos, Piece[][][] boards) {
        return move.getMovesFrom(pos, boards);
    }
}
