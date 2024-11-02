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
import java.io.FileNotFoundException;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipEntry;
import java.util.HashMap;
import java.util.ArrayList;

public class ArchiveHandler {
    private HashMap<String, ByteArrayInputStream> inputStreamMap;
    private Document projectDescriptor;

    public ArchiveHandler(String projectArchivePath) throws Exception {
        try {
            LoadProjectArchive(projectArchivePath);
        }
        catch (Exception exc){
            if (exc instanceof FileNotFoundException){
                throw new Exception(ResourceLoader.getString("exception.archiveNotFound"));
            }
            throw new Exception(ResourceLoader.getString("exception.archiveLoadFailed"));
        }
        LoadProjectDescriptor();
    }

    private void LoadProjectArchive(String projectArchivePath) throws Exception {
        FileInputStream inputFile = new FileInputStream(projectArchivePath);
        ZipInputStream inputZip = new ZipInputStream(inputFile);
        inputStreamMap = new HashMap<String, ByteArrayInputStream>();
        ZipEntry zipEntry;
        while ((zipEntry = inputZip.getNextEntry()) != null){
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputZip.read(buffer)) > 0){
                outStream.write(buffer, 0, len);
            }
            byte[] byteArray = outStream.toByteArray();
            inputStreamMap.put(zipEntry.getName(), new ByteArrayInputStream(byteArray));
        }
    }

    private void LoadProjectDescriptor() throws Exception {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        projectDescriptor = docBuilder.parse(inputStreamMap.get("descriptor.xml"));
        if (projectDescriptor == null){
            throw new Exception(ResourceLoader.getString("exception.descriptorNotFound"));
        }
        projectDescriptor.normalizeDocument();
    }

    public Object[] getProjectStructure() throws Exception {
        Element projectElement = (Element) projectDescriptor.getElementsByTagName("project").item(0);
        if (projectElement == null){
            throw new Exception(ResourceLoader.getString("exception.invalidDescriptor"));
        }
        return buildProjectStructure(projectElement);
    }

    private Object[] buildProjectStructure(Element rootElement){
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

