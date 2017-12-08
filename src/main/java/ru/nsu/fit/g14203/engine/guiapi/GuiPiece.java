package ru.nsu.fit.g14203.engine.guiapi;

import ru.nsu.fit.g14203.engine.utils.Color;
import ru.nsu.fit.g14203.engine.utils.Dot3D;
import ru.nsu.fit.g14203.engine.utils.Playground;
import ru.nsu.fit.g14203.engine.utils.Way;
import ru.nsu.fit.g14203.engine.constraints.Constraint;

public interface GuiPiece {

    public abstract Color getColor();

    public abstract Dot3D[] getAvailableMoves(Dot3D position, Playground boards);

    public abstract Dot3D[] getAvailableCaptures(Dot3D position, Playground boards);

}
