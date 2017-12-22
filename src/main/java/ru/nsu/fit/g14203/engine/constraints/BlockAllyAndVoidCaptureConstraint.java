package ru.nsu.fit.g14203.engine.constraints;

import ru.nsu.fit.g14203.engine.api.Piece;
import ru.nsu.fit.g14203.engine.api.utils.Dot3D;

import java.util.ArrayList;
import java.util.List;

public class BlockAllyAndVoidCaptureConstraint implements Constraint {



    @Override
    public void changeMove(List<Dot3D> ends, Dot3D start, Piece[][][] boards) {
        List<Dot3D> out = new ArrayList<>();

        for (Dot3D end : ends) {
            if (boards[end.x][end.y][end.z] != null && boards[end.x][end.y][end.z].getColor() != boards[start.x][start.y][start.z].getColor())
                out.add(end);
        }
        ends.clear();
        ends.addAll(out);
    }

    @Override
    public void changeCapture(List<Dot3D> ends, Dot3D start, Piece[][][] boards) {

    }
}
