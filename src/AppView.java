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
        
        try {
        add(new PageView(new ProjectModel("/data/Workspace/byte-rover/projects/sample_project.project")), "PageView");
        //appViewLayout.show(this, "PageView");
        } catch (Exception e){
            e.printStackTrace();
        }

        pack();
        setVisible(true);
    }
}

