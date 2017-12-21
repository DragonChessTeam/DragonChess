package ru.nsu.fit.g14203.net.channel;

import ru.nsu.fit.g14203.engine.api.Piece;
import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.engine.api.utils.Dot3D;

import java.util.List;
import java.util.Objects;

public class TestPiece implements Piece {

    private final Color color;

    public TestPiece(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public List<Dot3D> getAvailableMoves(Dot3D position, Piece[][][] boards) {
        return null;
    }

    @Override
    public List<Dot3D> getAvailableCaptures(Dot3D position, Piece[][][] boards) {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        final TestPiece testPiece = (TestPiece) o;
        return color == testPiece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
