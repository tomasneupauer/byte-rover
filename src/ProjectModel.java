package org.berandev.byterover;

import java.util.HashMap;
import java.util.Map;

public class ProjectModel implements TreeStructure, PageCollection{
    private Map<String, String> treeNodes;
    private Map<String, ByteArray> pageEntries;
    private Map<String, String> pageTypes;
    private String projectName;
    private String selectedPageName;

    public ProjectModel (String projectName){
        treeNodes = new HashMap<String, String>();
        pageEntries = new HashMap<String, ByteArray>();
        pageTypes = new HashMap<String, String>();
        this.projectName = projectName;
    }

    public void selectPage(String pageName){
        selectedPageName = pageName;
    }

    public String getTreeRootName(){
        return projectName;
    }

    public String getSelectedPageName(){
        return selectedPageName;
    };

    //helper
    public Map<String, String> getTreeNodes(){
        return treeNodes;
    }

    //helper
    public Map<String, ByteArray> getPageEntries(){
        return pageEntries;
    }

    //helper
    public Map<String, String> getPageTypes(){
        return pageTypes;
    }
}

