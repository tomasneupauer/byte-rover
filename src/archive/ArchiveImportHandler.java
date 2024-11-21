package org.berandev.byterover;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ArchiveImportHandler {
    private static ProjectArchive projectArchive;
    private static StructureTreeModel projectModel;
    private static StructureTreeNode defaultNode;

    public static StructureTreeModel importArchive(String archivePath) throws Exception {
        projectArchive = ZipArchiveHandler.loadZipArchive(archivePath);
        String descriptorFilename = ResourceLoader.getFilename("projectDescriptor");
        if (!projectArchive.containsEntry(descriptorFilename)){
            throw new Exception(ResourceLoader.getException("descriptorNotFound"));
        }
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        ByteArray descriptorEntry = projectArchive.getEntry(descriptorFilename);
        Document projectDescriptor = docBuilder.parse(descriptorEntry.toInputStream());
        projectDescriptor.getDocumentElement().normalize();
        return buildProjectTreeModel(projectDescriptor.getDocumentElement());
    }

    private static StructureTreeModel buildProjectTreeModel(Element projectRoot){
        projectModel = new StructureTreeModel(buildProjectTreeNode(projectRoot));
        projectModel.setDefaultNode(defaultNode);
        return projectModel;
    }

    private static StructureTreeNode buildProjectTreeNode(Element element){
        ProjectTreeNode treeNode = new ProjectTreeNode(element.getAttribute("name"));
        if (element.getNodeName().equals("page")){
            if (element.getAttribute("default").equals("true")){
                defaultNode = treeNode;
            }
            treeNode.setPageModel(new PageModel());
            buildPageContent(treeNode.getPageModel(), element);
        }
        else {
            for (Element childElement : getValidChildren(element)){
                treeNode.add(buildProjectTreeNode(childElement));
            }
        }
        return treeNode;
    }

    private static void buildPageContent(PageModel page, Element element){
        Element content = (Element) element.getElementsByTagName("content").item(0);
        if (content == null){
            return;
        }
        String pageFile = content.getAttribute("file");
        String pageType = content.getAttribute("type");
        if (projectArchive.containsEntry(pageFile)){
            page.setContent(projectArchive.getEntry(pageFile), pageType);
        }
    }

    private static Element[] getValidChildren(Element element){
        List<Element> childElements = new ArrayList<>();
        NodeList childNodes = element.getChildNodes();
        for (int i=0; i<childNodes.getLength(); i++){
            String nodeName = childNodes.item(i).getNodeName();
            if (nodeName.equals("group") || nodeName.equals("page")){
                childElements.add((Element) childNodes.item(i));
            }
        }
        return childElements.toArray(new Element[0]);
    }
}

