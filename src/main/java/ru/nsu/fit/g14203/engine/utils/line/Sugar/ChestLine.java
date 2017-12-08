package ru.nsu.fit.g14203.engine.utils.line.Sugar;

import ru.nsu.fit.g14203.engine.utils.Dot3D;
import ru.nsu.fit.g14203.engine.utils.Playground;
import ru.nsu.fit.g14203.engine.utils.Way;
import ru.nsu.fit.g14203.engine.utils.line.DirectLine;
import ru.nsu.fit.g14203.engine.utils.line.Line;

import java.util.ArrayList;
import java.util.List;

public class ChestLine implements Line{
    List<Line> moveLines = new ArrayList<>();
    int[] workingLevels;
    boolean isBlockable;

    public ChestLine(boolean blockable, int[] levels, boolean u, boolean d, boolean r, boolean l, int numSteps) {
        workingLevels = levels;
        isBlockable = blockable;

        for (int level : levels) {
            if (r) moveLines.add(new DirectLine(level, new Dot3D(1,0 ,0), numSteps));
            if (u) moveLines.add(new DirectLine(level, new Dot3D(0,1, 0), numSteps));
            if (l) moveLines.add(new DirectLine(level, new Dot3D(-1,0,0), numSteps));
            if (d) moveLines.add(new DirectLine(level, new Dot3D(0,-1,0), numSteps));
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
