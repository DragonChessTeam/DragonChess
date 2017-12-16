package ru.nsu.fit.g14203.engine.api.utils;

import ru.nsu.fit.g14203.engine.api.Piece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UpdateEntry {

    public Piece pieceToPlace;

    public List<Dot3D> fullPath;

    public UpdateEntry(Piece piece, Dot3D... dots) {
        pieceToPlace = piece;
        fullPath = new ArrayList<>();
        fullPath.addAll(Arrays.asList(dots));
    }
}
