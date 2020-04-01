package it.francescozanoni.app.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class ApiClient extends Application {

    public static void main(String[] args) {
        Application.launch(ApiClient.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = this.getSceneFromFxml(getClass().getResource("/api_client.fxml"));

        stage.setTitle("API client");
        stage.setScene(scene);
        stage.show();
    }

    protected Scene getSceneFromFxml(URL fxmlFileUrl) throws Exception {
        return new Scene(FXMLLoader.load(fxmlFileUrl));
    }
}
