package ru.nsu.fit.g14203.engine.utils.line;

import ru.nsu.fit.g14203.engine.utils.Dot3D;
import ru.nsu.fit.g14203.engine.utils.Playground;
import ru.nsu.fit.g14203.engine.utils.Way;

import java.util.List;

public class GLine implements Line{
    public GLine(int level, Line oneHand, Line anotherHand) {

    }

    @Override
    public List<Dot3D> getAvailableMovesFrom(Dot3D pos, Playground boards) {
        return null;
    }

    @Override
    public List<Dot3D> getAvailableCapturesFrom(Dot3D pos, Playground boards) {
        return null;
    }

    @Override
    public List<Dot3D> getMoveCoveredBy(Way turn, Playground boards) {
        return null;
    }

    @Override
    public List<Dot3D> getCaptureCoveredBy(Way turn, Playground boards) {
        return null;
    }
}
