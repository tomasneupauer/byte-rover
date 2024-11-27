package org.berandev.byterover;

import javax.swing.tree.DefaultMutableTreeNode;

public class PageNode extends DefaultMutableTreeNode implements StructureTreeNode {
    private PageModel pageModel;

    public PageNode(String name){
        super(name);
        pageModel = new PageModel();
    }

    public PageModel getPageModel(){
        return pageModel;
    }

    public int getType(){
        return PAGE_NODE;
    }

    public boolean isShowable(){
        return true;
    }
}

