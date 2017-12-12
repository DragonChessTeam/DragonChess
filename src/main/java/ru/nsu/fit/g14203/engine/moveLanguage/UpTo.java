package ru.nsu.fit.g14203.engine.moveLanguage;

import ru.nsu.fit.g14203.engine.api.utils.Dot3D;
import ru.nsu.fit.g14203.engine.moveLanguage.BasicMove;
import ru.nsu.fit.g14203.engine.moveLanguage.Move;

import java.util.ArrayList;
import java.util.List;

public class UpTo extends BasicMove {

    private int steps;
    private Move m;


    public UpTo(Move move, int maxMove) {
        steps = maxMove;
        m = move;
    }

    @Override
    protected List<Dot3D> doMove(Dot3D pos) {
        List<Dot3D> out = new ArrayList<>();

        List<Dot3D> stepIn;
        List<Dot3D> stepOut = new ArrayList<>();
        stepOut.add(pos);

        for (int step = 0; step < steps; step++) {
            stepIn = stepOut;
            stepOut = new ArrayList<>();
            for (Dot3D start : stepIn) {
                stepOut.addAll(m.getMovesFrom(start));
            }
            out.addAll(stepOut);
        }
        return out;
    }
}
