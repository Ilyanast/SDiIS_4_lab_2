package org.bsuir;

import javafx.application.Application;
import javafx.stage.Stage;
import org.bsuir.view.MainView;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        MainView mainView = new MainView(720, 480);

        stage.setTitle("SDiIS_4_lab_2");
        stage.setScene(mainView.getScene());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}