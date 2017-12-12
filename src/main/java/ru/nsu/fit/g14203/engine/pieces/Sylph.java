package ru.nsu.fit.g14203.engine.pieces;

import ru.nsu.fit.g14203.engine.api.Piece;
import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.engine.api.utils.Dot3D;
import ru.nsu.fit.g14203.engine.moveLanguage.Move;
import ru.nsu.fit.g14203.engine.moveLanguage.Or;
import ru.nsu.fit.g14203.engine.moveLanguage.constraints.LevelConstraint;
import ru.nsu.fit.g14203.engine.moveLanguage.moves.AbsoluteMove;
import ru.nsu.fit.g14203.engine.moveLanguage.moves.StepMove;

import java.util.ArrayList;
import java.util.List;

import static ru.nsu.fit.g14203.engine.api.utils.Dot3D.*;

public class Sylph extends BasicPiece {

    public Sylph(Color c, Piece[][][] pg) {
        color = c;
        List<Move> startMoves = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            startMoves.add(new AbsoluteMove(new Dot3D(i*2, 2, 3)));
        }

        move = new Or (
                    new Or(
                    new StepMove(FORWARD.sum(LEFT)),
                    new StepMove(FORWARD.sum(RIGHT))
                ).updateConstraints(new LevelConstraint(3)),
                new StepMove(UP).updateConstraints(new LevelConstraint(2)),
                new Or ((Move[])startMoves.toArray()).updateConstraints(new LevelConstraint(2))
        );

        capture = new Or (new StepMove(FORWARD), new StepMove(DOWN)).updateConstraints(new LevelConstraint(3));
    }
}
