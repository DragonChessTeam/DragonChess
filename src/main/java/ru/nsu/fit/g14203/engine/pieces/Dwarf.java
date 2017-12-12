package ru.nsu.fit.g14203.engine.pieces;

import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.engine.moveLanguage.Or;
import ru.nsu.fit.g14203.engine.moveLanguage.constraints.LevelConstraint;
import ru.nsu.fit.g14203.engine.moveLanguage.moves.StepMove;

import static ru.nsu.fit.g14203.engine.api.utils.Dot3D.*;

public class Dwarf extends BasicPiece {


    public Dwarf(Color c) {
        color = c;

        move = new Or(
                new Or(
                    new StepMove(LEFT),
                    new StepMove(RIGHT),
                    new StepMove(FORWARD)
                ).updateConstraints(new LevelConstraint(1,2)),
                new StepMove(DOWN).updateConstraints(new LevelConstraint(2))
        );
        capture = new Or(
                new Or (
                    new StepMove(FORWARD.sum(LEFT)),
                    new StepMove(FORWARD.sum(RIGHT))
                ).updateConstraints(new LevelConstraint(1,2)),
                new StepMove(UP).updateConstraints(new LevelConstraint(1))
        );

    }

}
