package org.berandev.byterover;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.event.ActionListener;

public class StructureTreeModel extends DefaultTreeModel {
    private StructureTreeNode defaultNode;
    private StructureTreeNode currentNode;
    private ActionListener currentNodeListener;

    public StructureTreeModel(StructureTreeNode root){
        super(root);
    }

    public StructureTreeNode getDefaultNode(){
        return defaultNode;
    }

    public StructureTreeNode getCurrentNode(){
        return currentNode;
    }

    public TreePath getDefaultPath(){
        return new TreePath(defaultNode.getPath());
    }

    public TreePath getCurrentPath(){
        return new TreePath(currentNode.getPath());
    }

    public boolean isDefault(StructureTreeNode node){
        return defaultNode.equals(node);
    }

    public boolean isCurrent(StructureTreeNode node){
        return currentNode.equals(node);
    }

    public void setDefaultNode(StructureTreeNode node){
        defaultNode = node;
    }

    public void setCurrentNode(StructureTreeNode node){
        currentNode = node;
        if (currentNodeListener != null){
            currentNodeListener.actionPerformed(null);
        }
    }

    public void addCurrentNodeListener(ActionListener listener){
        currentNodeListener = listener;
    }
}
