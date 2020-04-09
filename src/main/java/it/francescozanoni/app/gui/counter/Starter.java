package it.francescozanoni.app.gui.counter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// @see https://riptutorial.com/javafx/example/7285/nested-controllers

public class Starter extends Application {

    public static void main(String[] args) {
        Application.launch(Starter.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("/counter/outer.fxml"));
        Scene scene = new Scene(parent);

        stage.setTitle("Starter");
        stage.setScene(scene);
        stage.show();
    }
}
