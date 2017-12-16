package ru.nsu.fit.g14203.engine.pieces;

import ru.nsu.fit.g14203.engine.api.Piece;
import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.engine.api.utils.Dot3D;
import ru.nsu.fit.g14203.engine.constraints.BlockContraint;
import ru.nsu.fit.g14203.engine.constraints.BoardBorderConstraint;
import ru.nsu.fit.g14203.engine.constraints.CheckConstraint;
import ru.nsu.fit.g14203.engine.constraints.StartLevelConstraint;
import ru.nsu.fit.g14203.engine.moveLanguage.Or;
import ru.nsu.fit.g14203.engine.moveLanguage.moves.RoundMove;
import ru.nsu.fit.g14203.engine.moveLanguage.moves.StepMove;

import java.util.List;

import static ru.nsu.fit.g14203.engine.api.utils.Dot3D.DOWN;
import static ru.nsu.fit.g14203.engine.api.utils.Dot3D.UP;

public class King extends BasicPiece {

    private Dot3D previousPos;

    public King(Color c, Piece[][][] pg) {
        color = c;
        move = new Or(
                new RoundMove(),
                new StepMove(DOWN),
                new StepMove(UP)
        ).addConstraint(new BoardBorderConstraint(), new StartLevelConstraint(2), new CheckConstraint(),  new BlockContraint());
        capture = move;
    }

    @Override
    public List<Dot3D> getAvailableMoves(Dot3D position, Piece[][][] boards) {
        List<Dot3D> out = super.getAvailableMoves(position, boards);
        if (position.z == 1) previousPos = position;
        else out.add(previousPos);
        return out;
    }

}
