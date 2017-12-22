package ru.nsu.fit.g14203.engine.pieces;

import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.engine.constraints.BlockConstraint;
import ru.nsu.fit.g14203.engine.constraints.StartLevelConstraint;
import ru.nsu.fit.g14203.engine.moveLanguage.Or;
import ru.nsu.fit.g14203.engine.moveLanguage.UpTo;
import ru.nsu.fit.g14203.engine.moveLanguage.moves.StepMove;

import static ru.nsu.fit.g14203.engine.api.utils.Dot3D.*;
import static ru.nsu.fit.g14203.engine.constraints.ConstraintFormer.formMoveConstraint;

public class Hero extends BasicPiece {
    public Hero(Color c) {
        color = c;
        move = new Or(
                new UpTo(2, new StepMove(LEFT.sum(FORWARD))).addConstraint(formMoveConstraint(new StartLevelConstraint(2))).removeConstraint(BlockConstraint.class),
                new UpTo(2, new StepMove(LEFT.sum(BACKWARD))).addConstraint(formMoveConstraint(new StartLevelConstraint(2))).removeConstraint(BlockConstraint.class),
                new UpTo(2, new StepMove(RIGHT.sum(FORWARD))).addConstraint(formMoveConstraint(new StartLevelConstraint(2))).removeConstraint(BlockConstraint.class),
                new UpTo(2, new StepMove(RIGHT.sum(BACKWARD))).addConstraint(formMoveConstraint(new StartLevelConstraint(2))).removeConstraint(BlockConstraint.class)
        );
    }
}
