package org.berandev.byterover;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.util.ArrayList;
import java.util.List;

public class ArchiveExportHandler {
    private static DocumentBuilder documentBuilder;
    private static ProjectArchive projectArchive;
    private static StructureTreeModel pageTreeModel;

    public static void exportArchive(ProjectModel model, String path) throws Exception {
        documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        projectArchive = new ProjectArchive();
        pageTreeModel = model.getPageTreeModel();
        buildPageTree();
    }

    private static void buildPageTree(){
        Document document = documentBuilder.newDocument();
        StructureTreeNode rootNode = (StructureTreeNode) pageTreeModel.getRoot();
        document.appendChild(buildPageTreeNode(rootNode));
        //projectArchive.put(parsedDocument)
    }

    private static Element buildPageTreeNode(StructureTreeNode node){
        
    }
}

