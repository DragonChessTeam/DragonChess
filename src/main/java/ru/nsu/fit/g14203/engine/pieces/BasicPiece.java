package ru.nsu.fit.g14203.engine.pieces;

import org.omg.PortableInterceptor.INACTIVE;
import ru.nsu.fit.g14203.engine.constraints.Constraint;
import ru.nsu.fit.g14203.engine.utils.Color;
import ru.nsu.fit.g14203.engine.utils.Dot3D;
import ru.nsu.fit.g14203.engine.utils.Playground;
import ru.nsu.fit.g14203.engine.utils.Way;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract  class BasicPiece implements Piece{

    protected Color color;
    protected List<Constraint> constraints = new ArrayList<>();

    public Color getColor() {
        return color;
    }

    public void addConstraint(Constraint effect) {
        constraints.add(effect);
    }

    public void delConstraint(Constraint effect) {
        constraints.remove(effect);
    }
}
