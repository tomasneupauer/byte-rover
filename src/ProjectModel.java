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
        pageModels.add(pageName, new PageModel(contentEntry));
    }

    public addTreeNode(String groupName, String parentName){
        treeNodes.add(groupName, parentName);
    }

    public boolean isPage(String nodeName){
        return pageModels.containsKey(nodeName);
    }

    public String[] getTreeNodeNames(){
        return treeNodes.keySet.toArray();
    }

    public String getTreeNodeParentName(String nodeName){
        return treeNodes.get(nodeName)
    }
}

