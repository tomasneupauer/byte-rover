package org.berandev.byterover;

import javax.swing.tree.DefaultMutableTreeNode;

public class ProjectTreeNode extends DefaultMutableTreeNode implements StructureTreeNode {
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

    public int getType(){
        if (isRoot()){
            return PROJECT_NODE;
        }
        if (!isShowable()){
            return GROUP_NODE;
        }
        return PAGE_NODE;
    }

    public boolean isShowable(){
        return pageModel != null;
    }
}

