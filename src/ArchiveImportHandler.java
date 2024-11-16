package org.berandev.byterover;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArchiveImportHandler {
    private static Map<String, ByteArray> archiveEntries;
    private static ProjectTreeModel projectModel;
    private static ProjectTreeNode defaultNode;

    public static ProjectTreeModel importArchive(String archivePath) throws Exception {
        archiveEntries = ZipHandler.loadZip(archivePath);
        String descriptorFilename = ResourceLoader.getFilename("projectDescriptor");
        if (!archiveEntries.containsKey(descriptorFilename)){
            throw new Exception(ResourceLoader.getException("descriptorNotFound"));
        }
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        ByteArray descriptorEntry = archiveEntries.get(descriptorFilename);
        Document projectDescriptor = docBuilder.parse(descriptorEntry.toInputStream());
        projectDescriptor.getDocumentElement().normalize();
        return buildProjectTreeModel(projectDescriptor.getDocumentElement());
    }

    private static ProjectTreeModel buildProjectTreeModel(Element projectRoot){
        projectModel = new ProjectTreeModel(buildProjectTreeNode(projectRoot));
        if (defaultNode != null){
            projectModel.setDefaultSelection(defaultNode.getPath());
        }
        return projectModel;
    }

    private static ProjectTreeNode buildProjectTreeNode(Element element){
        ProjectTreeNode treeNode = new ProjectTreeNode(element.getAttribute("name"));
        if (element.getNodeName().equals("page")){
            if (element.getAttribute("default").equals("true")){
                defaultNode = treeNode;
            }
            treeNode.setUserObject(new PageModel());
            buildPageContent((PageModel) treeNode.getUserObject(), element);
            //buildPageCircuit((PageModel) treeNode.getUserObject(), element);
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
        if (archiveEntries.containsKey(pageFile)){
            page.setContent(archiveEntries.get(pageFile), pageType);
        }
    }

    private static Element[] getValidChildren(Element element){
        List<Element> childElements = new ArrayList<>();
        NodeList childNodes = element.getChildNodes();
        for (int i=0; i<childNodes.getLength(); i++){
            String nodeName = childNodes.item(0).getNodeName();
            if (nodeName.equals("group") || nodeName.equals("page")){
                childElements.add((Element) childNodes.item(0));
            }
        }
        return childElements.toArray(new Element[0]);
    }
}

