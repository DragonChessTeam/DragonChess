package ru.nsu.fit.g14203.engine.pieces;

import ru.nsu.fit.g14203.engine.utils.Color;
import ru.nsu.fit.g14203.engine.utils.Dot3D;
import ru.nsu.fit.g14203.engine.utils.Playground;
import ru.nsu.fit.g14203.engine.utils.Way;
import ru.nsu.fit.g14203.engine.utils.line.DirectLine;

public class Dwarf extends BasicPiece {


    public Dwarf(Color c) {
        color = c;
        //On level 1
        moves.add(new DirectLine(1, new Dot3D(1,0,0), 1));
        moves.add(new DirectLine(1, new Dot3D(-1,0,0), 1));
        moves.add(new DirectLine(1, new Dot3D(0,1,0), 1));
        captures.add(new DirectLine(1, new Dot3D(1,1, 0), 1));
        captures.add(new DirectLine(1, new Dot3D(-1,1, 0), 1));
        captures.add(new DirectLine(1, new Dot3D(0,0, 1), 1));
        //On level 2
        moves.add(new DirectLine(2, new Dot3D(1,0,0), 1));
        moves.add(new DirectLine(2, new Dot3D(-1,0,0), 1));
        moves.add(new DirectLine(2, new Dot3D(0,1,0), 1));
        moves.add(new DirectLine(2, new Dot3D(0,0,-1), 1));
        captures.add(new DirectLine(2, new Dot3D(1,1, 0), 1));
        captures.add(new DirectLine(2, new Dot3D(-1,1, 0), 1));
    }

}
