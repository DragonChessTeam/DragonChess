package ru.nsu.fit.g14203.engine.moveLanguage.moves;

import ru.nsu.fit.g14203.engine.api.utils.Dot3D;
import ru.nsu.fit.g14203.engine.moveLanguage.BasicMove;
import ru.nsu.fit.g14203.engine.moveLanguage.Move;
import ru.nsu.fit.g14203.engine.moveLanguage.Or;

import java.util.ArrayList;
import java.util.List;

import static ru.nsu.fit.g14203.engine.api.utils.Dot3D.*;

public class FullDiagonalMove extends BasicMove{
    private Move move;

    public FullDiagonalMove() {
        List<Move> diags = new ArrayList<>();

        for (Dot3D start : new Dot3D[]{FORWARD, BACKWARD})
            for (Dot3D end : new Dot3D[]{LEFT, RIGHT}) {
                diags.add(new StepMove(start.sum(end)));
            }



        move = new Or((Move[]) diags.toArray());
    }

    @Override
    protected List<Dot3D> doMove(Dot3D pos) {
        return move.getMovesFrom(pos);
    }
}
