package org.bsuir.view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import org.bsuir.model.Parameters;

public class MenuBarBuilder {

    private MenuBar menuBar;
    private final MenuItem[] menuBarItems;

    public MenuBarBuilder() {
        menuBarItems = new MenuItem[Parameters.AMOUNT_OF_MENUBAR_ITEMS];
        createMenuBar();
    }

    private void createMenuBar(){
        MenuItem openFileItem = new MenuItem("Open File");
        MenuItem saveAsItem = new MenuItem("Save As");
        MenuItem newLineToolItem = new MenuItem("New line");
        MenuItem findToolItem = new MenuItem("Find");
        MenuItem deleteToolItem = new MenuItem("Delete");
        MenuItem aboutItem = new MenuItem("About");

        Menu fileMenu = new Menu("File");
        Menu toolMenu = new Menu("Tool");
        Menu helpMenu = new Menu("Help");

        addMenuBarItems(openFileItem, saveAsItem, newLineToolItem, findToolItem, deleteToolItem, aboutItem);

        fileMenu.getItems().addAll(openFileItem, saveAsItem);
        toolMenu.getItems().addAll(newLineToolItem, findToolItem, deleteToolItem);
        helpMenu.getItems().addAll(aboutItem);

        menuBar = new MenuBar(fileMenu, toolMenu, helpMenu);
    }

    private void addMenuBarItems(MenuItem openFileItem, MenuItem saveAsItem, MenuItem newLineToolItem,
                                                 MenuItem findToolItem, MenuItem deleteToolItem, MenuItem aboutItem) {
        menuBarItems[0] = openFileItem;
        menuBarItems[1] = saveAsItem;
        menuBarItems[2] = newLineToolItem;
        menuBarItems[3] = findToolItem;
        menuBarItems[4] = deleteToolItem;
        menuBarItems[5] = aboutItem;
    }

    public MenuItem[] getMenuBarItems() {
        return menuBarItems;
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }
}
