package org.berandev.byterover;

import java.awt.event.ActionEvent;
import javax.swing.tree.TreePath;

class CreateNewPageAction extends MenuItemAction {
    private StructureTreeModel treeModel;
    private StructureTree structureTree;

    public CreateNewPageAction(StructureTree tree){
        super(ResourceLoader.getString("action.createNewPage"));
        treeModel = (StructureTreeModel) tree.getModel();
        structureTree = tree;
    }

    public void actionUpdate(){
        StructureTreeNode contextNode = structureTree.getContextNode();
        setVisible(!contextNode.isShowable());
    }

    public void actionPerformed(ActionEvent event){
        StructureTreeNode contextNode = structureTree.getContextNode();
        String placeholder = ResourceLoader.getPlaceholder("newPage");
        ProjectTreeNode childNode = new ProjectTreeNode(placeholder);
        childNode.setPageModel(new PageModel());
        int childIndex = contextNode.getChildCount();
        treeModel.insertNodeInto(childNode, contextNode, childIndex);
        TreePath childPath = new TreePath(childNode.getPath());
        structureTree.setSelectionPath(childPath);
        structureTree.startEditingAtPath(childPath);
        treeModel.setCurrentNode(childNode);
    }
}

