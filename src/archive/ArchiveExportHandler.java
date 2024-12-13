package org.berandev.byterover;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

// pageTreeModel -> page-tree.xml, pages

public class ArchiveExportHandler {
    private static StructureTreeModel pageTreeModel;
    private static ProjectArchive projectArchive;

    public static void exportArchive(ProjectModel model, String path) throws Exception {
        pageTreeModel = model.getPageTreeModel();
        projectArchive = new ProjectArchive();
        buildPageTree();
    }

    private static void buildPageTree() throws Exception {
        StructureTreeNode rootNode = (StructureTreeNode) pageTreeModel.getRoot();
        Document document = new Document(buildPageTreeNode(rootNode));
        XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
        outputter.output(document, System.out);
        //projectArchive.put(parsedDocument)
    }

    private static Element buildPageTreeNode(StructureTreeNode node){
        Element nodeElement = new Element(node.isShowable() ? "page" : "group");
        nodeElement.setAttribute("name", node.getName());
        if (pageTreeModel.isDefault(node)){
            nodeElement.setAttribute("default", "true");
        }
        if (node.isShowable()){
            PageNode pageNode = (PageNode) node;
            PageModel pageModel = pageNode.getPageModel();
            if (pageModel.hasContent()){
                nodeElement.addContent(buildContent(pageModel));
            }
        }
        else {
            GroupNode groupNode = (GroupNode) node;
            for (StructureTreeNode child : groupNode.getChildren()){
                nodeElement.addContent(buildPageTreeNode(child));
            }
        }
        return nodeElement;
    }

    private static Element buildContent(PageModel model){
        Element contentElement = new Element("content");
        String fileName = projectArchive.putEntry(model.getContentEntry());
        contentElement.setAttribute("file", fileName);
        contentElement.setAttribute("type", model.getContentType());
        return contentElement;
    }
}

