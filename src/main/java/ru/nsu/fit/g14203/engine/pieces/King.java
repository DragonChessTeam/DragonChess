package ru.nsu.fit.g14203.engine.pieces;

public class King extends BasicPiece {

    public King(Color c) {
        color = c;
        moves.add(new RoundLine(2));
        moves.add(new DirectLine(-1, new Dot3D(0, 0, -1), 1));
        moves.add(new DirectLine(-1, new Dot3D(0, 0, 1), 1));
        captures = moves;
    }

}
