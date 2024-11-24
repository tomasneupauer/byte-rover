package org.berandev.byterover;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.JMenuBar;

public class AppControl {
    public static final String READER_CARD = "READER_CARD";

    private AppView appView;
    private StructureTreeModel projectModel;
    private ReaderControl readerControl;

    public AppControl(){
        FlatLightLaf.setup();
        appView = new AppView();
        readerControl = new ReaderControl(this);
    }

    public void loadProjectModel(String path){
        try {
            projectModel = ArchiveImportHandler.importArchive(path);
        }
        catch (Exception exception){
            exception.printStackTrace();
            return;
        }
        readerControl.initControl();
        appView.switchCard(READER_CARD);
    }

    public StructureTreeModel getProjectModel(){
        return projectModel;
    }

    public JMenuBar getMenuBar() {
        return appView.getJMenuBar();
    }

    public void initControl(){
        appView.add(readerControl.getView(), READER_CARD);
        loadProjectModel("projects/sample_project.project");
        appView.setVisible(true);
        appView.pack();
    }
}

