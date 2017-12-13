package ru.nsu.fit.g14203.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainPanel extends JPanel{

    private boardImage redBoard;
    private boardImage greenBoard;
    private boardImage blueBoard;
    private static double sideShift = 0.1;


    public MainPanel(){

        setLayout(new BorderLayout());

        redBoard = new boardImage("resources/redboard.jpg",600, 400);

/*        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                redBoard.scale(e.getComponent().getWidth(), e.getComponent().getHeight());
            }
        });*/
    }

    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawImage(redBoard.getImage(this.getWidth(), this.getHeight()), 0, 0, this);
    }

}
