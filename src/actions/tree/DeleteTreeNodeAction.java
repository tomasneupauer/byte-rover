package org.berandev.byterover;

import java.awt.event.ActionEvent;

public class DeleteTreeNodeAction extends MenuItemAction {
    private StructureTree structureTree;

    public DeleteTreeNodeAction(StructureTree tree){
        super(ResourceLoader.getString("action.deleteBase"));
        structureTree = tree;
    }

    public void actionUpdate(){
        StructureTreeModel treeModel = (StructureTreeModel) structureTree.getModel();
        StructureTreeNode contextNode = structureTree.getContextNode();
        setVisible(!contextNode.isRoot());
        if (treeModel.isDefault(contextNode)){
            setHint(ResourceLoader.getHint("currentlyDefault"));
            setEnabled(false);
        }
        else if (!contextNode.isLeaf()){
            setHint(ResourceLoader.getHint("groupNotEmpty"));
            setEnabled(false);
        }
        else {
            setHint("");
            setEnabled(true);
        }
        switch (contextNode.getType()){
            case StructureTreeNode.GROUP_NODE:
                setName(ResourceLoader.getString("action.deleteGroup"));
                break;
            case StructureTreeNode.PAGE_NODE:
                setName(ResourceLoader.getString("action.deletePage"));
                break;
            default:
                setName(ResourceLoader.getString("action.deleteBase"));
        }
    }

    public void actionPerformed(ActionEvent event){
        StructureTreeModel treeModel = (StructureTreeModel) structureTree.getModel();
        StructureTreeNode contextNode = structureTree.getContextNode();
        if (treeModel.isCurrent(contextNode)){
            treeModel.setCurrentNode(treeModel.getDefaultNode());
            structureTree.setSelectionPath(treeModel.getCurrentPath());
        }
        treeModel.removeNodeFromParent(contextNode);
    }
}

