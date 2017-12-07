package ru.nsu.fit.g14203.engine.pieces;

import ru.nsu.fit.g14203.engine.utils.Dot3D;
import ru.nsu.fit.g14203.engine.utils.Playground;
import ru.nsu.fit.g14203.engine.utils.Way;
import ru.nsu.fit.g14203.engine.constraints.Constraint;

public class Unicorn extends BasicPiece {


    /**
     * @see Piece#isValidMove(Way, Playground)
     */
    public boolean isValidMove(Way turn, Playground boards) {
        return false;
    }


    /**
     * @see Piece#isValidCapture(Way, Playground)
     */
    public boolean isValidCapture(Way turn, Playground boards) {
        return false;
    }


    /**
     * @see Piece#getAvailableMoves(Dot3D, Playground)
     */
    public Dot3D[] getAvailableMoves(Dot3D position, Playground boards) {
        return null;
    }


    /**
     * @see Piece#getAvailableCaptures(Dot3D, Playground)
     */
    public Dot3D[] getAvailableCaptures(Dot3D position, Playground boards) {
        return null;
    }


}
