package org.bsuir.model;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.Stack;

public class ParserHandler extends DefaultHandler {
    private final ArrayList<TableElement> tableElements = new ArrayList<>();

    private final Stack<String> elementStack = new Stack<>();
    private final Stack objectStack = new Stack();

    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        this.elementStack.push(qName);

        if ("tableElement".equals(qName)) {
            TableElement newTableElement = new TableElement();
            this.objectStack.push(newTableElement);
        }
    }

    public void characters(char[] ch, int start, int length) {
        String value = new String(ch, start, length).trim();

        if (value.length() == 0) return;

        if ("bookName".equals(currentElement())) {
            TableElement tableElement = (TableElement) this.objectStack.peek();
            tableElement.setBookName(value);
        }
        else if ("authorInitials".equals(currentElement())) {
            TableElement tableElement = (TableElement) this.objectStack.peek();
            tableElement.setAuthorInitials(value);
        }
        else if ("publisherInitials".equals(currentElement())) {
            TableElement tableElement = (TableElement) this.objectStack.peek();
            tableElement.setPublisherInitials(value);
        }
        else if ("tomAmount".equals(currentElement())) {
            TableElement tableElement = (TableElement) this.objectStack.peek();
            tableElement.setTomAmount(Integer.parseInt(value));
        }
        else if ("circulation".equals(currentElement())) {
            TableElement tableElement = (TableElement) this.objectStack.peek();
            tableElement.setCirculation(Integer.parseInt(value));
        }
        else if ("finalTomAmount".equals(currentElement())) {
            TableElement tableElement = (TableElement) this.objectStack.peek();
            tableElement.setFinalTomAmount(Integer.parseInt(value));
        }
    }

    public void endElement(String uri, String localName, String qName) {
        this.elementStack.pop();

        if ("tableElement".equals(qName)) {
            TableElement object = (TableElement) this.objectStack.pop();
            this.tableElements.add(object);
        }
    }

    private String currentElement() {
        return this.elementStack.peek();
    }

    public ArrayList<TableElement> getTableElements() {
        return tableElements;
    }
}