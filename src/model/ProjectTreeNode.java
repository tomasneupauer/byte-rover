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

    public String getType(){
        if (isRoot()){
            return ResourceLoader.getString("model.node.project");
        }
        if (!isShowable()){
            return ResourceLoader.getString("model.node.group");
        }
        return ResourceLoader.getString("model.node.page");
    }

    public boolean isShowable(){
        return pageModel != null;
    }
}

