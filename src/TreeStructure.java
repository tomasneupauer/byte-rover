package org.berandev.byterover;

import java.util.Map;

public interface TreeStructure {
    default void addTreeNode(String nodeName, String parentName){
        getTreeNodes().put(nodeName, parentName);
    }

    default String[] getTreeNodeNames(){
        return getTreeNodes().keySet().toArray(new String[0]);
    }

    default String getTreeNodeParentName(String nodeName){
        return getTreeNodes().get(nodeName);
    }

    String getTreeRootName();

    //helper
    Map<String, String> getTreeNodes();
}

