package ru.nsu.fit.g14203.engine.pieces;

import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.engine.moveLanguage.Follow;
import ru.nsu.fit.g14203.engine.moveLanguage.Or;
import ru.nsu.fit.g14203.engine.moveLanguage.UpTo;
import ru.nsu.fit.g14203.engine.moveLanguage.constraints.LevelConstraint;
import ru.nsu.fit.g14203.engine.moveLanguage.moves.FullDiagonalMove;
import ru.nsu.fit.g14203.engine.moveLanguage.moves.FullOrthogonalMove;
import ru.nsu.fit.g14203.engine.moveLanguage.moves.StepMove;

import static ru.nsu.fit.g14203.engine.api.utils.Dot3D.*;

public class Elemental extends BasicPiece {

    public Elemental(Color c) {
        color = c;
        move = new Or (
                new Or(
                        new UpTo(2, new StepMove(FORWARD)),
                        new UpTo(2, new StepMove(LEFT)),
                        new UpTo(2, new StepMove(RIGHT)),
                        new UpTo(2, new StepMove(BACKWARD)),
                        new FullDiagonalMove()
                ).updateConstraints(new LevelConstraint(1)),
                new Follow(new StepMove(DOWN), new FullOrthogonalMove()).updateConstraints(new LevelConstraint(2))
        );

        capture = new Or (
                new Or(
                        new UpTo(2, new StepMove(FORWARD)),
                        new UpTo(2, new StepMove(LEFT)),
                        new UpTo(2, new StepMove(RIGHT)),
                        new UpTo(2, new StepMove(BACKWARD)),
                        new Follow(new FullOrthogonalMove(), new StepMove(UP))
                ).updateConstraints(new LevelConstraint(1)),
                new Follow(new StepMove(DOWN), new FullOrthogonalMove()).updateConstraints(new LevelConstraint(2))
        );
    }

}
