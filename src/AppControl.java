package org.berandev.byterover;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.JFrame;

public class AppControl {
    private JFrame appView;
    private ProjectModel projectModel;
    private ReaderControl readerControl;

    public AppControl(){
        FlatLightLaf.setup();
        appView = new AppView();
        readerControl = new ReaderControl();
        initControl();
    }

    private void initControl(){
        appView.add(readerControl.getView(), "Reader");
        appView.pack();
        appView.setVisible(true);
        try {
            String path = "projects/sample_project.project";
            projectModel = ArchiveImportHandler.importArchive(path);
            readerControl.initControl(projectModel);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

