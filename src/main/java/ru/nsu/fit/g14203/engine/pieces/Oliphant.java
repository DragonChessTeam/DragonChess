package ru.nsu.fit.g14203.engine.pieces;

import ru.nsu.fit.g14203.engine.utils.Color;
import ru.nsu.fit.g14203.engine.utils.Dot3D;
import ru.nsu.fit.g14203.engine.utils.Playground;
import ru.nsu.fit.g14203.engine.utils.Way;
import ru.nsu.fit.g14203.engine.utils.line.Sugar.ChestLine;

public class Oliphant extends BasicPiece {


    public Oliphant(Color c) {
        color = c;
        moves.add(new ChestLine(true, new int[]{2}, true, true, true, true, Integer.MAX_VALUE));
        captures = moves;
    }

}
