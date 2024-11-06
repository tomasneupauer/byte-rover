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
            String path = "projects/sample_project.project";
            ProjectModel projectModel = ArchiveImportHandler.importArchive(path);
            PageController pageController = new PageController(projectModel);
            
            add(pageController.getView(), "PageView");
        } catch (Exception e){
            e.printStackTrace();
        }

        pack();
        setVisible(true);
    }
}

