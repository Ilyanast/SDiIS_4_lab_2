package org.bsuir.view;

import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class PageManager {

    private GridPane gridPane;

    public PageManager() {
        gridPane = new GridPane();
        setPageManagerParams();
    }

    private void setPageManagerParams() {
        gridPane.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        gridPane.getColumnConstraints().addAll(createColumnConstraints(), createColumnConstraints(),
                                                                createColumnConstraints(), createColumnConstraints());
    }

    private ColumnConstraints createColumnConstraints() {
        ColumnConstraints column = new ColumnConstraints();
        setColumnConstraintsParams(column);
        return column;
    }

    private void setColumnConstraintsParams (ColumnConstraints column) {
        column.setFillWidth(true);
        column.setHgrow(Priority.ALWAYS);
        column.setPercentWidth(25);
    }

    public void addButtonsToPageManager(Button[] buttons) {
        for (int i = 0 ; i < buttons.length; i++) {
            gridPane.add(buttons[i],  i, 0, 1, 1);
        }
    }

    public GridPane getGridPane() {
        return gridPane;
    }
}
