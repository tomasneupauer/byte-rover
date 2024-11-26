package org.berandev.byterover;

import java.awt.event.ActionEvent;
import javax.swing.tree.TreePath;

public class CreateGroupNodeAction extends MenuItemAction {
    private StructureTree structureTree;

    public CreateGroupNodeAction(StructureTree tree){
        super(ResourceLoader.getString("action.createGroupNode"));
        structureTree = tree;
    }

    public void actionUpdate(){
        StructureTreeNode contextNode = structureTree.getContextNode();
        setVisible(!contextNode.isShowable());
    }

    public void actionPerformed(ActionEvent event){
        StructureTreeModel treeModel = (StructureTreeModel) structureTree.getModel();
        StructureTreeNode contextNode = structureTree.getContextNode();
        String placeholder = ResourceLoader.getPlaceholder("newGroup");
        StructureTreeNode childNode = new GroupNode(placeholder);
        int childIndex = contextNode.getChildCount();
        treeModel.insertNodeInto(childNode, contextNode, childIndex);
        TreePath childPath = new TreePath(childNode.getPath());
        structureTree.setSelectionPath(childPath);
        structureTree.startEditingAtPath(childPath);
    }
}
