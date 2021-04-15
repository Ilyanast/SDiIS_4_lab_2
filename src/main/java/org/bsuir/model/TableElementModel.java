package org.bsuir.model;

import java.util.ArrayList;

public class TableElementModel {

    private ArrayList<TableElement> tableElements;

    public TableElementModel() {
        tableElements = new ArrayList<>();
    }

    public TableElementModel(ArrayList<TableElement> tableElements) {
        this.tableElements = tableElements;
    }

    public ArrayList<TableElement> getTableElements() {
        return tableElements;
    }

    public void setTableElements(ArrayList<TableElement> tableElements) {
        this.tableElements = tableElements;
    }
}