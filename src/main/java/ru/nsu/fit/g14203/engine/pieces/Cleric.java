package ru.nsu.fit.g14203.engine.pieces;

import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.engine.moveLanguage.BasicMove;
import ru.nsu.fit.g14203.engine.moveLanguage.Move;
import ru.nsu.fit.g14203.engine.moveLanguage.Or;
import ru.nsu.fit.g14203.engine.moveLanguage.moves.RoundMove;
import ru.nsu.fit.g14203.engine.moveLanguage.moves.StepMove;

import static ru.nsu.fit.g14203.engine.api.utils.Dot3D.DOWN;
import static ru.nsu.fit.g14203.engine.api.utils.Dot3D.UP;

public class Cleric extends BasicPiece {


    public Cleric(Color c) {
        color = c;
        move = new Or(new Move[] {new RoundMove(), new StepMove(UP), new StepMove(DOWN)});
        capture = move;
    }

}
