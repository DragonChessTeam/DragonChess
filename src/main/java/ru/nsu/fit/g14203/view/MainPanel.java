package ru.nsu.fit.g14203.view;

import ru.nsu.fit.g14203.engine.api.Observer;
import ru.nsu.fit.g14203.engine.api.utils.Dot3D;
import ru.nsu.fit.g14203.engine.api.utils.UpdateEntry;
import ru.nsu.fit.g14203.view.desks.SingleDesk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class MainPanel extends JPanel implements Observer{

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
                    case KeyEvent.VK_RIGHT: //rightSwipe();
                        deskSwiper.incrementIndex();
                        //deskSwiper.getDesk().drawPiece(1,1);
                        repaint();
                        break;
                    case KeyEvent.VK_LEFT: deskSwiper.decrementIndex();
                        deskSwiper.getDesk().drawPiece(2,2);
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

    @Override
    public void update(List<UpdateEntry> boards) {
        boards.forEach(s -> {
            Dot3D firstDot = s.fullPath.get(0);
            Dot3D lastDot = s.fullPath.get(s.fullPath.size() - 1);
            if(s.pieceToPlace == null && s.fullPath.size() == 1) { //удаление
                deskSwiper.getDesk(firstDot.z).deletePiece(firstDot.y, firstDot.x);
            }
            if(s.pieceToPlace != null && s.fullPath.size() == 1){ //добавление
                deskSwiper.getDesk(firstDot.z).addPiece(firstDot.y, firstDot.x, s.pieceToPlace);
            }
            if(s.pieceToPlace == null && s.fullPath.size() > 1){ //движение
                deskSwiper.getDesk(firstDot.z).deletePiece(firstDot.y, firstDot.x);
                deskSwiper.getDesk(lastDot.z).addPiece(lastDot.y, lastDot.x, s.pieceToPlace);
            }
        });
        repaint();
    }

/*    private void rightSwipe(){
        int swipeSpeed = 5;
        for(int i = 0; i <= this.getWidth(); i = i + swipeSpeed) {
            this.getGraphics().drawImage(deskSwiper.getDesk().getDeskImage(this.getWidth(), this.getHeight()), -i, 0, this);
            this.getGraphics().drawImage(deskSwiper.getNextDesk().getDeskImage(this.getWidth(), this.getHeight()), this.getWidth() - i, 0, this);
            swipeSpeed = i + 1;
        }
    }*/

}
