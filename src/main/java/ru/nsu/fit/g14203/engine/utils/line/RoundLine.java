package ru.nsu.fit.g14203.engine.utils.line;

import ru.nsu.fit.g14203.engine.utils.Dot3D;
import ru.nsu.fit.g14203.engine.utils.Playground;
import ru.nsu.fit.g14203.engine.utils.Way;

import java.util.ArrayList;
import java.util.List;

public class RoundLine implements Line{

    List<Dot3D> offsets = new ArrayList<>();
    int workingLevel;

    public RoundLine(int level) {
        workingLevel = level;
        for (int offsetX = -1; offsetX <= 1; offsetX++)
            for (int offsetY = -1; offsetY <= 1; offsetY++) {
                offsets.add(new Dot3D(offsetX, offsetY, 0));
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
        //TODO: проверка вылета за пределы поля.
        if (workingLevel != -1 && turn.start.z != workingLevel) return null;

        List<Dot3D> way = new ArrayList<>();
        for (Dot3D offset : offsets) {
            Dot3D currDot = turn.start.sum(offset);
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
