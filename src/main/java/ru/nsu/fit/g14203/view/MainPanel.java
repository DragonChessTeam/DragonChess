package ru.nsu.fit.g14203.view;

import ru.nsu.fit.g14203.view.desks.SingleDesk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainPanel extends JPanel{

    private DeskSwiper deskSwiper;
    private static double sideShift = 0.1;


    public MainPanel(){

        setLayout(new BorderLayout());
        setFocusable(true);
        requestFocusInWindow();

        deskSwiper = new DeskSwiper(new SingleDesk("resources/redboard.jpg", 600, 400),
                new SingleDesk("resources/greenboard.jpg", 600, 400),
                new SingleDesk("resources/blueboard.jpg", 600, 400));

        addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {

                switch (e.getKeyCode()){
                    case KeyEvent.VK_RIGHT: rightSwipe();
                        deskSwiper.incrementIndex();
                        repaint();
                        break;
                    case KeyEvent.VK_LEFT: deskSwiper.decrementIndex();
                        repaint();
                        break;
                    default: break;
                }

            }
        });

    }

    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawImage(deskSwiper.getDesk().getDeskImage(this.getWidth(), this.getHeight()), 0, 0, this);
    }

    private void rightSwipe(){
        int swipeSpeed = 5;
        for(int i = 0; i <= this.getWidth(); i = i + swipeSpeed) {
            this.getGraphics().drawImage(deskSwiper.getDesk().getDeskImage(this.getWidth(), this.getHeight()), -i, 0, this);
            this.getGraphics().drawImage(deskSwiper.getNextDesk().getDeskImage(this.getWidth(), this.getHeight()), this.getWidth() - i, 0, this);
            swipeSpeed = i + 1;
        }
    }

}
