package org.bsuir.view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import static org.bsuir.model.Parameters.FONT_NAME;

public class NewLineDialog {

    private final Stage dialogStage;
    private final Group rootElement;

    private TextField[] textFields;
    private Button addLineButton;

    private Font mainFont;

    public NewLineDialog() {
        int width = 500;
        int height = 350;

        rootElement = new Group();
        Scene dialogScene = new Scene(rootElement, width, height);

        formUserInterface();

        dialogStage = new Stage();
        dialogStage.setTitle("New Line");
        dialogStage.setResizable(false);
        dialogStage.setScene(dialogScene);
        dialogStage.show();
    }

    private void formUserInterface() {
        mainFont = Font.font(FONT_NAME, 16);

        createAllLabels();
        createAllTextFields();
        createButton();
    }

    private void createAllTextFields() {
        textFields = new TextField[5];
        textFields[0] = createTextField(0);
        textFields[1] = createTextField(60);
        textFields[2] = createTextField(120);
        textFields[3] = createTextField(180);
        textFields[4] = createTextField(240);
    }

    private TextField createTextField(int translateY) {
        TextField textField = new TextField();

        textField.setPrefSize(300, 50);
        textField.setTranslateY(translateY);
        textField.setTranslateX(200);
        textField.setFont(mainFont);

        rootElement.getChildren().add(textField);

        return textField;
    }

    private void createAllLabels() {
        createLabel("Book name", 15);
        createLabel("Author initials", 75);
        createLabel("Publisher initials", 135);
        createLabel("Tom amount", 195);
        createLabel("Circulation", 255);
    }

    private void createLabel(String text, int translateY) {
        Label label = new Label(text);

        label.setFont(mainFont);
        label.setTranslateY(translateY);
        rootElement.getChildren().add(label);
    }

    private void createButton() {
        addLineButton = new Button("Add new line");
        addLineButton.setPrefSize(500, 50);
        addLineButton.setTranslateY(300);

        rootElement.getChildren().add(addLineButton);
    }

    public Stage getDialogStage() { return dialogStage; }

    public TextField[] getTextFields() {
        return textFields;
    }

    public Button getAddLineButton() {
        return addLineButton;
    }
}