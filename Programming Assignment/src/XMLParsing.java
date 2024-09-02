import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class XMLParsing{

    //Method to parse through the xmlFile provided from the main file and provide a list of components.
    public List<Leaf> parser(File xmlFile) {
        List<Leaf> componentsList = new ArrayList<>();

        try {

            //Parsing the file
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("*");

            //For loop to find the leaf nodes
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    if (!element.hasChildNodes()) {//This determines whether we have found a leaf node. If we have, get the bounds.
                        String bounds = element.getAttribute("bounds");
                        componentsList.add(new Leaf(bounds));
                    }
                }
            }

        //Keeps the program running in case something is wrong with an xml file, so we can still parse the others.
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return componentsList;
    }
}