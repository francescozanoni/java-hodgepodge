package it.francescozanoni.app.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    TextField usernameField;

    private Status status;

    public LoginController() {
        this.status = Status.getInstance();
    }

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) throws Exception {

        if (!usernameField.getText().trim().equals("")) {
            status.username = usernameField.getText().trim();
        }

        status.page = "home";

        Shared.changeScene((Node) event.getSource(), "home.fxml");

    }

}
