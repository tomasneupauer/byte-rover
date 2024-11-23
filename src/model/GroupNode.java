package org.berandev.byterover;

import javax.swing.tree.DefaultMutableTreeNode;

public class GroupNode extends DefaultMutableTreeNode implements StructureTreeNode {
    public GroupNode(String name){
        super(name);
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

