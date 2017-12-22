package ru.nsu.fit.g14203.view.desks;

import ru.nsu.fit.g14203.engine.api.Piece;
import ru.nsu.fit.g14203.view.BoardImage;

import java.awt.image.BufferedImage;

public class SingleDesk implements ChessDesk{

    private BoardImage boardImage;
    private BoardModel boardModel;

    public SingleDesk(String path, int initalWidth, int initalHeight){
        boardImage = new BoardImage(path,initalWidth, initalHeight);
        boardModel = new BoardModel();
    }

    @Override
    public BufferedImage getDeskImage(int windowWidth, int windowHeight) {
        return boardImage.getImage(windowWidth, windowHeight);
    }

    @Override
    public void deletePiece(int i, int j){
        boardModel.deletePiece(i,j);
    }

    @Override
    public void addPiece(int i, int j, Piece piece){
        boardModel.addPiece(i, j, piece);
    }

    @Override
    public void movePiece(int i, int j, int i1, int j1){
        boardModel.movePiece(i, j, i1, j1);
    }

    @Override
    public void onBoardClick(int x, int y) {

    }
}
