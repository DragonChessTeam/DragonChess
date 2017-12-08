package ru.nsu.fit.g14203.engine.pieces;

import ru.nsu.fit.g14203.engine.constraints.Constraint;
import ru.nsu.fit.g14203.engine.guiapi.GuiPiece;
import ru.nsu.fit.g14203.engine.utils.*;
import ru.nsu.fit.g14203.engine.utils.line.Line;

import java.util.*;

public abstract  class BasicPiece implements GuiPiece {

    protected Color color;
    protected List<Constraint> constraints = new ArrayList<>();

    protected List<Line> moves = new ArrayList<>();
    protected List<Line> captures;


    public Color getColor() {
        return color;
    }

    public List<Dot3D> getMovePath(Way turn, Playground boards) {
        for (Line moveSteps : moves) {
            List<Dot3D> coveredMoveDots = moveSteps.getMoveCoveredBy(turn, boards);
            if (coveredMoveDots != null) return coveredMoveDots;
        }
        return null;
    }

    public List<Dot3D> getCapturePath(Way turn, Playground boards) {
        for (Line moveSteps : captures) {
            List<Dot3D> coveredCaptureDots = moveSteps.getCaptureCoveredBy(turn, boards);
            if (coveredCaptureDots != null) return coveredCaptureDots;
        }
        return null;
    }

    @Override
    public Dot3D[] getAvailableMoves(Dot3D position, Playground boards) {
        List<Dot3D> out = new ArrayList<>();
        for (Line moveSteps : moves) {
            List<Dot3D> coveredMoveDots = moveSteps.getAvailableMovesFrom(position, boards);
            if (coveredMoveDots != null) out.addAll(coveredMoveDots);
        }

        return (Dot3D[]) out.toArray();
    }

    @Override
    public Dot3D[] getAvailableCaptures(Dot3D position, Playground boards) {
        List<Dot3D> out = new ArrayList<>();
        for (Line moveSteps : captures) {
            List<Dot3D> coveredMoveDots = moveSteps.getAvailableCapturesFrom(position, boards);
            if (coveredMoveDots != null) out.addAll(coveredMoveDots);
        }

        return (Dot3D[]) out.toArray();
    }


    public void addConstraint(Constraint effect) {
        constraints.add(effect);
    }

    public void delConstraint(Constraint effect) {
        constraints.remove(effect);
    }
}
