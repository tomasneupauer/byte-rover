package org.berandev.byterover;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.util.HashMap;
import java.util.Map;

public class ArchiveImportHandler {
    private static Map<String, ByteArray> archiveEntries;
    private static ProjectModel projectModel;

    public static ProjectModel importArchive(String archivePath) throws Exception {
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
        return buildProjectModel(projectDescriptor.getDocumentElement());
    }

    private static ProjectModel buildProjectModel(Element projectElement) throws Exception{
        projectModel = new ProjectModel(projectElement.getAttribute("name"));
        buildProjectGroups(projectElement);
        buildProjectPages(projectElement);
        buildPageContent(projectElement);
        return projectModel;
    }

    private static void buildProjectGroups(Element projectElement){
        NodeList groupElements = projectElement.getElementsByTagName("group");
        for (int i=0; i<groupElements.getLength(); i++){
            Element groupElement = (Element) groupElements.item(i);
            Element parentElement = (Element) groupElement.getParentNode();
            String groupName = groupElement.getAttribute("name");
            String parentName = parentElement.getAttribute("name");
            projectModel.newGroup(groupName, parentName);
        }
    }

    private static void buildProjectPages(Element projectElement){
        NodeList pageElements = projectElement.getElementsByTagName("page");
        for (int i=0; i<pageElements.getLength(); i++){
            Element pageElement = (Element) pageElements.item(i);
            Element parentElement = (Element) pageElement.getParentNode();
            String pageName = pageElement.getAttribute("name");
            String parentName = parentElement.getAttribute("name");
            projectModel.newPage(pageName, parentName);
            buildPageContent(pageElement);
        }
    }

    private static void buildPageContent(Element pageElement){
        NodeList contentElements = pageElement.getElementsByTagName("content");
        if (contentElements.getLength() == 0){
            return;
        }
        Element contentElement = (Element) contentElements.item(0);
        String pageName = pageElement.getAttribute("name");
        String pageType = contentElement.getAttribute("type");
        String pageFile = contentElement.getAttribute("file");
        if (!archiveEntries.containsKey(pageFile)){
            return;
        }
        ByteArray pageEntry = archiveEntries.get(pageFile);
        projectModel.setPageContent(pageName, pageEntry, pageType);
        if (pageElement.getAttribute("default").equals("true")){
            projectModel.setSelectedPageName(pageName);
        }
    }
}

