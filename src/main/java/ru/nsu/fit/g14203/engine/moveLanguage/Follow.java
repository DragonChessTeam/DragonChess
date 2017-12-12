package ru.nsu.fit.g14203.engine.moveLanguage;

import ru.nsu.fit.g14203.engine.api.utils.Dot3D;

import java.util.ArrayList;
import java.util.List;

public class Follow extends BasicMove {
    private Move m1;
    private Move m2;


    public Follow(Move first, Move second) {
        m1 = first;
        m2 = second;
    }

    @Override
    public List<Dot3D> doMove(Dot3D pos) {
        List<Dot3D> firstSteps = m1.getMovesFrom(pos);
        List<Dot3D> out = new ArrayList<>();
        for (Dot3D halfEnd : firstSteps) {
            out.addAll(m2.getMovesFrom(halfEnd));
        }

        return out;
    }
}
