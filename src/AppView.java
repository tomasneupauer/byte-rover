package org.berandev.byterover;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.JFrame;
import java.awt.CardLayout;

public class AppView {
    private JFrame frame;
    private CardLayout frameLayout;

    public AppView (){
        FlatLightLaf.setup();

        frame = new JFrame(ResourceLoader.getString("app.title"));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }

    public static void main(String[] args){
        new AppView();
    }
}

