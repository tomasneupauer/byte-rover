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
}

public class ProjectModel {
    private Map<String, PageModel> pageModels;
    private Map<String, String> treeNodes;

    public ProjectModel (String projectName){
        pageModels = new HadhMap<String, PageModel>();
        treeNodes = new HashMap<String, String>();
        treeNodes.put(projectName, null);
    }

    public addPage(String pageName, ContentEntry contentEntry){
        pageModels.put(pageName, new PageModel(contentEntry));
    }

    public addTreeNode(String groupName, String parentName){
        treeNodes.put(groupName, parentName);
    }

    public String[] getTreeNodeNames(){
        return treeNodes.keySet.toArray();
    }

    public String getTreeNodeParentName(String nodeName){
        return treeNodes.get(nodeName)
    }

    public boolean isTreeRoot(String nodeName){
        return treeNodes.get(nodeName) == null;
    }
}

