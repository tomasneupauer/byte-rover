package org.berandev.byterover;

import javax.swing.tree.DefaultMutableTreeNode;

public class ProjectTreeNode extends DefaultMutableTreeNode {
    private PageModel pageModel;

    public ProjectTreeNode(String name){
        super(name);
    }

    public void setPageModel(PageModel page){
        pageModel = page;
    }

    public PageModel getPageModel(){
        return pageModel;
    }

    public boolean isPage(){
        return pageModel != null;
    }
}

