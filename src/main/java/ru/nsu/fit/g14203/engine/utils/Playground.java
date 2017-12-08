package ru.nsu.fit.g14203.engine.utils;

import ru.nsu.fit.g14203.engine.pieces.Piece;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Playground {

    public Piece[][][] ground = new Piece[12][8][3]; //[X][Y][Z==Num boards]

    public Piece getPieceOn(Dot3D pos) {return ground[pos.x][pos.y][pos.z];}

    public int getBoardWidth() {return ground.length;}

    public int getBoardHeight() {return ground[0].length;}

    public boolean isOutOfBoard(Dot3D end) {
        return end.x < 0 || end.x >= ground.length || end.y < 0 || end.y > ground[0].length || end.z < 0 || end.z > ground[0][0].length;
    }
}
