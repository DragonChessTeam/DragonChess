package ru.nsu.fit.g14203.engine.moveLanguage.moves;

import ru.nsu.fit.g14203.engine.api.utils.Dot3D;
import ru.nsu.fit.g14203.engine.moveLanguage.BasicMove;
import ru.nsu.fit.g14203.engine.moveLanguage.Move;
import ru.nsu.fit.g14203.engine.moveLanguage.Or;

import java.util.ArrayList;
import java.util.List;

import static ru.nsu.fit.g14203.engine.api.utils.Dot3D.*;

public class TriagonalMove extends BasicMove{
    private Move move;

    public TriagonalMove(Dot3D shift) {
        List<Move> triags = new ArrayList<>();

        for (Dot3D start : new Dot3D[]{FORWARD, BACKWARD})
            for (Dot3D end : new Dot3D[]{LEFT, RIGHT}) {
                triags.add(new StepMove(start.sum(end).sum(shift)));
            }



        move = new Or((Move[]) triags.toArray());
    }

    @Override
    protected List<Dot3D> doMove(Dot3D pos) {
        return move.getMovesFrom(pos);
    }

}
