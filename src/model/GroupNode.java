package org.berandev.byterover;

import javax.swing.tree.DefaultMutableTreeNode;

public class GroupNode extends DefaultMutableTreeNode implements StructureTreeNode {
    public GroupNode(String name){
        super(name);
    }

    public StructureTreeNode[] getChildren(){
        StructureTreeNode[] children = new StructureTreeNode[getChildCount()];
        for (int i=0; i<getChildCount(); i++){
            children[i] = (StructureTreeNode) getChildAt(i);
        }
        return children;
    }

    public int getType(){
        if (isRoot()){
            return PROJECT_NODE;
        }
        return GROUP_NODE;
    }

    public boolean isShowable(){
        return false;
    }
}

