package org.bsuir.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import org.bsuir.model.Parameters;
import org.bsuir.model.TableElement;

public class TableElementView extends BorderPane {

    private TableView<TableElement> tableView;
    private Button[] pageManagerButtons;
    private Label infoLabel;

    public TableElementView() {
        createTable();
        createButtons();
        createLabel();
    }

    public void createButtons() {
        PageManager pageManager = new PageManager();

        createPageManagerButtons();
        pageManager.addButtonsToPageManager(pageManagerButtons);
        setTop(pageManager.getGridPane());
    }

    private void createLabel() {
        infoLabel = new Label(Parameters.INITIAL_INFO_LABEL_STRING);
        setBottom(infoLabel);
    }

    private void createPageManagerButtons() {
        pageManagerButtons = new Button[4];
        pageManagerButtons[0] = createButton("First Page");
        pageManagerButtons[1] = createButton("Last Page");
        pageManagerButtons[2] = createButton("Next Page");
        pageManagerButtons[3] = createButton("Previous Page");
    }

    private Button createButton(String buttonName) {
        Button button = new Button(buttonName);
        button.setMaxSize(1000, 50);
        return button;
    }

    private void createTable() {
        tableView = new TableView<>();
        setTableViewParams();
        addAllColumns();
        setCenter(tableView);
    }

    private void setTableViewParams() {
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    private void addAllColumns() {
        tableView.getColumns().add(createTableColumn("Book name", "bookName"));
        tableView.getColumns().add(createTableColumn("Author Initials", "authorInitials"));
        tableView.getColumns().add(createTableColumn("Publisher Initials", "publisherInitials"));
        tableView.getColumns().add(createTableColumn("Tom Amount", "tomAmount"));
        tableView.getColumns().add(createTableColumn("Circulation", "circulation"));
        tableView.getColumns().add(createTableColumn("Final Tom Amount", "finalTomAmount"));
    }

    private TableColumn<TableElement, ?> createTableColumn(String columnNameStr, String columnIdentifierStr) {
        TableColumn<TableElement, ?> columnName = new TableColumn<>(columnNameStr);
        columnName.setCellValueFactory(new PropertyValueFactory<>(columnIdentifierStr));
        return columnName;
    }

    public TableView<TableElement> getTable() {
        return tableView;
    }

    public Label getInfoLabel() {
        return infoLabel;
    }

    public Button[] getPageManagerButtons() {
        return pageManagerButtons;
    }
}