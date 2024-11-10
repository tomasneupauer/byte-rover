package org.berandev.byterover;

import java.util.HashMap;
import java.util.Map;

public class ProjectModel implements PageCollection, TreeStructure {
    private Map<String, PageModel> pageModels;
    private Map<String, String> treeNodes;
    private String selectedPageName;
    private String projectName;

    public ProjectModel(String name){
        pageModels = new HashMap<String, PageModel>();
        treeNodes = new HashMap<String, String>();
        selectedPageName = name;
        projectName = name;
        newPage(name, null);
    }

    public void newGroup(String name, String parent){
        insertTreeNode(name, parent);
    }

    public void newPage(String name, String parent){
        insertTreeNode(name, parent);
        insertPage(name);
    }

    @Override
    public void renameTreeNode(String oldName, String newName){
        TreeStructure.super.renameTreeNode(oldName, newName);
        renamePage(oldName, newName);
    }

    @Override
    public void removeTreeNode(String name){
        TreeStructure.super.removeTreeNode(name);
        removePage(name);
    }

    public void setSelectedPageName(String name){
        selectedPageName = name;
    }

    public String getSelectedPageName(){
        return selectedPageName;
    }

    public String getTreeRootName(){
        return projectName;
    }

    public Map<String, PageModel> getPageModels(){
        return pageModels;
    }

    public Map<String, String> getTreeNodes(){
        return treeNodes;
    }
}

