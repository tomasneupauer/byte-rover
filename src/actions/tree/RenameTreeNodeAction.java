package org.berandev.byterover;

import java.awt.event.ActionEvent;

public class RenameTreeNodeAction extends MenuItemAction {
    private StructureTree structureTree;

    public RenameTreeNodeAction(StructureTree tree){
        super(ResourceLoader.getString("action.renameBase"));
        structureTree = tree;
    }

    public void actionUpdate(){
        switch (structureTree.getContextNode().getType()){
            case StructureTreeNode.PROJECT_NODE:
                setName(ResourceLoader.getString("action.renameProject"));
                break;
            case StructureTreeNode.GROUP_NODE:
                setName(ResourceLoader.getString("action.renameGroup"));
                break;
            case StructureTreeNode.PAGE_NODE:
                setName(ResourceLoader.getString("action.renamePage"));
                break;
            default:
                setName(ResourceLoader.getString("action.renameBase"));
        }
    }

    public void actionPerformed(ActionEvent event){   
        structureTree.startEditingAtPath(structureTree.getContextPath());
    }
}

