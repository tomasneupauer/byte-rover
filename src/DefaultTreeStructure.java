package org.berandev.byterover;

import java.util.Map;

public interface DefaultTreeStructure extends TreeStructure {
    @Override
    default void insertTreeNode(String nodeName, String parentName){
        getTreeNodes().put(nodeName, parentName);
    }

    @Override
    default void updateTreeNode(String nodeName, String parentName){
        getTreeNodes().replace(nodeName, parentName);
    }

    @Override
    default void renameTreeNode(String nodeName, String newName){
        if (isTreeNode(nodeName)){
            getTreeNodes().put(newName, getTreeNodes().remove(nodeName));
        }
    }

    @Override
    default void removeTreeNode(String nodeName){
        getTreeNodes().remove(nodeName);
    }

    @Override
    default void insertRootNode(String rootName){
        getTreeNodes().put(rootName, null);
    }

    @Override
    default String[] getTreeNodeNames(){
        return getTreeNodes().keySet().toArray(new String[0]);
    }

    @Override
    default String getTreeNodeParentName(String nodeName){
        return getTreeNodes().get(nodeName);
    }

    @Override
    default boolean isTreeNode(String nodeName){
        return getTreeNodes().containsKey(nodeName);
    }

    Map<String, String> getTreeNodes();
}

