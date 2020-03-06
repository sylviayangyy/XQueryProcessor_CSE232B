package common;

import IO.IOHelper;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.print.Doc;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

public class XMLTreeProcessor {
    private Document document;

    public XMLTreeProcessor() {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            this.document = documentBuilder.newDocument();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            System.out.println("documentBuilder error in XMLTreeProcessor Class");
        }
    }

    public LinkedList<Node> getRoot(String fileName) {
        LinkedList<Node> nodes = new LinkedList<>();
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
            Document document = docBuilder.parse(new InputSource(inputStream));
            Element root = document.getDocumentElement();
            root.normalize();
            nodes.add(root);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return nodes;
    }

    public LinkedList<Node> getChildren(Node node) {
        LinkedList<Node> nodes = new LinkedList<>();
        if (node == null) {
            return nodes;
        }
        NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child != null) {
                nodes.add(child);
            }
        }
        return nodes;
    }

    public LinkedList<Node> getParent(Node node) {
        LinkedList<Node> nodes = new LinkedList<>();
        if (node == null) {
            return nodes;
        }
        if (node.getNodeType() == Node.ATTRIBUTE_NODE) {
            Element parent = ((Attr) node).getOwnerElement();
            nodes.add(parent);
        } else {
            Node parent = node.getParentNode();
            nodes.add(parent);
        }
        return nodes;
    }

    public LinkedList<Node> getDescendantOrSelf(Node node) {
        LinkedList<Node> nodes = new LinkedList<>();
        if (node == null) {
            return nodes;
        }
        Queue<Node> queue = new LinkedList<>();
        ((LinkedList<Node>) queue).add(node);
        while (!queue.isEmpty()) {
            Node n = queue.remove();
            if (n != null) {
                nodes.add(n);
            }
            LinkedList<Node> children = this.getChildren(n);
            for (Node child : children) {
                ((LinkedList<Node>) queue).add(child);
            }
        }
        return nodes;
    }

    public LinkedList<Node> getTextNodes(Node node) {
        LinkedList<Node> nodes = new LinkedList<>();
        if (node == null) {
            return nodes;
        }
        LinkedList<Node> children = this.getChildren(node);
        for (Node child : children) {
            if (child.getNodeType() == Node.TEXT_NODE && child.getTextContent() != null && child.getTextContent().length() != 0) {
                nodes.add(child);
            }
        }
        return nodes;
    }

    public LinkedList<Node> getAttributeNode(Node node, String attribute) {
        LinkedList<Node> nodes = new LinkedList<>();
        if (node == null) {
            return nodes;
        }
        if (node.getNodeType() != Node.ELEMENT_NODE) {
            return nodes;
        }
        if (!((Element) node).hasAttribute(attribute)) {
            return nodes;
        }
        Node attributeNode = ((Element) node).getAttributeNode(attribute);
        nodes.add(attributeNode);
        return nodes;
    }

    public String getTagName(Node node) {
        if (node == null) {
            return "";
        }
        return node.getNodeName();
    }

    public boolean isEqual(LinkedList<Node> nodes1, LinkedList<Node> nodes2) {
        for (Node n1 : nodes1) {
            for (Node n2 : nodes2) {
                if (n1.isEqualNode(n2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isSame(LinkedList<Node> nodes1, LinkedList<Node> nodes2) {
        for (Node n1 : nodes1) {
            for (Node n2 : nodes2) {
                if (n1.isSameNode(n2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public LinkedList<Node> singleNodeToList(Node node) {
        LinkedList<Node> nodeLinkedList = new LinkedList<>();
        if (node != null) {
            nodeLinkedList.add(node);
        }
        return nodeLinkedList;
    }

    public Node makeElem(String tagName, LinkedList<Node> nodes) {
        Element elementNode = document.createElement(tagName);
        for (Node node : nodes) {
            Node curNode = document.importNode(node, true);
            if (curNode.getNodeType() == Node.ATTRIBUTE_NODE) {
                elementNode.setAttributeNode((Attr) curNode);
            } else {
                elementNode.appendChild(curNode);
            }
        }
        return elementNode;
    }

    public Node makeText(String stringConstant) {
        return document.createTextNode(stringConstant);
    }

    public LinkedList<Node> unique(LinkedList<Node> nodes) {
        Set<Node> nodeSet = new HashSet<>(nodes);
        return new LinkedList<>(nodeSet);
    }

    public Node makeAttribute (String attr) {
        return document.createAttribute(attr);
    }

    public String getKeyForNode (Node node, List<Node> attrs) {
        StringBuilder keyBuilder = new StringBuilder();
        NodeList children = node.getChildNodes();
        for (Node attr : attrs) {
            for (int i = 0; i < children.getLength(); i++) {
                Node child = children.item(i);
                if (child.getNodeName().equals(attr.getNodeName())) {
                    keyBuilder.append(IOHelper.nodesToString(getChildren(child))) ;
                }
            }
        }
        return keyBuilder.toString();
    }
}