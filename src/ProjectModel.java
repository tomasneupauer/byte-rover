package org.berandev.byterover;

//import java.util.Arrays;

public class ProjectModel {
    private Object[] projectStructureRoot;

    public ProjectModel (){
        projectStructureRoot = new Object[] {
            "Project Name",
            new Object[] {
                "Group",
                "Element 1",
                "Element 2"},
            "Another Page"};
    }

    public Object[] getProjectStructureRoot(){
        return projectStructureRoot;
    }
}

