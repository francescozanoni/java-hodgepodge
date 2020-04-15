package it.francescozanoni.app.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public interface Shared {

    static Scene getSceneFromFxml(URL fxmlFileUrl) throws Exception {
        // https://stackoverflow.com/questions/28387218/why-does-my-javafx-stage-not-want-to-load
        // http://java-no-makanaikata.blogspot.com/2012/11/javafx-fxml-root-value-already-specified.html
        FXMLLoader loader = FXMLLoaderFactory.get(fxmlFileUrl);
        return new Scene(loader.load());
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
