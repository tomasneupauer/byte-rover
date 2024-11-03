package org.berandev.byterover;

import javax.swing.tree.TreeModel;
import javax.swing.tree.DefaultTreeModel;
import javax.seing.tree.DefaultMutableTreeNode;

class StructureNode {
    protected String nodeName;
    protected StructureNode parentNode;

    public StructureNode (String name, StructureNode parent) {
        nodeName = name;
        parentNode = parent;
    }

    public setName(String name){
        nodeName = name;
    }

    public setParentNode(StructureNode node){
        parentNode = node;
    }

    public getName(){
        return nodeName;
    }

    public getParentNode(){
        return parentNode;
    }
}

class PageNode extends StructureNode {
    private byte[] pageContent;
    private String pageType;

    public PageNode(String name, StructureNode parent){
        super(name, parent);
    }

    public setPageContent(byte[] content, String type){
        pageContent = content;
        pageType = type;
    }
}

public class ProjectModel {
    private List<PageNode> pageNodes;
    private List<GroupNode> groupNodes;

    public ProjectModel (String projectArchivePath) throws Exception {
        ArchiveHandler importArchiveHandler = new ArchiveHandler(projectArchivePath);
        projectStructure = archiveHandler.loadProjectStructure();

        pageContentType = "text/html";
        pageContent = "<html><h1>Editor Pane</h1><p>Lorem sum, dolor sit amet</p></html>";
    }

    public Object[] getProjectStructure(){
        return projectStructure;
    }

    public String getPageContentType(){
        return pageContentType;
    }

    public String getPageContent(){
        return pageContent;
    }
}

