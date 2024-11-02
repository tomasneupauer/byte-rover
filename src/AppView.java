package org.berandev.byterover;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.JFrame;
import java.awt.CardLayout;

public class AppView extends JFrame{
    private CardLayout appViewLayout;

    public AppView (){
        FlatLightLaf.setup();

        setTitle(ResourceLoader.getString("app.title"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        appViewLayout = new CardLayout();
        setLayout(appViewLayout);
        
        add(new PageView(new ProjectModel()), "PageView");
        //appViewLayout.show(this, "PageView");

        pack();
        setVisible(true);
    }
}

