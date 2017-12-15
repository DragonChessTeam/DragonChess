package ru.nsu.fit.g14203.engine.constraints;

import ru.nsu.fit.g14203.engine.api.utils.Way;
import ru.nsu.fit.g14203.engine.utils.ChessChecker;
import ru.nsu.fit.g14203.engine.api.Piece;
import ru.nsu.fit.g14203.engine.api.utils.Dot3D;

import java.util.ArrayList;
import java.util.List;

public class CheckConstraint implements Constraint{

    private Piece[][][] pg;

    public CheckConstraint(Piece[][][] board) {
        pg = board;
    }

    @Override
    public void changeMove(List<Dot3D> ends, Dot3D start) {
        List<Dot3D> out = new ArrayList<>();
        for (Dot3D end : ends) {
            if (!ChessChecker.isCheckAfterTurn(new Way(start, end), pg[start.x][start.y][start.z].getColor(), pg))
                out.add(end);
        }
        ends.clear();
        ends.addAll(out);
    }

    @Override
    public void changeCapture(List<Dot3D> ends, Dot3D start) {
        List<Dot3D> out = new ArrayList<>();
        for (Dot3D end : ends) {
            if (!ChessChecker.isCheckAfterTurn(new Way(start, end), pg[start.x][start.y][start.z].getColor(), pg))
                out.add(end);
        }
        ends.clear();
        ends.addAll(out);
    }
}
