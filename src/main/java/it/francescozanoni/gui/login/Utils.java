package it.francescozanoni.gui.login;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public interface Utils {

    String resourceBasePath = "/login";

    static void changeScene(Node node, Page page) throws Exception {

        // https://stackoverflow.com/questions/13246211/javafx-how-to-get-stage-from-controller-during-initialization
        Stage stage = (Stage) node.getScene().getWindow();

        changeScene(stage, page);
    }

    static void changeScene(Stage stage, Page page) throws Exception {

        // TODO refactor by moving this statement out of here
        Status.getInstance().page = page;

        // https://stackoverflow.com/questions/28387218/why-does-my-javafx-stage-not-want-to-load
        // http://java-no-makanaikata.blogspot.com/2012/11/javafx-fxml-root-value-already-specified.html
        // https://stackoverflow.com/questions/8275499/how-to-call-getclass-from-a-static-method-in-java
        FXMLLoader loader = FXMLLoaderFactory.get(Utils.class.getResource(resourceBasePath + "/" + page + ".fxml"));
        Scene scene = new Scene(loader.load());

        stage.setScene(scene);
    }

}
