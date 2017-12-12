package ru.nsu.fit.g14203.engine.pieces;

import ru.nsu.fit.g14203.engine.utils.line.Sugar.TriagonalLine;

public class Thief extends BasicPiece {
    public Thief(Color c) {
        color = c;
        moves.add(new TriagonalLine(true, new int[]{2}, 0, true, true, true, true, Integer.MAX_VALUE));
        captures = moves;
    }
}
