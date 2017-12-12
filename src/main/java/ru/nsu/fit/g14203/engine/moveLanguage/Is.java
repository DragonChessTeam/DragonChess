package ru.nsu.fit.g14203.engine.moveLanguage;

import ru.nsu.fit.g14203.engine.api.utils.Dot3D;
import ru.nsu.fit.g14203.engine.moveLanguage.BasicMove;
import ru.nsu.fit.g14203.engine.moveLanguage.Move;

import java.util.ArrayList;
import java.util.List;

public class Is extends BasicMove {

    private int steps;
    private Move m;

    public Is(Move move, int numSteps) {
        m = move;
        steps = numSteps;
    }

    @Override
    public List<Dot3D> doMove(Dot3D pos) {
        List<Dot3D> stepIn;
        List<Dot3D> stepOut = new ArrayList<>();
        stepOut.add(pos);

        for (int step = 0; step < steps; step++) {
            stepIn = stepOut;
            stepOut = new ArrayList<>();
            for (Dot3D start : stepIn) {
                stepOut.addAll(m.getMovesFrom(start));
            }
        }

        return stepOut;
    }
}
