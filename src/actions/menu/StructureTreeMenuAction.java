package org.berandev.byterover;

import java.awt.event.ActionEvent;

public class StructureTreeMenuAction extends MenuItemAction {
    public StructureTreeMenuAction(){
        super(ResourceLoader.getString("action.structureTreeMenu"));
        setSubActionKeys(new Object[]{
            ActionModel.CREATE_PAGE_NODE,
            ActionModel.CREATE_GROUP_NODE,
            ActionModel.SET_DEFAULT_TREE_NODE,
            ActionModel.RENAME_TREE_NODE,
            ActionModel.DELETE_TREE_NODE
        });
    }

    public void actionUpdate(){}
    public void actionPerformed(ActionEvent event){}
}

