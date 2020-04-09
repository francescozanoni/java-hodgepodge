package it.francescozanoni.app.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;

public class LoginController {

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) throws Exception {

        Status.getInstance().page = "home";

        Shared.changeScene((Node) event.getSource(), "home.fxml");

    }

}
