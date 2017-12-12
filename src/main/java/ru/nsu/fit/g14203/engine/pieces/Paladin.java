package ru.nsu.fit.g14203.engine.pieces;

import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.engine.api.utils.Dot3D;
import ru.nsu.fit.g14203.engine.moveLanguage.Follow;
import ru.nsu.fit.g14203.engine.moveLanguage.Is;
import ru.nsu.fit.g14203.engine.moveLanguage.Move;
import ru.nsu.fit.g14203.engine.moveLanguage.Or;
import ru.nsu.fit.g14203.engine.moveLanguage.constraints.LevelConstraint;
import ru.nsu.fit.g14203.engine.moveLanguage.moves.RoundMove;
import ru.nsu.fit.g14203.engine.moveLanguage.moves.StepMove;

import java.util.ArrayList;
import java.util.List;

import static ru.nsu.fit.g14203.engine.api.utils.Dot3D.*;

public class Paladin extends BasicPiece {
    public Paladin(Color c) {

        List<Move> knight = new ArrayList<>();
        //Knight moves
        for (Dot3D a : new Dot3D[]{FORWARD, BACKWARD})
            for (Dot3D b : new Dot3D[]{LEFT, RIGHT}) {
                Dot3D end = a.sum(a).sum(b);
                Dot3D end2 = a.sum(b).sum(b);
                knight.add(new StepMove(end));
                knight.add(new StepMove(end2));
            }
        //Knight moves in 3D :D
        List<Move> knightUp = new ArrayList<>();
        for (Dot3D a : new Dot3D[]{UP, DOWN})
            for (Dot3D b : new Dot3D[]{FORWARD, BACKWARD, LEFT, RIGHT}) {
                Dot3D end = a.sum(a).sum(b);
                Dot3D end2 = a.sum(b).sum(b);
                knight.add(new StepMove(end));
                knight.add(new StepMove(end2));
            }

        move = new Or(
                new RoundMove().updateConstraints(new LevelConstraint(1,3)),
                new Or(
                    new Follow(new RoundMove(), new Or((Move[]) knight.toArray())),
                    new Follow(new Or((Move[]) knight.toArray()), new RoundMove())
                ).updateConstraints(new LevelConstraint(2)),
                new Or((Move[]) knightUp.toArray())
        );

        capture = new Or(
                new RoundMove().updateConstraints(new LevelConstraint(1,3)),
                new Or(
                        new Follow(new RoundMove(), new Or((Move[]) knight.toArray())),
                        new Follow(new Or((Move[]) knight.toArray()), new RoundMove())
                ).updateConstraints(new LevelConstraint(2))
        );


    }
}
