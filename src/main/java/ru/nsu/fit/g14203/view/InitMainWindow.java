package ru.nsu.fit.g14203.view;

import ru.nsu.fit.g14203.view.utils.MainFrame;

import javax.swing.*;

/**
 * Main window class
 * @author Ivan Pospelov
 */
public class InitMainWindow extends MainFrame {


    /**
     * Default constructor to create main window
     */
    //private JScrollPane scrollPane;
    private static int windowWidth = 610;
    private static int windowHeight = 600;
    private MainPanel mainPanel;

    private InitMainWindow()
    {
        super(windowWidth, windowHeight, "Dragonchess");
        mainPanel = new MainPanel();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(mainPanel);


        //controller.drawFunctionMap();


        try
        {
            //addSubMenu("File", KeyEvent.VK_F);
            //addSubMenu("Edit", KeyEvent.VK_F);



        }
        catch(Exception e)
        {
            throw new RuntimeException(e);
        }

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