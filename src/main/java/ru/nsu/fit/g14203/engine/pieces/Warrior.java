package ru.nsu.fit.g14203.engine.pieces;

import ru.nsu.fit.g14203.engine.utils.Color;
import ru.nsu.fit.g14203.engine.utils.Dot3D;
import ru.nsu.fit.g14203.engine.utils.Playground;
import ru.nsu.fit.g14203.engine.utils.Way;
import ru.nsu.fit.g14203.engine.utils.line.DirectLine;
import ru.nsu.fit.g14203.engine.utils.line.Sugar.TriagonalLine;

public class Warrior extends BasicPiece {
    public Warrior(Color c) {
        color = c;
        moves.add(new DirectLine(2, new Dot3D(0,1,0),1));
        captures.add(new TriagonalLine(false, new int[]{2}, 0, true, true, false, false, 1));
    }

}
