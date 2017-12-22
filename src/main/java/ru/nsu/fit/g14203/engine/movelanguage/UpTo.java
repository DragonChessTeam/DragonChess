package ru.nsu.fit.g14203.engine.movelanguage;

import ru.nsu.fit.g14203.engine.api.Piece;
import ru.nsu.fit.g14203.engine.api.utils.Dot3D;

import java.util.ArrayList;
import java.util.List;

public class UpTo extends BasicMove {

    private int steps;
    private Move m;


    public UpTo(int maxMove, Move move) {
        steps = maxMove;
        m = move;
    }

    @Override
    protected List<Dot3D> doMove(Dot3D pos, Piece[][][] boards) {
        List<Dot3D> out = new ArrayList<>();

        List<Dot3D> stepIn;
        List<Dot3D> stepOut = new ArrayList<>();
        stepOut.add(pos);

        for (int step = 0; step < steps; step++) {
            stepIn = stepOut;
            stepOut = new ArrayList<>();
            for (Dot3D start : stepIn) {
                stepOut.addAll(m.getMovesFrom(start, boards));
            }
            out.addAll(stepOut);
        }
        return out;
    }
}
