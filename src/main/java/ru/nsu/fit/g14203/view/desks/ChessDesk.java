package ru.nsu.fit.g14203.view.desks;

import java.awt.image.BufferedImage;

public interface ChessDesk {

    public BufferedImage getDeskImage();

    public void onBoardClick(int x, int y);

}
