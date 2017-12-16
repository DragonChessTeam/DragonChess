package ru.nsu.fit.g14203.engine.moveLanguage.moves;

import ru.nsu.fit.g14203.engine.api.Piece;
import ru.nsu.fit.g14203.engine.api.utils.Dot3D;
import ru.nsu.fit.g14203.engine.moveLanguage.BasicMove;

import java.util.ArrayList;
import java.util.List;

public class AbsoluteMove extends BasicMove{
    private Dot3D destination;

    public AbsoluteMove(Dot3D dst) {
        destination = dst;
    }

    @Override
    protected List<Dot3D> doMove(Dot3D pos, Piece[][][] boards) {
        List<Dot3D> out = new ArrayList<>();
        Dot3D outDot = new Dot3D();
        outDot.x = destination.x;
        outDot.y = destination.y;
        outDot.z = destination.z;
        out.add(outDot);
        return out;
    }
}
