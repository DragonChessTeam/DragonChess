package ru.nsu.fit.g14203.view;

import ru.nsu.fit.g14203.view.desks.SingleDesk;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel{

    private SingleDesk redDesk;
    private BoardImage greenBoard;
    private BoardImage blueBoard;
    private static double sideShift = 0.1;


    public MainPanel(){

        setLayout(new BorderLayout());

        redDesk = new SingleDesk("resources/redboard.jpg", 600, 400);

/*        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                redBoard.scale(e.getComponent().getWidth(), e.getComponent().getHeight());
            }
        });*/
    }

    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawImage(redDesk.getDeskImage(this.getWidth(), this.getHeight()), 0, 0, this);
    }

}
