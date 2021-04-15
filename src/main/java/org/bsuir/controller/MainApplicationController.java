package org.bsuir.controller;

import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.StageStyle;
import org.bsuir.model.TableElement;
import org.bsuir.model.TableElementModel;
import org.bsuir.model.TableElementParser;
import org.bsuir.view.DeleteDialog;
import org.bsuir.view.FindDialog;
import org.bsuir.view.MainView;
import org.bsuir.view.NewLineDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainApplicationController {
    private final TableElementModel model;
    private final MainView view;

    private final TableElementController tableController;

    public MainApplicationController(MainView view, TableElementModel model) {
        this.view = view;
        this.model = model;

        tableController = new TableElementController(view.getTableView(), model, 10);
        initController();
    }

    private void save() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName("untitled.xml");
        File saveFile = fileChooser.showSaveDialog(null);

        if(saveFile != null) {
            TableElementParser xmlModelWriter = new TableElementParser();
            ArrayList<TableElement> entriesArray = model.getTableElements();
            xmlModelWriter.parseAndWrite("table", "tableElement", entriesArray, saveFile.getAbsolutePath());
        }
    }

    private void load() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName("untitled.xml");
        File loadFile = fileChooser.showOpenDialog(null);

        if(loadFile != null) {
            TableElementParser xmlDocumentReader = new TableElementParser();
            ArrayList<TableElement> tableElements = xmlDocumentReader.readAndParse(loadFile.getAbsolutePath());
            model.setTableElements(tableElements);
            tableController.updateModel(tableElements);
            tableController.updateTableView();
        }
    }

    private void initController() {
        view.getMenuBarItems()[1].setOnAction(event -> save());
        view.getMenuBarItems()[0].setOnAction(event -> load());

        view.getMenuBarItems()[2].setOnAction(event -> initNewLineDialog());
        view.getMenuBarItems()[3].setOnAction(event -> initFindDialog());
        view.getMenuBarItems()[4].setOnAction(event -> initDeleteDialog());

        view.getToolBarItems()[0].setOnMouseClicked(event -> initNewLineDialog());
        view.getToolBarItems()[1].setOnMouseClicked(event -> initFindDialog());
        view.getToolBarItems()[2].setOnAction(event -> initDeleteDialog());

        view.getTableView().getPageManagerButtons()[0].setOnAction(event -> tableController.toFirstPage());
        view.getTableView().getPageManagerButtons()[1].setOnAction(event -> tableController.toLastPage());
        view.getTableView().getPageManagerButtons()[2].setOnAction(event -> tableController.nextPage());
        view.getTableView().getPageManagerButtons()[3].setOnAction(event -> tableController.prevPage());
    }

    private void initNewLineDialog() {
        NewLineDialog dialog = new NewLineDialog();

        dialog.getAddLineButton().setOnMouseClicked(event -> {
            String bookName = dialog.getTextFields()[0].getText();
            String authorInitials = dialog.getTextFields()[1].getText();
            String publisherInitials = dialog.getTextFields()[2].getText();
            String tomAmount = dialog.getTextFields()[3].getText();
            String circulation = dialog.getTextFields()[4].getText();

            boolean inputCorrect = true;

            int circulationInt = 0, tomAmountInt = 0;

            try {
                tomAmountInt = Integer.parseInt(tomAmount);
                circulationInt = Integer.parseInt(circulation);
            }
            catch (NumberFormatException e) {
                inputCorrect = false;
            }

            if (inputCorrect && !bookName.isEmpty() && !authorInitials.isEmpty() && !publisherInitials.isEmpty()){
                model.getTableElements().add(new TableElement(bookName, authorInitials, publisherInitials,
                        tomAmountInt, circulationInt, (tomAmountInt * circulationInt)));
                tableController.updateModel(model.getTableElements());
                tableController.updateTableView();

                dialog.getDialogStage().hide();
            }
            else {
                Alert incorrectInput = new Alert(Alert.AlertType.WARNING);
                incorrectInput.initStyle(StageStyle.UTILITY);
                incorrectInput.setTitle("");
                incorrectInput.setHeaderText("Ошибка");
                incorrectInput.setContentText("Некорректный ввод");
                incorrectInput.showAndWait();
            }
        });
    }

    private void initFindDialog() {
        FindDialog dialog = new FindDialog();

        dialog.getFindButton().setOnMouseClicked(event -> {
            String bookName = dialog.getTextFields()[0].getText();
            String authorInitials = dialog.getTextFields()[1].getText();
            String publisherInitials = dialog.getTextFields()[2].getText();

            String tomAmountLowRange = dialog.getTextFields()[3].getText();
            String tomAmountHighRange = dialog.getTextFields()[4].getText();
            String circulationLowRange = dialog.getTextFields()[5].getText();
            String circulationHighRange = dialog.getTextFields()[6].getText();
            String finalTomAmountLowRange = dialog.getTextFields()[7].getText();
            String finalTomAmountHighRange = dialog.getTextFields()[8].getText();

            ArrayList<TableElement> foundedTableElements = (ArrayList<TableElement>) find(bookName, authorInitials, publisherInitials,
                        tomAmountLowRange, tomAmountHighRange, circulationLowRange, circulationHighRange,
                        finalTomAmountLowRange, finalTomAmountHighRange);

            TableElementController dialogTableController = new TableElementController(dialog.getFoundTable(),
                                                            new TableElementModel(foundedTableElements), 3);

            dialog.getFoundTable().getPageManagerButtons()[0].setOnAction(e -> dialogTableController.toFirstPage());
            dialog.getFoundTable().getPageManagerButtons()[1].setOnAction(e -> dialogTableController.toLastPage());
            dialog.getFoundTable().getPageManagerButtons()[2].setOnAction(e -> dialogTableController.nextPage());
            dialog.getFoundTable().getPageManagerButtons()[3].setOnAction(e -> dialogTableController.prevPage());

            dialogTableController.updateTableView();
        });
    }

    private void initDeleteDialog() {
        DeleteDialog dialog = new DeleteDialog();
        dialog.getDeleteButton().setOnAction(event -> {
            tableController.toFirstPage();

            String bookName = dialog.getTextFields()[0].getText();
            String authorInitials = dialog.getTextFields()[1].getText();
            String publisherInitials = dialog.getTextFields()[2].getText();

            String tomAmountLowRange = dialog.getTextFields()[3].getText();
            String tomAmountHighRange = dialog.getTextFields()[4].getText();
            String circulationLowRange = dialog.getTextFields()[5].getText();
            String circulationHighRange = dialog.getTextFields()[6].getText();
            String finalTomAmountLowRange = dialog.getTextFields()[7].getText();
            String finalTomAmountHighRange = dialog.getTextFields()[8].getText();

            ArrayList<TableElement> foundedTableElements = (ArrayList<TableElement>) find(bookName, authorInitials, publisherInitials,
                    tomAmountLowRange, tomAmountHighRange, circulationLowRange, circulationHighRange,
                    finalTomAmountLowRange, finalTomAmountHighRange);

            model.getTableElements().removeAll(foundedTableElements);

            tableController.updateModel(model.getTableElements());
            tableController.updateTableView();

            if(foundedTableElements.size() == 0) {
                dialog.getResultInfoLabel().setText("Ничего не было удалено");
            }
            else{
                dialog.getResultInfoLabel().setText("Было удалено записей: " + foundedTableElements.size());
            }

        });
    }

    private List<TableElement> find(String bookName, String authorInitials, String publisherInitials,
                                    String tomAmountLowRange, String tomAmountHighRange, String circulationLowRange,
                                    String circulationHighRange, String finalTomAmountLowRange, String finalTomAmountHighRange) {

        int tomAmountLowRangeInt = 0, tomAmountHighRangeInt = Integer.MAX_VALUE;
        int circulationLowRangeInt = 0, circulationHighRangeInt = Integer.MAX_VALUE;
        int finalTomAmountLowRangeInt = 0, finalTomAmountHighRangeInt = Integer.MAX_VALUE;

        if(tomAmountLowRange.equals("") && !tomAmountHighRange.equals("")) {
            tomAmountHighRangeInt = Integer.parseInt(tomAmountHighRange);
        }
        if(!tomAmountLowRange.equals("") && tomAmountHighRange.equals("")) {
          tomAmountLowRangeInt = Integer.parseInt(tomAmountLowRange);
        }
        if(circulationLowRange.equals("") && !circulationHighRange.equals("")) {
            circulationHighRangeInt = Integer.parseInt(circulationHighRange);
        }
        if(!circulationLowRange.equals("") && circulationHighRange.equals("")) {
            circulationLowRangeInt = Integer.parseInt(circulationLowRange);
        }
        if(finalTomAmountLowRange.equals("") && !finalTomAmountHighRange.equals("")) {
            finalTomAmountHighRangeInt = Integer.parseInt(finalTomAmountHighRange);
        }
        if(!finalTomAmountLowRange.equals("") && finalTomAmountHighRange.equals("")) {
            finalTomAmountLowRangeInt = Integer.parseInt(finalTomAmountLowRange);
        }

        int tomAmountLowRangeIntF = tomAmountLowRangeInt;
        int tomAmountHighRangeIntF = tomAmountHighRangeInt;
        int circulationLowRangeIntF = circulationLowRangeInt;
        int circulationHighRangeIntF = circulationHighRangeInt;
        int finalTomAmountLowRangeIntF = finalTomAmountLowRangeInt;
        int finalTomAmountHighRangeIntF = finalTomAmountHighRangeInt;


        return model.getTableElements().stream()
                .filter(row -> row.getBookName().equals(bookName) || bookName.equals(""))
                .filter(row -> row.getAuthorInitials().equals(authorInitials) || authorInitials.equals(""))
                .filter(row -> row.getPublisherInitials().equals(publisherInitials) || publisherInitials.equals(""))
                .filter(row -> ((row.getTomAmount() >= tomAmountLowRangeIntF) && (row.getTomAmount() <= tomAmountHighRangeIntF)))
                .filter(row -> ((row.getTomAmount() >= circulationLowRangeIntF) && (row.getTomAmount() <= circulationHighRangeIntF)))
                .filter(row -> ((row.getFinalTomAmount() >= finalTomAmountLowRangeIntF) && (row.getFinalTomAmount() <= finalTomAmountHighRangeIntF)))
                .collect(Collectors.toList());
    }

}