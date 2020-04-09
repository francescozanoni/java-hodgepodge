package it.francescozanoni.app.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;

public class HomeController {

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) throws Exception {

        Status.getInstance().page = "login";

        Shared.changeScene((Node) event.getSource(), "login.fxml");

    }

}
