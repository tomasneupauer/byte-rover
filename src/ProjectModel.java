package org.berandev.byterover;

import java.util.HashMap;
import java.util.Map;

public class ProjectModel implements TreeStructure, PageCollection{
    private Map<String, String> treeNodes;
    private Map<String, PageEntry> pageEntries;
    private String projectName;
    private String selectedPageName;

    public ProjectModel (String projectName){
        treeNodes = new HashMap<String, String>();
        pageEntries = new HashMap<String, PageEntry>();
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

    public Map<String, String> getTreeNodes(){
        return treeNodes;
    }

    public Map<String, PageEntry> getPageEntries(){
        return pageEntries;
    }
}

