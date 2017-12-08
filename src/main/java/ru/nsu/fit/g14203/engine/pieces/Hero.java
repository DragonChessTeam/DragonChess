package ru.nsu.fit.g14203.engine.pieces;

import ru.nsu.fit.g14203.engine.utils.Color;
import ru.nsu.fit.g14203.engine.utils.Dot3D;
import ru.nsu.fit.g14203.engine.utils.line.DirectLine;
import ru.nsu.fit.g14203.engine.utils.line.Sugar.TriagonalLine;

public class Hero extends BasicPiece {
    public Hero(Color c) {
        color = c;
        moves.add(new TriagonalLine(false, new int[]{2}, 0, true, true, true, true, 2));
        moves.add(new TriagonalLine(true,  new int[]{2}, 1, true, true, true, true, 2));
        moves.add(new TriagonalLine(true,  new int[]{2}, -1, true, true, true, true, 2));
        moves.add(new DirectLine(1, new Dot3D(0,0,1),1));
        moves.add(new DirectLine(3, new Dot3D(0,0,-1),1));
        captures.addAll(moves);

    }
}
