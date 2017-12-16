package ru.nsu.fit.g14203.engine.moveLanguage.moves;

import ru.nsu.fit.g14203.engine.api.Piece;
import ru.nsu.fit.g14203.engine.api.utils.Dot3D;
import ru.nsu.fit.g14203.engine.moveLanguage.BasicMove;

import java.util.Arrays;
import java.util.List;

public class StepMove extends BasicMove {
    private Dot3D direction;

    public StepMove(Dot3D dir) {
        direction = dir;
    }

    @Override
    protected List<Dot3D> doMove(Dot3D pos, Piece[][][] boards) {
        return Arrays.asList(pos.sum(direction));
    }
}
