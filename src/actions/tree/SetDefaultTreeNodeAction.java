package org.berandev.byterover;

import java.awt.event.ActionEvent;

class SetDefaultTreeNodeAction extends MenuItemAction {
    private StructureTreeModel treeModel;
    private StructureTree structureTree;

    public SetDefaultTreeNodeAction(StructureTree tree){
        super(ResourceLoader.getString("action.setDefault"));
        treeModel = (StructureTreeModel) tree.getModel();
        structureTree = tree;
        setSeparated(true);
    }

    public void actionUpdate(){
        StructureTreeNode contextNode = structureTree.getContextNode();
        setVisible(contextNode.isShowable());
        setEnabled(!treeModel.isDefault(contextNode));
        setHint(isEnabled() ? "" : ResourceLoader.getHint("currentlyDefault"));
    }

    public void actionPerformed(ActionEvent event){
        treeModel.setDefaultNode(structureTree.getContextNode());
    }
}

