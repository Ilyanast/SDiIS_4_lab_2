package org.bsuir.model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

public class TableElementParser {
    public void parseAndWrite(String rootTag, String nodeTag, ArrayList<TableElement> parsedModelArray, String writePath) {

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;

        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element rootElement = document.createElement(rootTag);
            document.appendChild(rootElement);


            for (TableElement tableElement : parsedModelArray) {
                String bookName = tableElement.getBookName();
                String authorInitials = tableElement.getAuthorInitials();
                String publisherInitials = tableElement.getPublisherInitials();
                String tomAmount = String.valueOf(tableElement.getTomAmount());
                String circulation = String.valueOf(tableElement.getCirculation());
                String finalTomAmount = String.valueOf(tableElement.getFinalTomAmount());

                rootElement.appendChild(createElement(document, nodeTag, bookName, authorInitials, publisherInitials,
                        tomAmount, circulation, finalTomAmount));
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource writtenSource = new DOMSource(document);

            StreamResult streamResult = new StreamResult(new File(writePath));
            transformer.transform(writtenSource, streamResult);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Node createElement(Document document, String nodeTag, String bookName, String authorInitials,
                               String publisherInitials, String tomAmount, String circulation, String finalTomAmount) {

        Element element = document.createElement(nodeTag);

        element.appendChild(getEntryElements(document,"bookName", bookName));
        element.appendChild(getEntryElements(document,"authorInitials", authorInitials));
        element.appendChild(getEntryElements(document,"publisherInitials", publisherInitials));
        element.appendChild(getEntryElements(document,"tomAmount", tomAmount));
        element.appendChild(getEntryElements(document,"circulation", circulation));
        element.appendChild(getEntryElements(document,"finalTomAmount", finalTomAmount));

        return element;
    }

    private Node getEntryElements(Document document, String identifier, String valueStr) {
        Element node = document.createElement(identifier);
        node.appendChild(document.createTextNode(valueStr));
        return node;
    }

    public ArrayList<TableElement> readAndParse(String parsePath) {
        ArrayList<TableElement> patients = null;

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser documentModelReader = factory.newSAXParser();
            ParserHandler writerHandler = new ParserHandler();

            documentModelReader.parse(parsePath, writerHandler);
            patients = writerHandler.getTableElements();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return patients;
    }
}
