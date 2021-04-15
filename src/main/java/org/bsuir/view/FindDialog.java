package org.bsuir.view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import static org.bsuir.model.Parameters.FONT_NAME;

public class FindDialog {

    private final Stage dialogStage;
    private final Group rootElement;

    private TableElementView findTable;
    private TextField[] textFields;
    private Button findButton;

    private Font mainFont;

    public FindDialog() {
        int width = 800;
        int height = 620;

        rootElement = new Group();
        Scene dialogScene = new Scene(rootElement, width, height);

        formUserInterface();

        dialogStage = new Stage();
        dialogStage.setTitle("Find");
        dialogStage.setResizable(false);
        dialogStage.setScene(dialogScene);
        dialogStage.show();
    }

    private void formUserInterface() {
        mainFont = Font.font(FONT_NAME, 16);

        createAllLabels();
        createAllTextFields();
        createButton();
        createTable();
    }

    private void createAllTextFields() {
        textFields = new TextField[9];
        textFields[0] = createTextField(600, 200, 0);
        textFields[1] = createTextField(600, 200, 60);
        textFields[2] = createTextField(600, 200, 120);
        textFields[3] = createTextField(295, 200, 180);
        textFields[4] = createTextField(295, 505, 180);
        textFields[5] = createTextField(295, 200, 240);
        textFields[6] = createTextField(295, 505, 240);
        textFields[7] = createTextField(295, 200, 300);
        textFields[8] = createTextField(295, 505, 300);
    }

    private TextField createTextField(int width, int translateX, int translateY) {
        TextField textField = new TextField();

        textField.setPrefSize(width, 50);
        textField.setTranslateX(translateX);
        textField.setTranslateY(translateY);
        textField.setFont(mainFont);

        rootElement.getChildren().add(textField);

        return textField;
    }

    private void createAllLabels() {
        createLabel("Book name", 15);
        createLabel("Author initials", 75);
        createLabel("Publisher initials", 135);
        createLabel("Tom amount (range)", 195);
        createLabel("Circulation (range)", 255);
        createLabel("Final tom amount (range)", 315);
    }

    private void createLabel(String text, int translateY) {
        Label label = new Label(text);

        label.setFont(mainFont);
        label.setTranslateY(translateY);
        rootElement.getChildren().add(label);
    }

    private void createButton() {
        findButton = new Button("Find");

        findButton.setPrefSize(800, 50);
        findButton.setTranslateY(360);

        rootElement.getChildren().add(findButton);
    }

    private void createTable() {
        findTable = new TableElementView();

        findTable.setPrefSize(800, 200);
        findTable.setTranslateY(420);

        rootElement.getChildren().addAll(findTable);
    }

    public TextField[] getTextFields() {
        return textFields;
    }

    public Button getFindButton() { return findButton; }

    public TableElementView getFoundTable() { return findTable; }
}