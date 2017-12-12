package ru.nsu.fit.g14203.engine.moveLanguage.moves;

import ru.nsu.fit.g14203.engine.api.utils.Dot3D;
import ru.nsu.fit.g14203.engine.moveLanguage.BasicMove;
import ru.nsu.fit.g14203.engine.moveLanguage.Move;
import ru.nsu.fit.g14203.engine.moveLanguage.Or;

import java.util.ArrayList;
import java.util.List;

import static ru.nsu.fit.g14203.engine.api.utils.Dot3D.*;

public class FullOrthogonalMove extends BasicMove {
    private Move move;

    public FullOrthogonalMove() {
        List<Move> orths = new ArrayList<>();

        orths.add(new StepMove(FORWARD));
        orths.add(new StepMove(BACKWARD));
        orths.add(new StepMove(LEFT));
        orths.add(new StepMove(RIGHT));

        move = new Or((Move[]) orths.toArray());
    }

    @Override
    protected List<Dot3D> doMove(Dot3D pos) {
        return move.getMovesFrom(pos);
    }
}
