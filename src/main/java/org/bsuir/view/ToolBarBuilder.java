package org.bsuir.view;

import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import org.bsuir.model.Parameters;

public class ToolBarBuilder {

    private ToolBar toolBar;
    private final Button[] toolBarItems;

    public ToolBarBuilder() {
        toolBarItems = new Button[Parameters.AMOUNT_OF_TOOLBAR_ITEMS];
        createToolBar();
    }

    private void createToolBar() {
        toolBar = new ToolBar();
        toolBar.setOrientation(Orientation.HORIZONTAL);

        Button newLineButton = new Button("New line");
        Button findButton = new Button("Find");
        Button deleteButton = new Button("Delete");

        addToolBarItems(newLineButton, findButton, deleteButton);
        toolBar.getItems().addAll(newLineButton, new Separator(), findButton, new Separator(), deleteButton);
    }

    private void addToolBarItems(Button newLineButton, Button findButton, Button deleteButton) {
        toolBarItems[0] = newLineButton;
        toolBarItems[1] = findButton;
        toolBarItems[2] = deleteButton;
    }

    public Button[] getToolBarItems() {
        return toolBarItems;
    }

    public ToolBar getToolBar() {
        return toolBar;
    }
}
