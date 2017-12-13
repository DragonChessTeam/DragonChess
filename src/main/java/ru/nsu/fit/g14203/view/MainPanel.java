package ru.nsu.fit.g14203.view;

import ru.nsu.fit.g14203.view.desks.SingleDesk;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel{

    private DeskSwiper deskSwiper;
    private static double sideShift = 0.1;


    public MainPanel(){

        setLayout(new BorderLayout());

        deskSwiper = new DeskSwiper(new SingleDesk("resources/redboard.jpg", 600, 400),
                new SingleDesk("resources/greenboard.jpg", 600, 400),
                new SingleDesk("resources/blueboard.jpg", 600, 400));
        //redDesk = new SingleDesk("resources/redboard.jpg", 600, 400);

/*        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                redBoard.scale(e.getComponent().getWidth(), e.getComponent().getHeight());
            }
        });*/
    }

    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawImage(deskSwiper.getDesk().getDeskImage(this.getWidth(), this.getHeight()), 0, 0, this);
    }

}
