package ru.nsu.fit.g14203.engine.pieces;

import ru.nsu.fit.g14203.engine.utils.Color;
import ru.nsu.fit.g14203.engine.utils.Dot3D;
import ru.nsu.fit.g14203.engine.utils.Playground;
import ru.nsu.fit.g14203.engine.utils.Way;
import ru.nsu.fit.g14203.engine.utils.line.DirectLine;
import ru.nsu.fit.g14203.engine.utils.line.RoundLine;

import java.util.ArrayList;
import java.util.List;

public class Cleric extends BasicPiece {


    public Cleric(Color c) {
        color = c;
        moves.add(new RoundLine(-1));
        moves.add(new DirectLine(-1, new Dot3D(0, 0, -1), 1));
        moves.add(new DirectLine(-1, new Dot3D(0, 0, 1), 1));
        captures = moves;
    }

}
