package ru.nsu.fit.g14203.engine.utils;

import ru.nsu.fit.g14203.engine.api.Piece;
import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.engine.api.utils.Dot3D;
import ru.nsu.fit.g14203.engine.api.utils.Way;
import ru.nsu.fit.g14203.engine.constraints.CheckConstraint;
import ru.nsu.fit.g14203.engine.pieces.King;

import java.util.List;

public class ChessChecker {

    private ChessChecker() {}

    public static boolean isCheckAfterTurn(Way move, Color side, Piece[][][] pg) {

        Piece savedPiece = pg[move.end.x][move.end.y][move.end.z];
        pg[move.end.x][move.end.y][move.end.z] = pg[move.start.x][move.start.y][move.start.z];
        pg[move.start.x][move.start.y][move.start.z] = null;
        boolean result = isCheckFor(side, pg);
        pg[move.start.x][move.start.y][move.start.z] = pg[move.end.x][move.end.y][move.end.z];
        pg[move.end.x][move.end.y][move.end.z] = savedPiece;

        return result;
    }

    private static boolean isKingInRange(Piece[][][] boards, Piece piece, Color side, Dot3D piecePos, Dot3D kingPos) {
        if (piece != null && piece.getColor() != side){
            piece.getCaptureAlg().removeConstraint(CheckConstraint.class);
            List<Dot3D> ends =  piece.getAvailableCaptures(piecePos, boards);
            piece.getCaptureAlg().addConstraint(new CheckConstraint());

            for (Dot3D otherFigureEnd : ends) {
                if (otherFigureEnd.equals(kingPos)) return true;
            }
        }

        return false;
    }

    public static boolean isCheckFor(Color side, Piece[][][] pg) {

        //Find our king
        List<Dot3D> places = PieceFinder.findPiece(pg, King.class, side);
        Dot3D kingPos = places.get(0);

        //check available capture move from enemy's figures
        for (int x = 0; x < pg.length; x++)
            for(int y = 0; y < pg[0].length; y++)
                for (int z = 0; z < pg[0][0].length; z++)
                    if (isKingInRange(pg, pg[x][y][z], side, new Dot3D(x,y,z), kingPos)) return true;

        return false;
    }
    
    public static boolean isCheckMateFor(Color side, Piece[][][] pg) {
        List<Dot3D> piecePlaces = PieceFinder.findPieces(pg, side);
        for(Dot3D pos : piecePlaces) {
            List<Dot3D> availMoves = pg[pos.x][pos.y][pos.z].getAvailableMoves(pos, pg);
            if (!availMoves.isEmpty()) return false;
            availMoves = pg[pos.x][pos.y][pos.z].getAvailableCaptures(pos, pg);
            if (!availMoves.isEmpty()) return false;
        }

        return true;
    }
}
