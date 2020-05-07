package it.francescozanoni.app.gui.login;

import javafx.application.Application;
import javafx.stage.Stage;

public class Starter extends Application {

    public static void main(String[] args) {
        Application.launch(Starter.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Utils.changeScene(stage, Page.LOGIN);

        stage.setTitle("API client");
        stage.show();
    }

}
