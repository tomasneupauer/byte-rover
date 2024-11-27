package org.berandev.byterover;

import javax.swing.JFileChooser;
import java.awt.event.ActionEvent;

public class OpenProjectAction extends MenuItemAction {
    private AppControl appControl;

    public OpenProjectAction(AppControl control){
        super(ResourceLoader.getString("action.openProject"));
        appControl = control;
    }

    public void actionUpdate(){}

    public void actionPerformed(ActionEvent event){
        JFileChooser fileChooser = new JFileChooser();
        int state = fileChooser.showOpenDialog(appControl.getView());
        if (state == JFileChooser.APPROVE_OPTION){
            String selectedPath = fileChooser.getSelectedFile().getPath();
            appControl.importProjectModel(selectedPath);
        }
    }
}

