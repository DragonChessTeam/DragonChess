package ru.nsu.fit.g14203.view.desks;

import ru.nsu.fit.g14203.engine.api.Piece;

import java.awt.image.BufferedImage;

public interface ChessDesk {

    public BufferedImage getDeskImage(int windowWidth, int windowHeight);

    public void onBoardClick(int x, int y);

    public void deletePiece(int i, int j);

    public void addPiece(int i, int j, Piece piece);

    public void movePiece(int i, int j, int i1, int j1);a

}
