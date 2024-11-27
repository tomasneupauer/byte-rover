package org.berandev.byterover;

import com.formdev.flatlaf.FlatLightLaf;

public class AppControl {
    public static final String READER_CARD = "READER_CARD";

    private AppView appView;
    private StructureTreeModel projectModel;
    private ActionModel actionModel;
    private MenuFactory menuFactory;
    private Controller readerControl;

    public AppControl(){
        FlatLightLaf.setup();
        appView = new AppView();
        actionModel = new ActionModel();
        menuFactory = new MenuFactory(actionModel);
        readerControl = new ReaderControl(this);
        actionModel.newMenuActions();
    }

    public void importProjectModel(String path){
        try {
            projectModel = ArchiveImportHandler.importArchive(path);
        }
        catch (Exception exception){
            exception.printStackTrace();
            return;
        }
        readerControl.loadControl();
        appView.switchCard(READER_CARD);
    }

    public StructureTreeModel getProjectModel(){
        return projectModel;
    }

    public ActionModel getActionModel(){
        return actionModel;
    }

    public MenuFactory getMenuFactory(){
        return menuFactory;
    }

    public void initControl(){
        appView.getJMenuBar().add(menuFactory.newMenu(ActionModel.EDIT_MENU));
        appView.add(readerControl.getView(), READER_CARD);
        appView.setVisible(true);
        readerControl.initControl();

        importProjectModel("projects/sample_project.project");
        appView.pack();
    }
}

