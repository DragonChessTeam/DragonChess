package ru.nsu.fit.g14203.engine.utils.line.Sugar;

import ru.nsu.fit.g14203.engine.utils.Dot3D;
import ru.nsu.fit.g14203.engine.utils.Playground;
import ru.nsu.fit.g14203.engine.utils.Way;
import ru.nsu.fit.g14203.engine.utils.line.DirectLine;
import ru.nsu.fit.g14203.engine.utils.line.Line;

import java.util.ArrayList;
import java.util.List;

public class TriagonalLine implements Line {
    List<Line> moveLines = new ArrayList<>();
    int[] workingLevels;
    boolean isBlockable;
    public TriagonalLine(int[] levels, int upOrDown, boolean ur, boolean ul, boolean dr, boolean dl, int numSteps) {
        this(true, levels, upOrDown, ur, ul, dr, dl, numSteps);
    }

    public TriagonalLine(boolean blockable, int[] levels, int upOrDown, boolean ur, boolean ul, boolean dr, boolean dl, int numSteps) {
        workingLevels = levels;
        isBlockable = blockable;

        for (int level : levels) {
            moveLines.add(new DirectLine(level, new Dot3D(1,1,upOrDown), numSteps));
            moveLines.add(new DirectLine(level, new Dot3D(1,-1,upOrDown), numSteps));
            moveLines.add(new DirectLine(level, new Dot3D(-1,-1,upOrDown), numSteps));
            moveLines.add(new DirectLine(level, new Dot3D(-1,1,upOrDown), numSteps));
        }
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
