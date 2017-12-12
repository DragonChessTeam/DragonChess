package ru.nsu.fit.g14203.engine.pieces;

import ru.nsu.fit.g14203.engine.api.Piece;
import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.engine.moveLanguage.Or;
import ru.nsu.fit.g14203.engine.moveLanguage.UpToBorder;
import ru.nsu.fit.g14203.engine.moveLanguage.constraints.LevelConstraint;
import ru.nsu.fit.g14203.engine.moveLanguage.moves.StepMove;

import static ru.nsu.fit.g14203.engine.api.utils.Dot3D.*;

public class Oliphant extends BasicPiece {


    public Oliphant(Color c, Piece[][][] pg) {
        color = c;
        move = new Or(
                new UpToBorder(pg, new StepMove(FORWARD)),
                new UpToBorder(pg, new StepMove(RIGHT)),
                new UpToBorder(pg, new StepMove(LEFT)),
                new UpToBorder(pg, new StepMove(BACKWARD))
        ).updateConstraints(new LevelConstraint(2));

        capture = move;
    }

}
