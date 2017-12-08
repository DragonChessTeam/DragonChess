package ru.nsu.fit.g14203.engine.pieces;

import ru.nsu.fit.g14203.engine.utils.Color;
import ru.nsu.fit.g14203.engine.utils.Dot3D;
import ru.nsu.fit.g14203.engine.utils.Playground;
import ru.nsu.fit.g14203.engine.utils.Way;
import ru.nsu.fit.g14203.engine.utils.line.DirectLine;
import ru.nsu.fit.g14203.engine.utils.line.GLine;
import ru.nsu.fit.g14203.engine.utils.line.RoundLine;

public class Paladin extends BasicPiece {
    public Paladin(Color c) {
        moves.add(new RoundLine(2));
        moves.add(new GLine(2, new DirectLine(2, new Dot3D(1,1,0),1), new DirectLine(2, new Dot3D(1,0,0),1)));
        moves.add(new GLine(2, new DirectLine(2, new Dot3D(1,1,0),1), new DirectLine(2, new Dot3D(0,1,0),1)));
        moves.add(new GLine(2, new DirectLine(2, new Dot3D(-1,1,0),1), new DirectLine(2, new Dot3D(-1,0,0),1)));
        moves.add(new GLine(2, new DirectLine(2, new Dot3D(-1,1,0),1), new DirectLine(2, new Dot3D(0,1,0),1)));
        moves.add(new GLine(2, new DirectLine(2, new Dot3D(-1,-1,0),1), new DirectLine(2, new Dot3D(-1,0,0),1)));
        moves.add(new GLine(2, new DirectLine(2, new Dot3D(-1,-1,0),1), new DirectLine(2, new Dot3D(0,-1,0),1)));
        moves.add(new GLine(2, new DirectLine(2, new Dot3D(1,-1,0),1), new DirectLine(2, new Dot3D(1,0,0),1)));
        moves.add(new GLine(2, new DirectLine(2, new Dot3D(1,-1,0),1), new DirectLine(2, new Dot3D(0,-1,0),1)));

        moves.add(new RoundLine(1));
        moves.add(new RoundLine(3));

        captures.addAll(moves);

        moves.add(new GLine(1, new DirectLine(1, new Dot3D(0,0,1),1), new DirectLine(2, new Dot3D(1,0,0),2)));
        moves.add(new GLine(1, new DirectLine(1, new Dot3D(0,0,1),1), new DirectLine(2, new Dot3D(0,1,0),2)));
        moves.add(new GLine(1, new DirectLine(1, new Dot3D(0,0,1),1), new DirectLine(2, new Dot3D(-1,0,0),2)));
        moves.add(new GLine(1, new DirectLine(1, new Dot3D(0,0,1),1), new DirectLine(2, new Dot3D(0,-1,0),2)));
        moves.add(new GLine(1, new DirectLine(1, new Dot3D(0,0,1),2), new DirectLine(3, new Dot3D(1,0,0),1)));
        moves.add(new GLine(1, new DirectLine(1, new Dot3D(0,0,1),2), new DirectLine(3, new Dot3D(0,1,0),1)));
        moves.add(new GLine(1, new DirectLine(1, new Dot3D(0,0,1),2), new DirectLine(3, new Dot3D(-1,0,0),1)));
        moves.add(new GLine(1, new DirectLine(1, new Dot3D(0,0,1),2), new DirectLine(3, new Dot3D(0,-1,0),1)));

        moves.add(new GLine(3, new DirectLine(3, new Dot3D(0,0,-1),1), new DirectLine(2, new Dot3D(1,0,0),2)));
        moves.add(new GLine(3, new DirectLine(3, new Dot3D(0,0,-1),1), new DirectLine(2, new Dot3D(0,1,0),2)));
        moves.add(new GLine(3, new DirectLine(3, new Dot3D(0,0,-1),1), new DirectLine(2, new Dot3D(-1,0,0),2)));
        moves.add(new GLine(3, new DirectLine(3, new Dot3D(0,0,-1),1), new DirectLine(2, new Dot3D(0,-1,0),2)));
        moves.add(new GLine(3, new DirectLine(3, new Dot3D(0,0,-1),2), new DirectLine(1, new Dot3D(1,0,0),1)));
        moves.add(new GLine(3, new DirectLine(3, new Dot3D(0,0,-1),2), new DirectLine(1, new Dot3D(0,1,0),1)));
        moves.add(new GLine(3, new DirectLine(3, new Dot3D(0,0,-1),2), new DirectLine(1, new Dot3D(-1,0,0),1)));
        moves.add(new GLine(3, new DirectLine(3, new Dot3D(0,0,-1),2), new DirectLine(1, new Dot3D(0,-1,0),1)));

    }
}
