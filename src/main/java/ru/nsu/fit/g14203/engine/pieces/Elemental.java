package ru.nsu.fit.g14203.engine.pieces;

import ru.nsu.fit.g14203.engine.utils.Color;
import ru.nsu.fit.g14203.engine.utils.Dot3D;
import ru.nsu.fit.g14203.engine.utils.Playground;
import ru.nsu.fit.g14203.engine.utils.Way;
import ru.nsu.fit.g14203.engine.utils.line.DirectLine;
import ru.nsu.fit.g14203.engine.utils.line.GLine;

public class Elemental extends BasicPiece {

    public Elemental(Color c) {
        color = c;
        moves.add(new DirectLine(1, new Dot3D(1,0,0), 2));
        moves.add(new DirectLine(1, new Dot3D(-1,0,0), 2));
        moves.add(new DirectLine(1, new Dot3D(0,1,0), 2));
        moves.add(new DirectLine(1, new Dot3D(0,-1,0), 2));
        captures.addAll(moves);
        moves.add(new DirectLine(1, new Dot3D(1,1,0), 1));
        moves.add(new DirectLine(1, new Dot3D(-1,1,0), 1));
        moves.add(new DirectLine(1, new Dot3D(-1,-1,0), 1));
        moves.add(new DirectLine(1, new Dot3D(1,-1,0), 1));
        moves.add(new GLine(2, new DirectLine(2, new Dot3D(0,0,-1),1), new DirectLine(1, new Dot3D(1,0,0),1)));
        moves.add(new GLine(2, new DirectLine(2, new Dot3D(0,0,-1),1), new DirectLine(1, new Dot3D(-1,0,0),1)));
        moves.add(new GLine(2, new DirectLine(2, new Dot3D(0,0,-1),1), new DirectLine(1, new Dot3D(0,1,0),1)));
        moves.add(new GLine(2, new DirectLine(2, new Dot3D(0,0,-1),1), new DirectLine(1, new Dot3D(0,-1,0),1)));
        captures.add(new GLine(1, new DirectLine(1, new Dot3D(0,0,1),1), new DirectLine(2, new Dot3D(1,0,0),1)));
        captures.add(new GLine(1, new DirectLine(1, new Dot3D(0,0,1),1), new DirectLine(2, new Dot3D(-1,0,0),1)));
        captures.add(new GLine(1, new DirectLine(1, new Dot3D(0,0,1),1), new DirectLine(2, new Dot3D(0,-1,0),1)));
        captures.add(new GLine(1, new DirectLine(1, new Dot3D(0,0,1),1), new DirectLine(2, new Dot3D(0,1,0),1)));
    }

}
