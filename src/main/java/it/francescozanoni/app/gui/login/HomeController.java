package it.francescozanoni.app.gui.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    private final Status status;

    @FXML
    Label usernameLabel;

    public HomeController(Status status) {
        this.status = status;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (status.username != null && !status.username.equals("")) {
            usernameLabel.setText(status.username);
        } else {
            usernameLabel.setText("NOBODY");
        }
    }

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) throws Exception {

        status.username = null;

        Shared.changeScene((Node) event.getSource(), "login");

    }

}
