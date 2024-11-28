package org.berandev.byterover;

import javax.swing.JFileChooser;
import java.awt.event.ActionEvent;

public class SaveProjectAction extends MenuItemAction {
    private AppControl appControl;

    public SaveProjectAction(AppControl control){
        super(ResourceLoader.getString("action.saveProject"));
        appControl = control;
    }

    public void actionUpdate(){}

    public void actionPerformed(ActionEvent event){
        JFileChooser fileChooser = new JFileChooser();
        int state = fileChooser.showSaveDialog(appControl.getView());
        if (state == JFileChooser.APPROVE_OPTION){
            String selectedPath = fileChooser.getSelectedFile().getPath();
            appControl.exportProjectModel(selectedPath);
        }
    }
}

