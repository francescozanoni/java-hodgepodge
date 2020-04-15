package it.francescozanoni.app.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    private Status status;

    public MenuController(Status status) {
        this.status = status;
    }

    @FXML
    MenuBar myMenuBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (status.page.equals("login")) {
            myMenuBar.getMenus().get(0).getItems().get(1).setDisable(true);
        }
        if (status.page.equals("home")) {
            myMenuBar.getMenus().get(0).getItems().get(0).setDisable(true);
        }
    }

    @FXML
    protected void handleHomeMenuClicked(ActionEvent event) throws Exception {
        status.page = "home";

        // https://stackoverflow.com/questions/45168721/how-to-change-scenes-in-menuitem-in-javafx-fxmlcontroller
        Shared.changeScene(myMenuBar, "home.fxml");
    }

    @FXML
    protected void handleLoginMenuClicked(ActionEvent event) throws Exception {
        status.page = "login";

        // https://stackoverflow.com/questions/45168721/how-to-change-scenes-in-menuitem-in-javafx-fxmlcontroller
        Shared.changeScene(myMenuBar, "login.fxml");
    }

}
