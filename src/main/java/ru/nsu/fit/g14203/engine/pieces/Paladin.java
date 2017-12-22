package ru.nsu.fit.g14203.engine.pieces;

import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.engine.constraints.StartLevelConstraint;
import ru.nsu.fit.g14203.engine.movelanguage.Follow;
import ru.nsu.fit.g14203.engine.movelanguage.Is;
import ru.nsu.fit.g14203.engine.movelanguage.Or;
import ru.nsu.fit.g14203.engine.movelanguage.moves.RoundMove;
import ru.nsu.fit.g14203.engine.movelanguage.moves.StepMove;

import static ru.nsu.fit.g14203.engine.api.utils.Dot3D.*;
import static ru.nsu.fit.g14203.engine.constraints.ConstraintFormer.formMoveConstraint;

public class Paladin extends BasicPiece {
    public Paladin(Color c) {
        color = c;
        move = new Or(
                new RoundMove(),
                new StepMove(LEFT.sum(FORWARD).sum(FORWARD)).addConstraint(formMoveConstraint(new StartLevelConstraint(2))),
                new StepMove(LEFT.sum(FORWARD).sum(LEFT)).addConstraint(formMoveConstraint(new StartLevelConstraint(2))),
                new StepMove(LEFT.sum(BACKWARD).sum(BACKWARD)).addConstraint(formMoveConstraint(new StartLevelConstraint(2))),
                new StepMove(LEFT.sum(BACKWARD).sum(LEFT)).addConstraint(formMoveConstraint(new StartLevelConstraint(2))),
                new StepMove(RIGHT.sum(BACKWARD).sum(RIGHT)).addConstraint(formMoveConstraint(new StartLevelConstraint(2))),
                new StepMove(RIGHT.sum(BACKWARD).sum(BACKWARD)).addConstraint(formMoveConstraint(new StartLevelConstraint(2))),
                new StepMove(RIGHT.sum(FORWARD).sum(RIGHT)).addConstraint(formMoveConstraint(new StartLevelConstraint(2))),
                new StepMove(RIGHT.sum(FORWARD).sum(FORWARD)).addConstraint(formMoveConstraint(new StartLevelConstraint(2))),
                new RoundMove().addConstraint(formMoveConstraint(new StartLevelConstraint(1,3))),
                new Follow(new StepMove(UP), new Is(2, new StepMove(FORWARD))).addConstraint(formMoveConstraint()),
                new Follow(new StepMove(UP), new Is(2, new StepMove(BACKWARD))).addConstraint(formMoveConstraint()),
                new Follow(new StepMove(UP), new Is(2, new StepMove(LEFT))).addConstraint(formMoveConstraint()),
                new Follow(new StepMove(UP), new Is(2, new StepMove(RIGHT))).addConstraint(formMoveConstraint()),

                new Follow(new StepMove(DOWN), new Is(2, new StepMove(FORWARD)).addConstraint(formMoveConstraint())),
                new Follow(new StepMove(DOWN), new Is(2, new StepMove(BACKWARD)).addConstraint(formMoveConstraint())),
                new Follow(new StepMove(DOWN), new Is(2, new StepMove(LEFT)).addConstraint(formMoveConstraint())),
                new Follow(new StepMove(DOWN), new Is(2, new StepMove(RIGHT)).addConstraint(formMoveConstraint())),

                new Follow(new Follow(new StepMove(UP), new StepMove(UP)), new StepMove(FORWARD)).addConstraint(formMoveConstraint()),
                new Follow(new Follow(new StepMove(UP), new StepMove(UP)), new StepMove(BACKWARD)).addConstraint(formMoveConstraint()),
                new Follow(new Follow(new StepMove(UP), new StepMove(UP)), new StepMove(LEFT)).addConstraint(formMoveConstraint()),
                new Follow(new Follow(new StepMove(UP), new StepMove(UP)), new StepMove(RIGHT)).addConstraint(formMoveConstraint()),

                new Follow(new Follow(new StepMove(DOWN), new StepMove(DOWN)), new StepMove(FORWARD)).addConstraint(formMoveConstraint()),
                new Follow(new Follow(new StepMove(DOWN), new StepMove(DOWN)), new StepMove(BACKWARD)).addConstraint(formMoveConstraint()),
                new Follow(new Follow(new StepMove(DOWN), new StepMove(DOWN)), new StepMove(LEFT)).addConstraint(formMoveConstraint()),
                new Follow(new Follow(new StepMove(DOWN), new StepMove(DOWN)), new StepMove(RIGHT)).addConstraint(formMoveConstraint())



        );
    }
}
