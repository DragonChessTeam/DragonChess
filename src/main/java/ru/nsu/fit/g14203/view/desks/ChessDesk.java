package ru.nsu.fit.g14203.view.desks;

import ru.nsu.fit.g14203.engine.api.Piece;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface ChessDesk {

    BufferedImage getDeskImage(int windowWidth, int windowHeight);

    void onBoardClick(int x, int y);

    void deletePiece(int i, int j);

    void addPiece(int i, int j, Piece piece);

    void movePiece(int i, int j, int i1, int j1);

    Dimension getBoardModelPosition(int x, int y);

    Piece getPiece(Dimension position);

    void setChosenField(Dimension dimension);

    void clearChosenField();

    Piece[][] getBoardModel();

    void setReleasedField(Dimension dimension, Color color);

    void clearReleasedField();


}
