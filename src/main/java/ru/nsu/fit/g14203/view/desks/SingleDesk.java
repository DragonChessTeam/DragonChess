package ru.nsu.fit.g14203.view.desks;

import ru.nsu.fit.g14203.view.BoardImage;

import java.awt.image.BufferedImage;

public class SingleDesk implements ChessDesk{

    private BoardImage boardImage;

    public SingleDesk(String path, int initalWidth, int initalHeight){
        boardImage = new BoardImage(path,initalWidth, initalHeight);
    }

    @Override
    public BufferedImage getDeskImage(int windowWidth, int windowHeight) {
        return boardImage.getImage(windowWidth, windowHeight);
    }

    @Override
    public void onBoardClick(int x, int y) {

    }
}
