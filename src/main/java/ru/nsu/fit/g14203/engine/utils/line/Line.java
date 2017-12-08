package ru.nsu.fit.g14203.engine.utils.line;

import ru.nsu.fit.g14203.engine.utils.Dot3D;
import ru.nsu.fit.g14203.engine.utils.Playground;
import ru.nsu.fit.g14203.engine.utils.Way;

import java.util.List;

public interface Line {
    List<Dot3D> getAvailableMovesFrom(Dot3D pos, Playground boards);
    List<Dot3D> getAvailableCapturesFrom(Dot3D pos, Playground boards);
    List<Dot3D> getMoveCoveredBy(Way turn, Playground boards);
    List<Dot3D> getCaptureCoveredBy(Way turn, Playground boards);
}
