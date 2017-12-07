package ru.nsu.fit.g14203.engine;

public interface Constraint {

    public abstract Dot3D[] changeValidMoves(Dot3D[] moves);

    public abstract Dot3D[] changeValidCaptures(Dot3D[] captures);

}
