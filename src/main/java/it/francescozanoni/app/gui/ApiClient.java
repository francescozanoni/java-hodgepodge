package it.francescozanoni.app.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ApiClient extends Application implements Shared {

    public static void main(String[] args) {
        Application.launch(ApiClient.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = Shared.getSceneFromFxml(getClass().getResource("/login.fxml"));

        stage.setTitle("API client");
        stage.setScene(scene);
        stage.show();
    }
}
