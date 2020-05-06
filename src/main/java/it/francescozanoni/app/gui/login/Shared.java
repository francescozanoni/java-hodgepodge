package it.francescozanoni.app.gui.login;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Arrays;

public interface Shared {

    static String resourceBasePath = "/login";
    static String[] pages = {"login", "home"};

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

    static void changeScene(Node node, String page) throws Exception {

        Stage stage = Shared.getStageFromNode(node);

        Shared.changeScene(stage, page);
    }

    static void changeScene(Stage stage, String page) throws Exception {

        if (!Arrays.asList(Shared.pages).contains(page)) {
            throw new Exception("'" + page + "' is not allowed");
        }

        Status.getInstance().page = page;

        // https://stackoverflow.com/questions/8275499/how-to-call-getclass-from-a-static-method-in-java
        Scene scene = Shared.getSceneFromFxml(Shared.class.getResource(Shared.resourceBasePath + "/" + page + ".fxml"));
        stage.setScene(scene);
    }

}
