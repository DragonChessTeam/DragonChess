package ru.nsu.fit.g14203.engine.pieces;

import ru.nsu.fit.g14203.engine.utils.line.Sugar.TriagonalLine;

public class Sylph extends BasicPiece {

    public Sylph(Color c, Dot3D[] startPoints) {
        color = c;
        moves.add(new TriagonalLine(false, new int[]{3}, 0, true, true, false, false, 1));
        moves.add(new DirectLine(2, new Dot3D(0,0,1),1));
        for (Dot3D start : startPoints)
            moves.add(new MomentalLine(2, start));
        captures.add(new DirectLine(3, new Dot3D(0,1,0),1));
        captures.add(new DirectLine(3, new Dot3D(0,0,-1),1));
    }
}
