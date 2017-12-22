package ru.nsu.fit.g14203.engine.moveLanguage.moves;

import ru.nsu.fit.g14203.engine.api.Piece;
import ru.nsu.fit.g14203.engine.api.utils.Dot3D;
import ru.nsu.fit.g14203.engine.moveLanguage.BasicMove;
import ru.nsu.fit.g14203.engine.moveLanguage.Move;
import ru.nsu.fit.g14203.engine.moveLanguage.Or;
import ru.nsu.fit.g14203.engine.pieces.BasicPiece;

import java.util.ArrayList;
import java.util.List;

import static ru.nsu.fit.g14203.engine.api.utils.Dot3D.*;
import static ru.nsu.fit.g14203.engine.api.utils.Dot3D.BACKWARD;
import static ru.nsu.fit.g14203.engine.api.utils.Dot3D.NONE;

public class OneOrthogonal extends BasicMove {

    private Move move;

    public OneOrthogonal() {
        List<Move> moves = new ArrayList<>();
        for (Dot3D a : new Dot3D[]{LEFT, RIGHT, FORWARD, BACKWARD})
            moves.add(new StepMove(a));
        Move[] moveArr = new Move[moves.size()];
        moves.toArray(moveArr);
        move = new Or(moveArr);
    }


    public List<Dot3D> doMove(Dot3D pos, Piece[][][] boards) {

        return move.getMovesFrom(pos, boards);

    }

}
