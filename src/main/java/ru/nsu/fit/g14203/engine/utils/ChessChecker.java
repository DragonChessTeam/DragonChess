package ru.nsu.fit.g14203.engine.utils;

import ru.nsu.fit.g14203.engine.api.Piece;
import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.engine.api.utils.Dot3D;
import ru.nsu.fit.g14203.engine.api.utils.Way;
import ru.nsu.fit.g14203.engine.constraints.CheckConstraint;
import ru.nsu.fit.g14203.engine.pieces.King;

import java.util.List;

public class ChessChecker {

    public static boolean isCheckAfterTurn(Way move, Color side, Piece[][][] pg) {

        Piece savedPiece = pg[move.end.x][move.end.y][move.end.z-1];
        pg[move.end.x][move.end.y][move.end.z-1] = pg[move.start.x][move.start.y][move.start.z-1];
        pg[move.start.x][move.start.y][move.start.z-1] = null;
        boolean result = isCheckFor(side, pg);
        pg[move.start.x][move.start.y][move.start.z-1] = pg[move.end.x][move.end.y][move.end.z-1];
        pg[move.end.x][move.end.y][move.end.z-1] = savedPiece;

        return result;
    }

    public static boolean isCheckFor(Color side, Piece[][][] pg) {
int i2 = 0;

        //Find our king
        List<Dot3D> places = PieceFinder.findPiece(pg, King.class, side);
        Dot3D kingPos = places.get(0);

        //check available capture move from enemy's figures
        for (int x = 0; x < pg.length; x++)
            for(int y = 0; y < pg[0].length; y++)
                for (int z = 0; z < pg[0][0].length; z++)
                    if (pg[x][y][z] != null && pg[x][y][z].getColor() != side){
                        pg[x][y][z].getCaptureAlg().removeConstraint(CheckConstraint.class);
                        List<Dot3D> ends = pg[x][y][z].getAvailableCaptures(new Dot3D(x,y,z+1), pg);
                        pg[x][y][z].getCaptureAlg().addConstraint(new CheckConstraint(pg));

                        for (Dot3D otherFigureEnd : ends) {
                            if (otherFigureEnd.equals(kingPos)) return true;
                        }
                    }

        return false;
    }
    
    public static boolean isCheckMateFor(Color side, Piece[][][] pg) {
        List<Dot3D> piecePlaces = PieceFinder.findPiece(pg, side);
        for(Dot3D pos : piecePlaces) {
            List<Dot3D> availMoves = pg[pos.x][pos.y][pos.z-1].getAvailableMoves(pos, pg);
            if (!availMoves.isEmpty()) return false;
            availMoves = pg[pos.x][pos.y][pos.z-1].getAvailableCaptures(pos, pg);
            if (!availMoves.isEmpty()) return false;
        }

        return true;
    }
}
