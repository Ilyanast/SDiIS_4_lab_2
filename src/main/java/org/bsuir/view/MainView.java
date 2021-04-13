package org.bsuir.view;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class MainView {
    private VBox mainRoot;
    private Scene mainScene;

    private MenuItem[] menuBarItems;
    private Button[] toolBarItems;

    private TableElementView tableView;

    public MainView(int width, int height) {
        mainRoot = new VBox();
        mainScene = new Scene(mainRoot, width, height);

        createMenu();
        createToolbar();
        createTable();
    }

    private void createMenu() {
        MenuBarBuilder menuBarBuilder = new MenuBarBuilder();
        menuBarItems = menuBarBuilder.getMenuBarItems();
        mainRoot.getChildren().add(menuBarBuilder.getMenuBar());
    }

    private void createToolbar() {
        ToolBarBuilder toolBarBuilder = new ToolBarBuilder();
        toolBarItems = toolBarBuilder.getToolBarItems();
        mainRoot.getChildren().add(toolBarBuilder.getToolBar());
    }

    private void createTable() {
        tableView = new TableElementView();
        mainRoot.getChildren().add(tableView);
    }

    public Scene getScene() {
        return mainScene;
    }

    public MenuItem[] getMenuBarItems() {
        return  menuBarItems;
    }
    public Button[] getToolBarItems() {
        return toolBarItems;
    }
    public TableElementView getTableView() { return tableView; }
}