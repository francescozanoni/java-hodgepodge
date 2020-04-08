package it.francescozanoni.app.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public interface Shared {

    static Scene getSceneFromFxml(URL fxmlFileUrl) throws Exception {
        return new Scene(FXMLLoader.load(fxmlFileUrl));
    }

    static Stage getStageFromNode(Node node) {
        // https://stackoverflow.com/questions/13246211/javafx-how-to-get-stage-from-controller-during-initialization
        return (Stage) node.getScene().getWindow();
    }

    static void changeScene(Node node, String fxmlFileName) throws Exception {
        Stage stage = Shared.getStageFromNode(node);
        // https://stackoverflow.com/questions/8275499/how-to-call-getclass-from-a-static-method-in-java
        Scene scene = Shared.getSceneFromFxml(Shared.class.getResource("/" + fxmlFileName));
        stage.setScene(scene);
    }

}
