package ru.nsu.fit.g14203.engine.utils;

import ru.nsu.fit.g14203.engine.guiapi.Piece;

import java.util.List;

public class Playground {

    public Piece[][][] ground = new Piece[12][8][3]; //[X][Y][Z==Num boards]

    public Piece getPieceOn(Dot3D pos) {return ground[pos.x][pos.y][pos.z];}

    public int getBoardWidth() {return ground.length;}

    public int getBoardHeight() {return ground[0].length;}

    public boolean isOutOfBoard(Dot3D end) {
        return end.x < 0 || end.x >= ground.length || end.y < 0 || end.y > ground[0].length || end.z < 0 || end.z > ground[0][0].length;
    }

    private boolean angleChecker(int value) {
        return (value == 1 || value == -1 || value == 0);
    }

    public boolean isFreeWay(Way turn) {
        Dot3D diff = turn.end.sub(turn.start);
        if (!angleChecker(diff.x)) diff.x /= Math.abs(diff.x);
        if (!angleChecker(diff.y)) diff.y /= Math.abs(diff.y);
        if (!angleChecker(diff.z)) diff.z /= Math.abs(diff.z);

        Dot3D now = turn.start;
        while (!now.equals(turn.end)) {
            if (ground[now.x][now.y][now.z] != null) return false;
            now = now.sum(diff);
        }
        return true;
    }

    public boolean thoseDotsAreFree(List<Dot3D> coveredMoveDots) {
        for (Dot3D dot : coveredMoveDots) {
            if (ground[dot.x][dot.y][dot.z] != null) return false;
        }
        return true;
    }
}
