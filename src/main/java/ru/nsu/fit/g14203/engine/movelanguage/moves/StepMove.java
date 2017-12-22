package ru.nsu.fit.g14203.engine.movelanguage.moves;

import ru.nsu.fit.g14203.engine.api.Piece;
import ru.nsu.fit.g14203.engine.api.utils.Dot3D;
import ru.nsu.fit.g14203.engine.movelanguage.BasicMove;

import java.util.ArrayList;
import java.util.List;

public class StepMove extends BasicMove {
    private Dot3D direction;

    public StepMove(Dot3D dir) {
        direction = dir;
    }

    @Override
    protected List<Dot3D> doMove(Dot3D pos, Piece[][][] boards) {
        List<Dot3D> out = new ArrayList<>();
        out.add(pos.sum(direction));
        return out;
    }
}
