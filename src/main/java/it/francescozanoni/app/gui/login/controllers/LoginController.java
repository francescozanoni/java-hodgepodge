package it.francescozanoni.app.gui.login.controllers;

import it.francescozanoni.app.gui.login.Page;
import it.francescozanoni.app.gui.login.Utils;
import it.francescozanoni.app.gui.login.Status;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    TextField usernameField;

    private final Status status;

    public LoginController(Status status) {
        this.status = status;
    }

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) throws Exception {

        if (!usernameField.getText().trim().equals("")) {
            status.username = usernameField.getText().trim();
        }

        Utils.changeScene((Node) event.getSource(), Page.HOME);

    }

}
