package ru.nsu.fit.g14203.view.desks;

import ru.nsu.fit.g14203.engine.api.Piece;


public class BoardModel {

    private Piece[][] board;



    BoardModel(){
        board = new Piece[8][12];
    }

    public Piece getPiece(int i, int j){
        return board[i][j];
    }

    public void setPiece(int i, int j, Piece piece){
        board[i][j] = piece;
    }

    public void deletePiece(int i, int j){
        board[i][j] = null;
    }

    public void addPiece(int i, int j, Piece piece){
        board[i][j] = piece;
    }

    public void movePiece(int i, int j, int i1, int j1){
        board[i1][j1] = board[i][j];
        board[i][j] = null;
    }

    public Piece[][] getBoard() {
        return board;
    }
}
