package org.berandev.byterover;

class ContentEntry extends ArchiveEntry {
    private String contentType;

    public ContentEntry(byte[] content, String contentType){
        this.contentType = contentType;
        super(content);
    }

    public getType(){
        return contentType;
    }
}

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
}

