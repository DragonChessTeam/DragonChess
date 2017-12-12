package ru.nsu.fit.g14203.engine.moveLanguage.moves;

import ru.nsu.fit.g14203.engine.api.utils.Dot3D;
import ru.nsu.fit.g14203.engine.moveLanguage.BasicMove;
import ru.nsu.fit.g14203.engine.moveLanguage.Move;
import ru.nsu.fit.g14203.engine.moveLanguage.Or;

import java.util.ArrayList;
import java.util.List;

import static ru.nsu.fit.g14203.engine.api.utils.Dot3D.*;

public class RoundMove extends BasicMove {

    private Move move;

    public RoundMove() {
        List<Move> moves = new ArrayList<>();
        for (Dot3D a : new Dot3D[]{UP, DOWN, NONE})
            for (Dot3D b : new Dot3D[]{FORWARD, BACKWARD, NONE}) {
                if (a == b) continue;
                moves.add(new StepMove(a.sum(b)));
            }
        move = new Or((Move[]) moves.toArray());
    }


    public List<Dot3D> doMove(Dot3D pos) {
        return move.getMovesFrom(pos);
    }

}
