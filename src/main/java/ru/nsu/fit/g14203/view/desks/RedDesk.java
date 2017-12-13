package ru.nsu.fit.g14203.view.desks;

import ru.nsu.fit.g14203.view.BoardImage;

import java.awt.image.BufferedImage;

public class RedDesk implements ChessDesk{

    private BoardImage boardImage;

    public RedDesk(){
        boardImage = new BoardImage("resources/redboard.jpg",600, 400);
    }

    @Override
    public BufferedImage getDeskImage(int windowWidth, int windowHeight) {
        return boardImage.getImage(windowWidth, windowHeight);
    }

    @Override
    public void onBoardClick(int x, int y) {

    }
}
