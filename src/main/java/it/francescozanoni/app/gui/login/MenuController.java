package it.francescozanoni.app.gui.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    private final Status status;
    public MenuItem home;
    public MenuItem login;

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
    protected void handleMenuClicked(ActionEvent event) throws Exception {

        MenuItem clickedMenu = (MenuItem) event.getSource();

        // https://stackoverflow.com/questions/45168721/how-to-change-scenes-in-menuitem-in-javafx-fxmlcontroller
        Shared.changeScene(myMenuBar, clickedMenu.getId());

    }

}
