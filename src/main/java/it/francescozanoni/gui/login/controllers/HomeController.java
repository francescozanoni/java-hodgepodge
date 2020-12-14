package it.francescozanoni.gui.login.controllers;

import it.francescozanoni.gui.login.Page;
import it.francescozanoni.gui.login.Request;
import it.francescozanoni.gui.login.Status;
import it.francescozanoni.gui.login.Utils;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;
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

    public void handleAddRequestsButtonAction(ActionEvent actionEvent) throws IOException {
        ObservableList<Request> data = requestTable.getItems();

        data.clear();

        // https://crunchify.com/java-properties-file-how-to-read-config-properties-values-in-java
        Properties config = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("login/config.properties");
        config.load(inputStream);

        Enumeration<Object> keys = config.keys();
        do {
            String url = config.getProperty((String) keys.nextElement());
            String time = String.valueOf(System.currentTimeMillis());
            data.add(new Request(url, time));
        } while (keys.hasMoreElements());

    }

    public void handleRemoveRequestButtonAction(ActionEvent actionEvent) {
        ObservableList<Request> data = requestTable.getItems();
        int numberOfRequests = data.size();
        if (numberOfRequests > 0) {
            data.remove(numberOfRequests - 1);
        }
    }
}
