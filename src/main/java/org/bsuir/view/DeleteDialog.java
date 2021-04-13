package org.bsuir.view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import static org.bsuir.model.Parameters.FONT_NAME;

public class DeleteDialog {
    private final Stage dialogStage;
    private final Group rootElement;

    private TextField[] textFields;
    private Button deleteButton;
    private Label resultInfoLabel;

    private Font mainFont;

    public DeleteDialog() {
        int width = 500;
        int height = 460;

        rootElement = new Group();
        Scene dialogScene = new Scene(rootElement, width, height);

        formUserInterface();

        dialogStage = new Stage();
        dialogStage.setTitle("Delete");
        dialogStage.setResizable(false);
        dialogStage.setScene(dialogScene);
        dialogStage.show();
    }

    private void formUserInterface() {
        mainFont = Font.font(FONT_NAME, 16);

        createAllLabels();
        createAllTextFields();
        createButton();
        createResultLabel();
    }

    private void createAllTextFields() {
        textFields = new TextField[9];
        textFields[0] = createTextField(300, 200, 0);
        textFields[1] = createTextField(300, 200, 60);
        textFields[2] = createTextField(300, 200, 120);
        textFields[3] = createTextField(145, 200, 180);
        textFields[4] = createTextField(145, 355, 180);
        textFields[5] = createTextField(145, 200, 240);
        textFields[6] = createTextField(145, 355, 240);
        textFields[7] = createTextField(145, 200, 300);
        textFields[8] = createTextField(145, 355, 300);
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
        deleteButton = new Button("Delete");

        deleteButton.setPrefSize(500, 50);
        deleteButton.setTranslateY(360);

        rootElement.getChildren().add(deleteButton);
    }

    private void createResultLabel() {
        resultInfoLabel = new Label("Результат выполнения");

        resultInfoLabel.setFont(Font.font(FONT_NAME, 20));
        resultInfoLabel.setTranslateY(420);

        rootElement.getChildren().add(resultInfoLabel);
    }

    public Stage getDialogStage() { return dialogStage; }

    public TextField[] getTextFields() {
        return textFields;
    }

    public Button getDeleteButton() { return deleteButton; }

    public Label getResultInfoLabel() { return resultInfoLabel; }
}