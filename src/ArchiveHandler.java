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

public class ArchiveImportHandler {
    private Map<String, byte[]> archiveEntries;
    private Document projectDescriptor;

    public ArchiveHandler(String projectArchivePath) throws Exception {
        loadArchiveEntries(projectArchivePath);
        loadProjectDescriptor();
    }

    public ProjectModel buildProjectModel(){
        
    }

    private void loadArchiveEntries(String path) throws Exception {
        ZipInputStream zipStream;
        try {
            zipStream = new ZipInputStream(new FileInputStream(projectArchivePath));
        }
        catch (Exception exc){
            throw new Exception(ResourceLoader.getException("archiveNotFound"));
        }
        try {
            while (zipStream.available()){
                loadNextArchiveEntry(zipStream);
            }
            zipStream.close();
        }
        catch (Exception exc){
            throw new Exception(ResourceLoader.getException("archiveLoadFailed"));
        }
        return archiveEntries;
    }

    private void loadNextArchiveEntry(ZipInputStream zipStream) throws Exception {
        ZipEntry zipEntry = zipStream.getNextEntry();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024]; int length;
        while ((length = zipStream.read(buffer)) > 0){
            outStream.write(buffer, 0, length);
        }
        archiveEntries.put(zipEntry.getName(), outStream.toByteArray());
    }

    private void loadProjectDescriptor() throws Exception {
        String descriptorFilename = ResourceLoader.getFilename("projectDescriptor");
        if (!archiveEntries.containKey(descriptorFilename)){
            throw new Exception(ResourceLoader.getException("descriptorNotFound"));
        }
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        byte[] descriptorEntry = archiveEntries.get(descriptorFilename);
        projectDescriptor = docBuilder.parse(new ByteArrayInputStream(descriptorEntry));
        projectDescriptor.normalizeDocument();
    }

    //public ProjectStructure getProjectStructure() throws Exception {
        Element projectElement = (Element) projectDescriptor.getElementsByTagName("project").item(0);
        if (projectElement == null){
            throw new Exception(ResourceLoader.getString("exception.invalidDescriptor"));
        }
        return buildProjectStructure(projectElement);
    }

    //private Object[] buildProjectStructure(Element rootElement){
        NodeList childNodes = rootElement.getChildNodes();
        ArrayList<Object> childElements = new ArrayList<Object>();
        childElements.add(rootElement.getAttribute("name"));
        for (int i=0; i<childNodes.getLength(); i++){
            Node childNode = childNodes.item(i);
            if (childNode.getNodeType() == Node.ELEMENT_NODE){
                Element childElement = (Element) childNode;
                if (childElement.getTagName().equals("group")){
                    childElements.add(buildProjectStructure(childElement));
                }
                if (childElement.getTagName().equals("page")){
                    childElements.add(childElement.getAttribute("name"));
                }
            }
        }
        return childElements.toArray();
    }
}

