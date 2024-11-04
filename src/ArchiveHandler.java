package org.berandev.byterover;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.util.HashMap;
import java.util.Map;

public class ArchiveHandler {
    private static Map<String, ByteArray> archiveEntries;
    private static ProjectModel projectModel;

    public static ProjectModel loadArchive(String archivePath) throws Exception {
        archiveEntries = ZipHandler.loadZip(archivePath);
        String descriptorFilename = ResourceLoader.getFilename("projectDescriptor");
        if (!archiveEntries.containsKey(descriptorFilename)){
            throw new Exception(ResourceLoader.getException("descriptorNotFound"));
        }
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        StreamEntry descriptorEntry = (StreamEntry) archiveEntries.get(descriptorFilename);
        Document projectDescriptor = docBuilder.parse(descriptorEntry.toInputStream());
        return buildProjectModel(projectDescriptor.getDocumentElement());
    }

    private static ProjectModel buildProjectModel(Element projectElement){
        projectModel = new ProjectModel(projectElement.getAttribute("name"));
        buildProjectGroups(projectElement);
        buildProjectPages(projectElement);
        return projectModel
    }

    private static void buildProjectGroups(Element projectElement){
        NodeList groupElements = projectElement.getElementsByTagName("group");
        for (int i=0; i<groupElements.getLength(); i++){
            Element groupElement = (Element) groupElements.item(i);
            Element parentElement = (Element) group.getParentNode();
            String groupName = groupElement.getAttribute("name");
            String parentName = parentElement.getAttribute("name");
            projectModel.addTreeNode(groupName, parentName);
        }
    }

    private static void buildProjectPages(Element projectElement){
        NodeList pageElements = projectElement.getElementsByTagName("page");
        for (int i=0; i<pageElements.getLength(); i++){
            Element pageElement = (Element) pageElements.item(i);
            Element parentElement = (Element) pageElement.getParentNode();
            String pageName = pageElement.getAttribute("name");
            String parentName = parentElement.getAttribute("name")
            projectModel.addTreeNode(pageName, parentName);
            buildPageContent(pageElement);
        }
    }

    private static void buildPageContent(Element pageElement){
        NodeList contentElements =pageElement.getElementsByTagName("content");
        if (contentElements.getLength > 0){
            Element contentElement = (Element) contentElements.item(0);
            String pageName = pageElement.getAttribute("name");
            String pageType = contentElement.getAttribute("type");
            String pageFile = contentElement.getAttribute("file");
            if (archiveEntries.containsKey(pageFile)){
                byte[] pageContent = archiveEntries.get(pageFile).getContent();
                projectModel.addPage(pageName, new ContentEntry(pageContent, pageType));
            }
        }
    }
}

