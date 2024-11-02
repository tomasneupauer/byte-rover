package org.berandev.byterover;

public class ProjectModel {
    private Object[] projectStructure;
    private String pageContentType;
    private String pageContent;

    public ProjectModel (String projectArchivePath) throws Exception {
        ArchiveHandler archiveHandler = new ArchiveHandler(projectArchivePath);
        projectStructure = archiveHandler.getEntryNames();

        pageContentType = "text/html";
        pageContent = "<html><h1>Editor Pane</h1><p>Lorem ipsum, dolor sit amet</p></html>";
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

