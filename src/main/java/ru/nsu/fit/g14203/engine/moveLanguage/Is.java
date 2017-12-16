package ru.nsu.fit.g14203.engine.moveLanguage;

import ru.nsu.fit.g14203.engine.api.Piece;
import ru.nsu.fit.g14203.engine.api.utils.Dot3D;

import java.util.ArrayList;
import java.util.List;

public class Is extends BasicMove {

    private int steps;
    private Move m;

    public Is(int numSteps, Move move) {
        m = move;
        steps = numSteps;
    }

    @Override
    public List<Dot3D> doMove(Dot3D pos, Piece[][][] boards) {
        List<Dot3D> stepIn;
        List<Dot3D> stepOut = new ArrayList<>();
        stepOut.add(pos);

        for (int step = 0; step < steps; step++) {
            stepIn = stepOut;
            stepOut = new ArrayList<>();
            for (Dot3D start : stepIn) {
                stepOut.addAll(m.getMovesFrom(start, boards));
            }
        }

        return stepOut;
    }
}
