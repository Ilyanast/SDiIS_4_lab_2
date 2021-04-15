package org.bsuir.controller;

import org.bsuir.model.TableElement;
import org.bsuir.model.TableElementModel;
import org.bsuir.view.TableElementView;

import java.util.ArrayList;
import java.util.List;

public class TableElementController {
    private final TableElementView controlledTableView;
    private final TableElementModel controlledModel;

    private final int rowsOnPage;
    private int currentPage = 1;

    public TableElementController(TableElementView controlledTableView, TableElementModel controlledModel, int rowsOnPage) {
        this.controlledTableView = controlledTableView;
        this.controlledModel = controlledModel;
        this.rowsOnPage = rowsOnPage;
    }

    public void toFirstPage() {
        currentPage = 1;
        updateTableView();
    }

    public void toLastPage() {
        currentPage = (controlledModel.getTableElements().size() / rowsOnPage) + 1;
        updateTableView();
    }

    public void nextPage() {
        if (currentPage <= controlledModel.getTableElements().size() / rowsOnPage) {
            currentPage++;
            updateTableView();
        }
    }

    public void prevPage() {
        if (currentPage > 1) {
            currentPage--;
            updateTableView();
        }
    }

    private void clearTableView() {
        controlledTableView.getTable().getItems().clear();
    }

    public void updateTableView() {
        clearTableView();
        int startIdx = (currentPage - 1) * rowsOnPage;
        int endIdx = Math.min(currentPage * rowsOnPage, controlledModel.getTableElements().size());

        List<TableElement> tablesArray = controlledModel.getTableElements().subList(startIdx, endIdx);
        controlledTableView.getTable().getItems().addAll(tablesArray);

        updateTableStatus(tablesArray.size());
    }

    private void updateTableStatus(int entriesOnPage) {
        int pagesAmount = controlledModel.getTableElements().size() / rowsOnPage + 1;
        controlledTableView.getInfoLabel().setText("" +
                "Номер страницы: " + currentPage + "   " +
                "Всего страниц: " + pagesAmount + "   " +
                "Записей на странице: " + entriesOnPage + "   " +
                "Всего записей: " + controlledModel.getTableElements().size()
        );
    }

    public void updateModel(ArrayList<TableElement> newTableArray) {
        controlledModel.setTableElements(newTableArray);
    }
}