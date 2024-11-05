package org.berandev.byterover;

import java.util.HashMap;
import java.util.Map;

class PageModel {
    private ContentEntry contentEntry;

    public PageModel(ContentEntry contentEntry){
        this.contentEntry = contentEntry;
    }

    public String toString(){
        return contentEntry.toString();
    }

    public String getType(){
        return contentEntry.getType();
    }
}

public class ProjectModel {
    private Map<String, PageModel> pageModels;
    private Map<String, String> treeNodes;
    private String projectName;
    private String currentPageName;
    private String defaultPageName;

    public ProjectModel (String projectName){
        this.projectName = projectName;
        pageModels = new HashMap<String, PageModel>();
        treeNodes = new HashMap<String, String>();
    }

    public void addPage(String pageName, ContentEntry contentEntry){
        pageModels.put(pageName, new PageModel(contentEntry));
    }

    public void addTreeNode(String nodeName, String parentName){
        treeNodes.put(nodeName, parentName);
    }

    public String[] getTreeNodeNames(){
        return treeNodes.keySet().toArray(new String[0]);
    }

    public String getTreeNodeParentName(String nodeName){
        return treeNodes.get(nodeName);
    }

    public String getTreeRootName(){
        return projectName;
    }

    public void setDefaultPageName(String defaultPageName){
        this.defaultPageName = defaultPageName;
    }

    public String getCurrentPageName(){
        return currentPageName != null ? currentPageName : defaultPageName;
    }

    public boolean isPage(String pageName){
        return pageModels.containsKey(pageName);
    }

    public boolean isValidModel(){
        return isPage(defaultPageName);
    }
}

