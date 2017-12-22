package ru.nsu.fit.g14203.engine.pieces;

import ru.nsu.fit.g14203.engine.api.Piece;
import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.engine.api.utils.Dot3D;
import ru.nsu.fit.g14203.engine.constraints.BlockConstraint;
import ru.nsu.fit.g14203.engine.constraints.StartLevelConstraint;
import ru.nsu.fit.g14203.engine.moveLanguage.Or;
import ru.nsu.fit.g14203.engine.moveLanguage.UpTo;
import ru.nsu.fit.g14203.engine.moveLanguage.moves.StepMove;
import ru.nsu.fit.g14203.engine.moveLanguage.moves.TriagonalMove;

import java.util.ArrayList;
import java.util.List;

import static ru.nsu.fit.g14203.engine.api.utils.Dot3D.*;
import static ru.nsu.fit.g14203.engine.constraints.ConstraintFormer.formCaptureConstraint;
import static ru.nsu.fit.g14203.engine.constraints.ConstraintFormer.formMoveConstraint;

public class Hero extends BasicPiece {

    private Dot3D prevPosition = null;

    public Hero(Color c) {
        color = c;
        move = new Or(
                new UpTo(2, new StepMove(LEFT.sum(FORWARD))).addConstraint(formMoveConstraint(new StartLevelConstraint(2))).removeConstraint(BlockConstraint.class),
                new UpTo(2, new StepMove(LEFT.sum(BACKWARD))).addConstraint(formMoveConstraint(new StartLevelConstraint(2))).removeConstraint(BlockConstraint.class),
                new UpTo(2, new StepMove(RIGHT.sum(FORWARD))).addConstraint(formMoveConstraint(new StartLevelConstraint(2))).removeConstraint(BlockConstraint.class),
                new UpTo(2, new StepMove(RIGHT.sum(BACKWARD))).addConstraint(formMoveConstraint(new StartLevelConstraint(2))).removeConstraint(BlockConstraint.class),
                new TriagonalMove(false).addConstraint(formMoveConstraint(new StartLevelConstraint(2))),
                new TriagonalMove(true).addConstraint(formMoveConstraint(new StartLevelConstraint(2)))
        );

        capture = new Or(
                new UpTo(2, new StepMove(LEFT.sum(FORWARD))).addConstraint(formCaptureConstraint(new StartLevelConstraint(2))).removeConstraint(BlockConstraint.class),
                new UpTo(2, new StepMove(LEFT.sum(BACKWARD))).addConstraint(formCaptureConstraint(new StartLevelConstraint(2))).removeConstraint(BlockConstraint.class),
                new UpTo(2, new StepMove(RIGHT.sum(FORWARD))).addConstraint(formCaptureConstraint(new StartLevelConstraint(2))).removeConstraint(BlockConstraint.class),
                new UpTo(2, new StepMove(RIGHT.sum(BACKWARD))).addConstraint(formCaptureConstraint(new StartLevelConstraint(2))).removeConstraint(BlockConstraint.class),
                new TriagonalMove(false).addConstraint(formCaptureConstraint(new StartLevelConstraint(2))),
                new TriagonalMove(true).addConstraint(formCaptureConstraint(new StartLevelConstraint(2)))
        );
    }


    @Override
    public List<Dot3D> getAvailableMoves(Dot3D position, Piece[][][] boards) {
        if (position.z == 1) {
            prevPosition = position;
        }

        List <Dot3D> out = super.getAvailableMoves(position, boards);
        if (position.z != 1) {
            out.add(prevPosition);
        }
        return out;
    }

    @Override
    public List<Dot3D> getAvailableCaptures(Dot3D position, Piece[][][] boards) {
        if (position.z == 1) {
            prevPosition = position;
        }

        List <Dot3D> out = super.getAvailableCaptures(position, boards);
        if (position.z != 1) {
            out.add(prevPosition);
        }
        return out;
    }
}
