package ru.nsu.fit.g14203.engine.pieces;

import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.engine.api.utils.Dot3D;
import ru.nsu.fit.g14203.engine.moveLanguage.Move;
import ru.nsu.fit.g14203.engine.moveLanguage.Or;
import ru.nsu.fit.g14203.engine.moveLanguage.constraints.LevelConstraint;
import ru.nsu.fit.g14203.engine.moveLanguage.moves.StepMove;

import java.util.ArrayList;
import java.util.List;

import static ru.nsu.fit.g14203.engine.api.utils.Dot3D.*;

public class Unicorn extends BasicPiece {
    public Unicorn(Color c) {
        color = c;
        List<Move> knight = new ArrayList<>();
        //Knight moves
        for (Dot3D a : new Dot3D[]{FORWARD, BACKWARD})
            for (Dot3D b : new Dot3D[]{LEFT, RIGHT}) {
                Dot3D end = a.sum(a).sum(b);
                Dot3D end2 = a.sum(b).sum(b);
                knight.add(new StepMove(end));
                knight.add(new StepMove(end2));
            }

        move = new Or((Move[]) knight.toArray()).updateConstraints(new LevelConstraint(2));
        capture = move;
    }
}
