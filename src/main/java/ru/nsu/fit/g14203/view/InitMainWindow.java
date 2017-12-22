package ru.nsu.fit.g14203.view;

import ru.nsu.fit.g14203.view.utils.MainFrame;

import javax.swing.*;
import java.awt.event.KeyEvent;

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

    private InitMainWindow()
    {
        super(windowWidth, windowHeight, "Dragonchess");
        mainPanel = new MainPanel();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(mainPanel);


        try
        {
            /*addSubMenu("Game", KeyEvent.VK_G);
            addMenuItem("Game/Connect", "Connect", KeyEvent.VK_C, null, "onConnect");
            addToolBarButton("Game/Connect");*/
            //addSubMenu("Edit", KeyEvent.VK_F);



        }
        catch(Exception e)
        {
            throw new RuntimeException(e);
        }

    }

    public void onConnect() {

    }


    public void onExit(){
        System.exit(0);
    }

    /**
     * Application main entry point
     * @param args command line arguments (unused)
     */
    public static void main(String[] args)
    {
        InitMainWindow mainFrame = new InitMainWindow();
        mainFrame.setVisible(true);
    }
}