package org.berandev.byterover;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.util.ArrayList;
import java.util.List;

//import org.jdom2.Document;
//import org.jdom2.Element;
//import org.idom2.input.SAXBuilder;

public class ArchiveImportHandler {
    private static ProjectArchive projectArchive;
    private static StructureTreeNode defaultNode;
/*
    public static void importArchive(ProjectModel model, String path) throws Exception {
        ProjectArchive projectArchive = new ProjectArchive();
        ZipArchiveHandler.loadArchive(projectArchive, path);
        model.setPageTreeModel(buildPageTree(projectArchive));
    }

    private static buildPageTree(ProjectArchive archive) throws Exception {
        String filename = ResourceLoader.getFilename("pageTreeDescriptor");
        if (!archive.containsEntry(filename)){
            throw new Exception(ResourceLoader.getException("archive.loadFailed"));
        }
    }
*/
    public static ProjectModel importArchive(String archivePath) throws Exception {
        projectArchive = new ProjectArchive();
        ZipArchiveHandler.loadArchive(projectArchive, archivePath);
        String descriptorFilename = ResourceLoader.getFilename("projectDescriptor");
        if (!projectArchive.containsEntry(descriptorFilename)){
            throw new Exception(ResourceLoader.getException("descriptorNotFound"));
        }
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        ByteArray descriptorEntry = projectArchive.getEntry(descriptorFilename);
        Document projectDescriptor = docBuilder.parse(descriptorEntry.toInputStream());
        projectDescriptor.getDocumentElement().normalize();
        return buildProjectModel(projectDescriptor.getDocumentElement());
    }

    private static ProjectModel buildProjectModel(Element element){
        ProjectModel projectModel = new ProjectModel();
        StructureTreeNode pageTreeRoot = buildPageTreeNode(element);
        StructureTreeModel pageTreeModel = new StructureTreeModel(pageTreeRoot);
        pageTreeModel.setDefaultNode(defaultNode);
        projectModel.setPageTreeModel(pageTreeModel);
        return projectModel;
    }

    private static StructureTreeNode buildPageTreeNode(Element element){
        if (element.getNodeName().equals("page")){
            PageNode pageNode = new PageNode(element.getAttribute("name"));
            if (element.getAttribute("default").equals("true")){
                defaultNode = pageNode;
            }
            buildPageContent(pageNode.getPageModel(), element);
            return pageNode;
        }
        else {
            GroupNode groupNode = new GroupNode(element.getAttribute("name"));
            for (Element childElement : getValidChildren(element)){
                groupNode.add(buildPageTreeNode(childElement));
            }
            return groupNode;
        }
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

