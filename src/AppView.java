package org.berandev.byterover;

import javax.swing.JFrame;
import java.awt.CardLayout;

public class AppView extends JFrame{
    private CardLayout cardLayout;

    public AppView(){
        cardLayout = new CardLayout();
        initComponents();
    }

    private void initComponents(){
        setTitle(ResourceLoader.getString("app.title"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(cardLayout);
    }
}
