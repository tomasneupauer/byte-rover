package org.berandev.byterover;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import java.awt.CardLayout;

public class AppView extends JFrame{
    private CardLayout cardLayout;
    private JMenuBar menuBar;

    public AppView(){
        cardLayout = new CardLayout();
        menuBar = new JMenuBar();
        initComponents();
    }

    public void switchCard(String name){
        cardLayout.show(getContentPane(), name);
    }

    private void initComponents(){
        setTitle(ResourceLoader.getString("app.title"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(cardLayout);
        setJMenuBar(menuBar);
    }
}
