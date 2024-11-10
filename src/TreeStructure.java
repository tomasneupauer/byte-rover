package org.berandev.byterover;

import java.util.Map;

public interface TreeStructure {
    default void insertTreeNode(String name, String parent){
        getTreeNodes().put(name, parent);
    }

    default void updateTreeNode(String name, String parent){
        getTreeNodes().replace(name, parent);
    }

    default void renameTreeNode(String oldName, String newName){
        getTreeNodes().put(newName, getTreeNodes().remove(oldName));
    }

    default void removeTreeNode(String name){
        getTreeNodes().remove(name);
    }

    default String[] getTreeNodeNames(){
        return getTreeNodes().keySet().toArray(new String[0]);
    }

    default String getTreeNodeParentName(String name){
        return getTreeNodes().get(name);
    }

    String getTreeRootName();
    Map<String, String> getTreeNodes();
}

