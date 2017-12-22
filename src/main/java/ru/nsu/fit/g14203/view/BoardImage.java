package ru.nsu.fit.g14203.view;

import net.coobird.thumbnailator.Thumbnails;
import ru.nsu.fit.g14203.engine.api.Piece;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Dimension2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BoardImage {

    private BufferedImage originalImage;
    private BufferedImage pressedImage;
    private int k;

    public BoardImage(String path, int initalWidth, int initalHeight){
        k = 0;
        try {
            originalImage = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        scale(initalWidth, initalHeight);

    }

    private void scale(int windowWidth, int windowHeight){

        double scaleWidthCount = (double) windowWidth/ (double) originalImage.getWidth();
        double scaleHeightCount = (double) windowHeight/ (double) originalImage.getHeight();
        double scaleCount = Math.min(scaleHeightCount, scaleWidthCount);

        try {
            pressedImage = Thumbnails.of(originalImage).scale(scaleCount).asBufferedImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void scaleByWidth(int width) throws IOException {

        double scaleCount = (double) width/(double) originalImage.getWidth();
        pressedImage =  Thumbnails.of(originalImage).scale(scaleCount).asBufferedImage();

    }

    public void scaleByHeight(int height) throws IOException {

        double scaleCount = (double) height/(double) originalImage.getHeight();
        pressedImage = Thumbnails.of(originalImage).scale(scaleCount).asBufferedImage();

    }

    public BufferedImage getImage(int windowWidth, int windowHeight, Piece[][] boardModel, Dimension choosenField){ //add piece drawing

        scale(windowWidth, windowHeight);

        for(int i = 0; i < boardModel.length; i++){
            for(int j = 0; j < boardModel[0].length; j++){
                if(boardModel[i][j] != null){
                    drawPiece(i, j, "asd", boardModel[i][j].getColor()); //TODO: change piece color
                }
            }
        }
        if(choosenField != null){
            drawChosenField(choosenField);
        }

        return pressedImage;
    }

    private void drawChosenField(Dimension choosenField){
        int xShift, yShift;
        xShift = pressedImage.getWidth() / 12;
        yShift = pressedImage.getHeight() / 8;
        Graphics g = pressedImage.getGraphics();
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.BLUE);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRect(xShift * choosenField.width, yShift * choosenField.height, xShift, yShift);

    }

    public BufferedImage getImage(){
        return pressedImage;
    }

    private void drawPiece(int i, int j, String name, ru.nsu.fit.g14203.engine.api.utils.Color color){

        Graphics g = pressedImage.createGraphics();

        if(color == ru.nsu.fit.g14203.engine.api.utils.Color.BLACK)
            g.setColor(Color.BLACK);
        else
            g.setColor(Color.WHITE);

        int xShift, yShift;
        xShift = pressedImage.getWidth() / 12;
        yShift = pressedImage.getHeight() / 8;
        int xPosition = xShift * j;
        int yPosition = yShift * i;
        g.fillOval(xPosition , yPosition , yShift, xShift);

        if(color == ru.nsu.fit.g14203.engine.api.utils.Color.BLACK)
            g.setColor(Color.WHITE);
        else
            g.setColor(Color.BLACK);
        g.drawString(name, xPosition + xShift/4, yPosition + yShift/2);
    }

    public boolean isOnBoard(int x, int y){
        return x < pressedImage.getWidth() && y < pressedImage.getHeight();
    }

    public Dimension getBoardCoordinations(int x, int y){
        int xShift = pressedImage.getWidth() / 12;
        int yShift = pressedImage.getHeight() / 8;
        return new Dimension(x/xShift,y/yShift);
    }

}
