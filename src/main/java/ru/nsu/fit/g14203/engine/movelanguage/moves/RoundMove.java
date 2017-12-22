package ru.nsu.fit.g14203.engine.movelanguage.moves;

import ru.nsu.fit.g14203.engine.api.Piece;
import ru.nsu.fit.g14203.engine.api.utils.Dot3D;
import ru.nsu.fit.g14203.engine.movelanguage.BasicMove;
import ru.nsu.fit.g14203.engine.movelanguage.Move;
import ru.nsu.fit.g14203.engine.movelanguage.Or;

import java.util.ArrayList;
import java.util.List;

import static ru.nsu.fit.g14203.engine.api.utils.Dot3D.*;

public class RoundMove extends BasicMove {

    private Move move;

    public RoundMove() {
        List<Move> moves = new ArrayList<>();
        for (Dot3D a : new Dot3D[]{LEFT, RIGHT, NONE})
            for (Dot3D b : new Dot3D[]{FORWARD, BACKWARD, NONE}) {
                if (a == b) continue;
                moves.add(new StepMove(a.sum(b)));
            }
        Move[] moveArr = new Move[moves.size()];
        moves.toArray(moveArr);
        move = new Or(moveArr);
    }


    public List<Dot3D> doMove(Dot3D pos, Piece[][][] boards) {
        return move.getMovesFrom(pos, boards);

    }

}
