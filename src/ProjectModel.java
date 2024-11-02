package org.berandev.byterover;

//import java.util.Arrays;

public class ProjectModel {
    private Object[] projectStructureRoot;
    private String pageContentType;
    private String pageContent;

    public ProjectModel (){
        projectStructureRoot = new Object[] {
            "Project Name",
            new Object[] {
                "Group",
                "Element 1",
                "Element 2"},
            "Another Page"};

        pageContentType = "text/html";
        pageContent = "<html><h1>Editor Pane</h1><p>Lorem ipsum, dolor sit amet</p></html>";
    }

    public Object[] getProjectStructureRoot(){
        return projectStructureRoot;
    }

    public String getPageContentType(){
        return pageContentType;
    }

    public String getPageContent(){
        return pageContent;
    }
}

