package ru.nsu.fit.g14203.view;

import ru.nsu.fit.g14203.engine.RealEngine;
import ru.nsu.fit.g14203.engine.api.Engine;
import ru.nsu.fit.g14203.engine.api.Observer;
import ru.nsu.fit.g14203.engine.api.utils.Dot3D;
import ru.nsu.fit.g14203.engine.api.utils.UpdateEntry;
import ru.nsu.fit.g14203.engine.initializer.NormalEngineInitializer;
import ru.nsu.fit.g14203.view.desks.SingleDesk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class MainPanel extends JPanel implements Observer{

    private DeskSwiper deskSwiper;
    private static double sideShift = 0.1;
    private Engine engine;


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
                        repaint();
                        break;
                    case KeyEvent.VK_LEFT: deskSwiper.decrementIndex();
                        repaint();
                        break;
                    default: break;
                }

            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                deskSwiper.handleClick(e.getX(), e.getY(), engine);
                repaint();
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                deskSwiper.handleMove(e.getX(), e.getY());
                repaint();
            }
        });


        engine = new RealEngine(new NormalEngineInitializer());
        engine.registerObserver(this);

    }

    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawImage(deskSwiper.getDesk().getDeskImage(this.getWidth(), this.getHeight()), 0, 0, this);
    }

    @Override
    public void update(List<UpdateEntry> boards) {
        deskSwiper.clearChosenFields();
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
                deskSwiper.getDesk(lastDot.z).addPiece(lastDot.y, lastDot.x, deskSwiper.getDesk(firstDot.z).getPiece(new Dimension(firstDot.x, firstDot.y)));
                deskSwiper.getDesk(firstDot.z).deletePiece(firstDot.y, firstDot.x);

            }
        });
        repaint();
    }

    public void setEngine(Engine engine){
        this.engine = engine;
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
