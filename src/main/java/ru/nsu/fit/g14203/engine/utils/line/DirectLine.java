package ru.nsu.fit.g14203.engine.utils.line;

import ru.nsu.fit.g14203.engine.utils.Dot3D;
import ru.nsu.fit.g14203.engine.utils.Playground;
import ru.nsu.fit.g14203.engine.utils.Way;

import java.util.ArrayList;
import java.util.List;

public class DirectLine implements Line {
    int workingLevel;
    Dot3D step;
    int numSteps;
    public DirectLine(int level, Dot3D step, int numSteps) {
        this.workingLevel = level;
        this.step = step;
        this.numSteps = numSteps;
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
        //TODO: проверка вылета за пределы поля.
        if (workingLevel != -1 && turn.start.z != workingLevel) return null;
        List<Dot3D> way = new ArrayList<>();

        Dot3D currDot = turn.start;

        for (int counter = 0; counter < numSteps; counter++) {
            currDot = currDot.sum(step);
            way.add(currDot);
            if (currDot.equals(turn.end)) return way;
        }

        return null;
    }

    @Override
    public List<Dot3D> getCaptureCoveredBy(Way turn, Playground boards) {
        return null;
    }
}
