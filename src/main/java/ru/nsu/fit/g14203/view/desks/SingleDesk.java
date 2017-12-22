package ru.nsu.fit.g14203.view.desks;

import ru.nsu.fit.g14203.engine.api.Piece;
import ru.nsu.fit.g14203.view.BoardImage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SingleDesk implements ChessDesk{

    private BoardImage boardImage;
    private BoardModel boardModel;
    private Dimension chosenField;
    private Dimension releasedField;
    private Color releasedColor;


    public SingleDesk(String path, int initalWidth, int initalHeight){
        boardImage = new BoardImage(path,initalWidth, initalHeight);
        boardModel = new BoardModel();
    }

    @Override
    public BufferedImage getDeskImage(int windowWidth, int windowHeight) { //add drawing pieces

        return boardImage.getImage(windowWidth, windowHeight, boardModel.getBoard(), chosenField, releasedField, releasedColor);
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

    @Override
    public Dimension getBoardModelPosition(int x, int y){
        if(boardImage.isOnBoard(x,y)){
            return boardImage.getBoardCoordinations(x,y);
            //System.out.print(test.height + " " + test.width);
        }else return null;
    }

    @Override
    public Piece getPiece(Dimension position){
        return boardModel.getPiece(position.height, position.width);
    }

    @Override
    public void setChosenField(Dimension dimension){
        this.chosenField = dimension;
    }

    @Override
    public void setReleasedField(Dimension dimension, Color color){
        this.releasedField = dimension;
        releasedColor = color;
    }

    @Override
    public void clearReleasedField(){
        releasedField = null;
    }

    @Override
    public void clearChosenField(){
        chosenField = null;
    }

    @Override
    public Piece[][] getBoardModel(){
        return boardModel.getBoard();
    }
}
