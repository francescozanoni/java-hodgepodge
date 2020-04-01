package it.francescozanoni.app.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;
import java.net.URL;

public class ApiClientController {

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) throws Exception {

        // Change scene
        Stage stage = this.getStageFromNode((Node) event.getSource());
        Scene newScene = this.getSceneFromFxml(getClass().getResource("/api_client_2.fxml"));
        stage.setScene(newScene);

    }

    protected Stage getStageFromNode(Node node) {
        // https://stackoverflow.com/questions/13246211/javafx-how-to-get-stage-from-controller-during-initialization
        return (Stage) node.getScene().getWindow();
    }

    protected Scene getSceneFromFxml(URL fxmlFileUrl) throws Exception {
        return new Scene(FXMLLoader.load(fxmlFileUrl));
    }

}
