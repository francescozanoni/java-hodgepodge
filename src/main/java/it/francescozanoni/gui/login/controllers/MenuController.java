package it.francescozanoni.gui.login.controllers;

import it.francescozanoni.gui.login.Page;
import it.francescozanoni.gui.login.Utils;
import it.francescozanoni.gui.login.Status;
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
    MenuBar mainMenuBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        switch (status.page) {
            case LOGIN:
                mainMenuBar.getMenus().get(0).getItems().get(1).setDisable(true);
                break;
            case HOME:
                mainMenuBar.getMenus().get(0).getItems().get(0).setDisable(true);
                break;
        }
    }

    @FXML
    protected void handleMenuClicked(ActionEvent event) throws Exception {

        MenuItem clickedMenu = (MenuItem) event.getSource();

        // https://stackoverflow.com/questions/45168721/how-to-change-scenes-in-menuitem-in-javafx-fxmlcontroller
        Utils.changeScene(mainMenuBar, Page.valueOf(clickedMenu.getId().toUpperCase()));

    }

}
