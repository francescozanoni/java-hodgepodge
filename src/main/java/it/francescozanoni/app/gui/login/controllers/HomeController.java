package it.francescozanoni.app.gui.login.controllers;

import it.francescozanoni.app.gui.login.Page;
import it.francescozanoni.app.gui.login.Request;
import it.francescozanoni.app.gui.login.Utils;
import it.francescozanoni.app.gui.login.Status;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    private final Status status;

    @FXML
    TableView<Request> requestTable;

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
        Utils.changeScene((Node) event.getSource(), Page.LOGIN);
    }

    public void handleAddRequestButtonAction(ActionEvent actionEvent) {
        ObservableList<Request> data = requestTable.getItems();
        if (data.size() > 0) {
            data.remove(0);
        }
        data.add(new Request("http://ciao.it", String.valueOf(System.currentTimeMillis())));
    }
}
