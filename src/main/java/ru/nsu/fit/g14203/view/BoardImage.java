package ru.nsu.fit.g14203.view;

import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BoardImage {

    private BufferedImage originalImage;
    private BufferedImage pressedImage;

    public BoardImage(String path, int initalWidth, int initalHeight){

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

    public BufferedImage getImage(int windowWidth, int windowHeight){
        scale(windowWidth, windowHeight);
        return pressedImage;
    }

    public BufferedImage getImage(){
        return pressedImage;
    }

}
