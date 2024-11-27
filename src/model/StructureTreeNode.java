package org.berandev.byterover;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

public interface StructureTreeNode extends MutableTreeNode {
    int PROJECT_NODE = 0;
    int GROUP_NODE = 1;
    int PAGE_NODE = 2;

    public TreeNode[] getPath(); 
    public int getType();
    public boolean isRoot();
    public boolean isLeaf();
    public boolean isShowable();
}

