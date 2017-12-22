package ru.nsu.fit.g14203.engine.constraints.triggers;

import ru.nsu.fit.g14203.engine.pieces.Basilisk;
import ru.nsu.fit.g14203.engine.utils.PieceFinder;
import ru.nsu.fit.g14203.engine.api.Piece;
import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.engine.api.utils.Dot3D;
import ru.nsu.fit.g14203.engine.constraints.Constraint;
import ru.nsu.fit.g14203.engine.constraints.Immobilized;

import java.util.ArrayList;
import java.util.List;

public class BasiliskTrigger implements Trigger {
    List<Piece> addedPreviouslyConstraints = new ArrayList<>();

    @Override
    public void apply(Piece[][][] boards) {
        //Delete old constraints
        for (Piece p : addedPreviouslyConstraints) {
            p.getMoveAlg().removeConstraint(Immobilized.class);
            p.getCaptureAlg().removeConstraint(Immobilized.class);
        }
        addedPreviouslyConstraints.clear();

        //Find basilisk
        List<Dot3D> basilisksPos = PieceFinder.findPiece(boards, Basilisk.class, Color.WHITE);
        basilisksPos.addAll(PieceFinder.findPiece(boards, Basilisk.class, Color.BLACK));

        //Setup new constraints
        for (Dot3D pos : basilisksPos) {
            if (boards[pos.x][pos.y][pos.z] != null) {
                Constraint c = new Immobilized();
                boards[pos.x][pos.y][pos.z].getMoveAlg().addConstraint(c);
                boards[pos.x][pos.y][pos.z].getCaptureAlg().addConstraint(c);
                addedPreviouslyConstraints.add(boards[pos.x][pos.y][pos.z]);
            }
        }
    }
}
