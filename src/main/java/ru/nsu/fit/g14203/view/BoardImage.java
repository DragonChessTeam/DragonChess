package ru.nsu.fit.g14203.view;

import net.coobird.thumbnailator.Thumbnails;
import ru.nsu.fit.g14203.engine.api.Piece;

import javax.imageio.ImageIO;
import java.awt.*;
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

    public void scale(int windowWidth, int windowHeight){

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

    public BufferedImage getImage(int windowWidth, int windowHeight, Piece[][] boardModel){ //add piece drawing
        scale(windowWidth, windowHeight);

        for(int i = 0; i < boardModel.length; i++){
            for(int j = 0; j < boardModel[0].length; j++){
                if(boardModel[i][j] != null){
                    drawPiece(i, j, "asd", boardModel[i][j].getColor());
                }
            }
        }

        /*Graphics g = pressedImage.createGraphics();
        int xShift, yShift;
        xShift = pressedImage.getWidth() / 12;
        yShift = pressedImage.getHeight() / 8;
        int xPosition = xShift * 1;
        int yPosition = yShift * 2;
        g.drawOval(xPosition , yPosition, yShift, xShift);
        System.out.println(xPosition + xShift + " " + (yPosition + yShift/2));*/
        return pressedImage;
    }

    public BufferedImage getImage(){
        return pressedImage;
    }

    public void drawPiece(int i, int j, String name, ru.nsu.fit.g14203.engine.api.utils.Color color){

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

}
