package ru.nsu.fit.g14203.engine;

public interface Piece {

    public abstract boolean isValidMove(Way turn, Playground boards);

    public abstract boolean isValidCapture(Way turn, Playground boards);

    public abstract Dot3D[] getAvailableMoves(Dot3D position, Playground boards);

    public abstract Dot3D[] getAvailableCaptures(Dot3D position, Playground boards);

    public abstract void addConstraint(Constraint effect);

    public abstract void delConstraint(Constraint effect);

}
