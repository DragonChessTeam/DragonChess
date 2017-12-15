package ru.nsu.fit.g14203.engine.moveLanguage;

import ru.nsu.fit.g14203.engine.api.Piece;
import ru.nsu.fit.g14203.engine.api.utils.Dot3D;

import java.util.ArrayList;
import java.util.List;

public class UpToBorder extends BasicMove {
    private Piece[][][] pl;
    private Move m;


    public UpToBorder(Piece[][][] playground, Move move) {
        pl = playground;
        m = move;
    }

    @Override
    public List<Dot3D> doMove(Dot3D pos) {

        List<Dot3D> out = new ArrayList<>();

        List<Dot3D> stepIn;
        List<Dot3D> stepOut = new ArrayList<>();
        stepOut.add(pos);

        boolean stepIsGood = true;
        while (stepIsGood) {
            stepIn = stepOut;
            stepOut = new ArrayList<>();
            stepIsGood = false;
            for (Dot3D start : stepIn) {
                if (start.x >= 0 && start.x < pl.length && start.y >= 0 && start.y < pl[0].length && start.z >= 0 && start.z < pl[0][0].length) {
                    stepOut.addAll(m.getMovesFrom(start));
                    stepIsGood = true;
                }
            }
            out.addAll(stepOut);
        }

        return out;
    }
}
