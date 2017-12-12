package ru.nsu.fit.g14203.engine.moveLanguage;

import ru.nsu.fit.g14203.engine.api.utils.Dot3D;

import java.util.ArrayList;
import java.util.List;

public class Or  extends BasicMove{
    private Move[] equalMoves;

    public Or(Move move1, Move move2) {
        equalMoves = new Move[2];
        equalMoves[0] = move1;
        equalMoves[1] = move2;
    }

    public Or(Move[] moves) {
        equalMoves = moves;
    }


    @Override
    protected List<Dot3D> doMove(Dot3D pos) {
        List<Dot3D> out = new ArrayList<>();
        for (Move m : equalMoves) {
            out.addAll(m.getMovesFrom(pos));
        }
        return out;
    }
}
