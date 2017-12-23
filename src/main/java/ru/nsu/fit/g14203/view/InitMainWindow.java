package ru.nsu.fit.g14203.view;

import ru.nsu.fit.g14203.engine.api.Engine;
import ru.nsu.fit.g14203.view.utils.MainFrame;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Main window class
 * @author Ivan Pospelov
 */
public class InitMainWindow extends MainFrame {


    /**
     * Default constructor to create main window
     */
    private static int windowWidth = 610;
    private static int windowHeight = 600;
    private MainPanel mainPanel;

    public InitMainWindow(Engine engine)
    {
        super(windowWidth, windowHeight, "Dragonchess");
        mainPanel = new MainPanel(engine);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(mainPanel);
        setVisible(true);

        try
        {
            //addSubMenu("Game", 0);
            //addMenuItem("Game/Connect", "Connect", KeyEvent.VK_C, null, "onConnect");
            //addToolBarButton("Game/Connect");
            //addSubMenu("Edit", KeyEvent.VK_F);



        }
        catch(Exception e)
        {
            throw new RuntimeException(e);
        }

    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }

    public void onConnect() {

    }


    public void onExit(){
        System.exit(0);
    }

     /*
    public static void main(String[] args)
    {
        InitMainWindow mainFrame = new InitMainWindow();
        mainFrame.setVisible(true);
    }*/
}