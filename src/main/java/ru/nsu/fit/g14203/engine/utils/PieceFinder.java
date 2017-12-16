package ru.nsu.fit.g14203.engine.utils;

import ru.nsu.fit.g14203.engine.api.Piece;
import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.engine.api.utils.Dot3D;

import java.util.ArrayList;
import java.util.List;

public class PieceFinder {
    public static List<Dot3D> findPiece(Piece[][][] boards, Class pieceType, Color color) {
        List<Dot3D> out = new ArrayList<>();
        for (int x = 0; x < boards.length; x++)
            for(int y = 0; y < boards[0].length; y++)
                for (int z = 0; z < boards[0][0].length; z++)
                    if (boards[x][y][z] != null && boards[x][y][z].getColor() == color && boards[x][y][z].getClass().getName().equals(pieceType.getName()))
                        out.add(new Dot3D(x,y,z));
        return out;
    }

    public static List<Dot3D> findPiece(Piece[][][] boards, Color side) {
        List<Dot3D> out = new ArrayList<>();
        for (int x = 0; x < boards.length; x++)
            for(int y = 0; y < boards[0].length; y++)
                for (int z = 0; z < boards[0][0].length; z++)
                    if (boards[x][y][z] != null && boards[x][y][z].getColor() == side)
                        out.add(new Dot3D(x,y,z));
        return out;
    }
}
