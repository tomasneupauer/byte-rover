package org.berandev.byterover;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import java.io.FileInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipEntry;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

class ArchiveEntry {
    protected byte[] content;

    public ArchiveEntry(byte[] content){
        this.content = content;
    }

    public toInputStream(){
        return new ByteArrayInputStream(content);
    }

    public toString(){
        return new String(content);
    }

    public getContent(){
        return content;
    }
}

class ZipHandler {
    public static Map<String, byte[]> loadZipArchive(String zipArchivePath) throws Exception {
        Map<String, byte[]> archiveEntries = new HashMap<String, byte[]>();
        ZipInputStream zipStream;
        try {
            zipStream = new ZipInputStream(new FileInputStream(projectArchivePath));
        }
        catch (Exception exc){
            throw new Exception(ResourceLoader.getException("archiveNotFound"));
        }
        try {
            while (zipStream.available()){
                ZipEntry zipEntry = zipStream.getNextEntry();
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024]; int length;
                while ((length = zipStream.read(buffer)) > 0){
                    outStream.write(buffer, 0, length);
                }
                archiveEntries.put(zipEntry.getName(), outStream.toByteArray());
            }
            zipStream.close();
        }
        catch (Exception exc){
            throw new Exception(ResourceLoader.getException("archiveLoadFailed"));
        }
        return archiveEntries;
    }
}

public class ArchiveHandler {
    private Map<String, ArchiveEntry> archiveEntries;
    private Document projectDescriptor;
    private ProjectModel projectModel;

    public ArchiveHandler(String projectArchivePath) throws Exception {
        archiveEntries = ZipHandler.loadZipArchive(projectArchivePath);
        String descriptorFilename = ResourceLoader.getFilename("projectDescriptor");
        if (!archiveEntries.containKey(descriptorFilename)){
            throw new Exception(ResourceLoader.getException("descriptorNotFound"));
        }
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        ArchiveEntry descriptorEntry = archiveEntries.get(descriptorFilename);
        projectDescriptor = docBuilder.parse(descriptorEntry.toInputStream());
        projectDescriptor.normalizeDocument();
    }

    public ProjectModel buildProjectModel(){
        Element projectElement = projectDescriptor.getDocumentElement();
        projectModel = new ProjectModel(projectElement.getAttribute("name"));
        buildProjectGroups(projectElement);
        buildProjectPages(projectElement);
        return projectModel
    }

    private void buildProjectGroups(Element projectElement){
        NodeList groupElements = projectElement.getElementsByTagName("group");
        for (int i=0; i<groupElements.getLength(); i++){
            Element groupElement = (Element) groupElements.item(i);
            Element parentElement = (Element) group.getParentNode();
            String groupName = groupElement.getAttribute("name");
            String parentName = parentElement.getAttribute("name");
            projectModel.addGroup(groupName, parentName);
        }
    }

    private void buildProjectPages(Element projectElement){
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

    private void buildPageContent(Element pageElement){
        Element contentElement = (Element) pageElement.getElementsByTagName("content").item(0);
        if (contentElement != null){
            String pageName = pageElement.getAttribute("name");
            String pageType = contentElement.getAttribute("type");
            String pageFile = contentElement.getAttribute("file");
            ArchiveEntry archiveEntry = archiveEntries.get(pageFile);
            if (pageEntry != null){
                projectModel.addPage(pageName, new ContentEntry(archiveEntry, pageType));
            }
        }
    }
}

