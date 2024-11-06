package org.berandev.byterover;

import java.util.HashMap;
import java.util.Map;

public class ProjectModel implements DefaultTreeStructure, DefaultPageCollection {
    // Project Model
    private Map<String, String> treeNodes;
    private Map<String, ByteArray> pageEntries;
    private Map<String, String> pageTypes;
    private String selectedPageName;
    private String projectName;

    public ProjectModel (String projectName){
        treeNodes = new HashMap<String, String>();
        pageEntries = new HashMap<String, ByteArray>();
        pageTypes = new HashMap<String, String>();
        this.projectName = projectName;
        insertRootNode(projectName);
    }

    // Tree Structure
    public String getTreeRootName(){
        return projectName;
    }

    // Page Collection
    public void selectPage(String pageName){
        selectedPageName = pageName;
    }

    public String getSelectedPageName(){
        return selectedPageName;
    };

    // Helper Accessors;
    public Map<String, String> getTreeNodes(){return treeNodes;}
    public Map<String, ByteArray> getPageEntries(){return pageEntries;}
    public Map<String, String> getPageTypes(){return pageTypes;}
}

