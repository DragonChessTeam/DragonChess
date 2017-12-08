package ru.nsu.fit.g14203.engine.pieces;

import ru.nsu.fit.g14203.engine.utils.Color;
import ru.nsu.fit.g14203.engine.utils.Dot3D;
import ru.nsu.fit.g14203.engine.utils.Playground;
import ru.nsu.fit.g14203.engine.utils.Way;
import ru.nsu.fit.g14203.engine.utils.line.DirectLine;
import ru.nsu.fit.g14203.engine.utils.line.Sugar.ChestLine;
import ru.nsu.fit.g14203.engine.utils.line.Sugar.TriagonalLine;

import java.net.Inet4Address;

public class Mage extends BasicPiece {


    public Mage(Color c) {
        color = c;
        moves.add(new TriagonalLine(true, new int[]{2}, 0, true, true, true, true, Integer.MAX_VALUE));
        moves.add(new ChestLine(true, new int[]{2}, true, true, true, true, Integer.MAX_VALUE));
        captures.addAll(moves);
        moves.add(new ChestLine(true, new int[]{1,3}, true, true, true, true, 1));
        moves.add(new DirectLine(-1, new Dot3D(0,0,1),2));
        moves.add(new DirectLine(-1, new Dot3D(0,0,-1),2));
        captures.add(new DirectLine(-1, new Dot3D(0,0,1),2));
        captures.add(new DirectLine(-1, new Dot3D(0,0,-1),2));
    }
}
