package org.berandev.byterover;

import java.awt.event.ActionEvent;

public class EditMenuAction extends MenuItemAction {
    public EditMenuAction(){
        super(ResourceLoader.getString("action.editMenu"));
        setSubActionKeys(new Object[]{
            ActionModel.STRUCTURE_TREE_MENU
        });
    }

    public void actionUpdate(){}
    public void actionPerformed(ActionEvent event){}
}

