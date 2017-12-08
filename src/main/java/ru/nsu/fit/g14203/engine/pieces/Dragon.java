package ru.nsu.fit.g14203.engine.pieces;

import ru.nsu.fit.g14203.engine.utils.Color;
import ru.nsu.fit.g14203.engine.utils.Dot3D;
import ru.nsu.fit.g14203.engine.utils.Playground;
import ru.nsu.fit.g14203.engine.utils.Way;
import ru.nsu.fit.g14203.engine.constraints.Constraint;
import ru.nsu.fit.g14203.engine.utils.line.DirectLine;
import ru.nsu.fit.g14203.engine.utils.line.GLine;
import ru.nsu.fit.g14203.engine.utils.line.RoundLine;

public class Dragon extends BasicPiece {

    public Dragon(Color c) {
        color = c;
        moves.add(new RoundLine(3));
        moves.add(new DirectLine(3, new Dot3D(1,1,0), Integer.MAX_VALUE));
        moves.add(new DirectLine(3, new Dot3D(-1,1,0), Integer.MAX_VALUE));
        moves.add(new DirectLine(3, new Dot3D(1,-1,0), Integer.MAX_VALUE));
        moves.add(new DirectLine(3, new Dot3D(-1,-1,0), Integer.MAX_VALUE));
        captures.addAll(moves);
        captures.add(new DirectLine(3, new Dot3D(0,0, -1), 1));
        captures.add(new GLine(3, new DirectLine(3, new Dot3D(0,0,-1),1), new DirectLine(3, new Dot3D(1, 0, 0), 1)));
        captures.add(new GLine(3, new DirectLine(3, new Dot3D(0,0,-1),1), new DirectLine(3, new Dot3D(-1, 0, 0), 1)));
        captures.add(new GLine(3, new DirectLine(3, new Dot3D(0,0,-1),1), new DirectLine(3, new Dot3D(0, 1, 0), 1)));
        captures.add(new GLine(3, new DirectLine(3, new Dot3D(0,0,-1),1), new DirectLine(3, new Dot3D(0, -1, 0), 1)));
    }

}
