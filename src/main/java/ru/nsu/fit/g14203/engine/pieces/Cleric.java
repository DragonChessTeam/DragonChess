package ru.nsu.fit.g14203.engine.pieces;

import ru.nsu.fit.g14203.engine.utils.Color;
import ru.nsu.fit.g14203.engine.utils.Dot3D;
import ru.nsu.fit.g14203.engine.utils.Playground;
import ru.nsu.fit.g14203.engine.utils.Way;
import ru.nsu.fit.g14203.engine.constraints.Constraint;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class Cleric extends BasicPiece {


    public Cleric(Color c) {
        color = c;
    }
    /**
     * @see Piece#isValidMove(Way, Playground)
     */
    public boolean isValidMove(Way turn, Playground boards) {

        if (turn.end.x >= boards.getBoardWidth() || turn.start.x < 0 || turn.end.y >= boards.getBoardHeight() || turn.start.y < 0)
            return false;
//        * * *
//        * X *
//        * * *

        if (Math.abs(turn.end.x-turn.start.x) <= 1 && Math.abs(turn.end.y-turn.start.y) <= 1 && (turn.end.z-turn.start.z) == 0) {
            return (boards.getPieceOn(turn.end) == null || boards.getPieceOn(turn.end).getColor() != this.getColor());
        }

//        ^
//        X
//        v

        if (turn.end.x-turn.start.x == 0 && turn.end.y - turn.start.y == 0 && Math.abs(turn.end.z-turn.start.z) == 1) {
            return (boards.getPieceOn(turn.end) == null || boards.getPieceOn(turn.end).getColor() != this.getColor());
        }

        return false;
    }


    /**
     * @see Piece#isValidCapture(Way, Playground)
     */
    public boolean isValidCapture(Way turn, Playground boards) {
        return isValidMove(turn, boards);
    }


    /**
     * @see Piece#getAvailableMoves(Dot3D, Playground)
     */
    public Dot3D[] getAvailableMoves(Dot3D position, Playground boards) {

        Dot3D end = new Dot3D();
        Way testWay = new Way(position, end);

        List<Dot3D> out = new ArrayList<>();

//        * * *
//        * X *
//        * * *
        for (int offsetX = -1; offsetX <= 1; offsetX++)
            for (int offsetY = -1; offsetY <= 1; offsetY++) {
                end.x = position.x + offsetX;
                end.y = position.y + offsetY;
                if (isValidMove(testWay, boards))
                    out.add(new Dot3D(position.x+offsetX, position.y+offsetY, position.z));
            }

//        ^
//        X
//        v
        end.x = position.x;
        end.y = position.y;
        for (int offsetZ = -1; offsetZ <= 1; offsetZ++) {
            end.z = position.z + offsetZ;
            if (isValidMove(testWay, boards))
                out.add(new Dot3D(position.x, position.y, position.z+offsetZ));
        }


        return (Dot3D[])out.toArray();
    }


    /**
     * @see Piece#getAvailableCaptures(Dot3D, Playground)
     */
    public Dot3D[] getAvailableCaptures(Dot3D position, Playground boards) {
        return getAvailableMoves(position, boards);
    }


}
