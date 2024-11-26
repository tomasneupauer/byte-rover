package org.berandev.byterover;

import java.awt.event.ActionEvent;

public class SetDefaultTreeNodeAction extends MenuItemAction {
    private StructureTree structureTree;

    public SetDefaultTreeNodeAction(StructureTree tree){
        super(ResourceLoader.getString("action.setDefault"));
        structureTree = tree;
        setSeparated(true);
    }

    public void actionUpdate(){
        StructureTreeModel treeModel = (StructureTreeModel) structureTree.getModel();
        StructureTreeNode contextNode = structureTree.getContextNode();
        setVisible(contextNode.isShowable());
        setEnabled(!treeModel.isDefault(contextNode));
        setHint(isEnabled() ? "" : ResourceLoader.getHint("currentlyDefault"));
    }

    public void actionPerformed(ActionEvent event){
        StructureTreeModel treeModel = (StructureTreeModel) structureTree.getModel();
        treeModel.setDefaultNode(structureTree.getContextNode());
    }
}

