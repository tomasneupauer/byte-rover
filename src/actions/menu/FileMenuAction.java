package org.berandev.byterover;

import java.awt.event.ActionEvent;

public class FileMenuAction extends MenuItemAction {
    public FileMenuAction(){
        super(ResourceLoader.getString("action.fileMenu"));
        setSubActionKeys(new Object[]{
            ActionModel.OPEN_PROJECT
        });
    }

    public void actionUpdate(){}

    public void actionPerformed(ActionEvent event){}
}

