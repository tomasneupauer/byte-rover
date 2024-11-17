package org.berandev.byterover;

import javax.swing.tree.DefaultMutableTreeNode;

public class ProjectTreeNode extends DefaultMutableTreeNode {
    private String nodeName;

    public ProjectTreeNode(String name){
        super();
        nodeName = name;
    }

    @Override
    public String toString(){
        return nodeName;
    }

    public boolean isPage(){
        return getUserObject() != null;
    }
}

