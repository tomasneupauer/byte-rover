package org.berandev.byterover;

public interface TreeStructure {
    //Mutators
    void insertTreeNode(String nodeName, String parentName);
    void updateTreeNode(String nodeName, String parentName);
    void renameTreeNode(String nodeName, String newName);
    void removeTreeNode(String nodeName);
    void insertRootNode(String rootName);
    //Accessors
    String[] getTreeNodeNames();
    String getTreeNodeParentName(String nodeName);
    String getTreeRootName();
    //Predicates
    boolean isTreeNode(String nodeName);
}

