package ru.nsu.fit.g14203.engine.pieces;

import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.engine.moveLanguage.Follow;
import ru.nsu.fit.g14203.engine.moveLanguage.Is;
import ru.nsu.fit.g14203.engine.moveLanguage.Or;
import ru.nsu.fit.g14203.engine.moveLanguage.UpTo;
import ru.nsu.fit.g14203.engine.moveLanguage.constraints.LevelConstraint;
import ru.nsu.fit.g14203.engine.moveLanguage.moves.FullDiagonalMove;
import ru.nsu.fit.g14203.engine.moveLanguage.moves.FullOrthogonalMove;
import ru.nsu.fit.g14203.engine.moveLanguage.moves.StepMove;
import ru.nsu.fit.g14203.engine.moveLanguage.moves.TriagonalMove;

import static ru.nsu.fit.g14203.engine.api.utils.Dot3D.*;

public class Griffon extends BasicPiece {


    public Griffon(Color c) {
        color = c;
        //magic on level 3
        move = new Or (
                new Or(
                    new Follow(new Is(2, new StepMove(FORWARD.sum(LEFT))), new StepMove(FORWARD)),
                    new Follow(new Is(2, new StepMove(FORWARD.sum(LEFT))), new StepMove(LEFT)),
                    new Follow(new Is(2, new StepMove(FORWARD.sum(RIGHT))), new StepMove(FORWARD)),
                    new Follow(new Is(2, new StepMove(FORWARD.sum(RIGHT))), new StepMove(RIGHT)),
                    new Follow(new Is(2, new StepMove(BACKWARD.sum(LEFT))), new StepMove(BACKWARD)),
                    new Follow(new Is(2, new StepMove(BACKWARD.sum(LEFT))), new StepMove(LEFT)),
                    new Follow(new Is(2, new StepMove(BACKWARD.sum(RIGHT))), new StepMove(BACKWARD)),
                    new Follow(new Is(2, new StepMove(BACKWARD.sum(RIGHT))), new StepMove(RIGHT)),
                    new TriagonalMove(DOWN)
            ).updateConstraints(new LevelConstraint(3)),
                new Or(
                        new FullDiagonalMove(),
                        new TriagonalMove(UP)
                ).updateConstraints(new LevelConstraint(2))
        );

        capture = move;
    }
}
