package ru.nsu.fit.g14203.engine;

public class Warrior implements Piece {


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


    /**
     * @see Piece#addConstraint(Constraint)
     */
    public void addConstraint(Constraint effect) {

    }


    /**
     * @see Piece#delConstraint(Constraint)
     */
    public void delConstraint(Constraint effect) {

    }

}
