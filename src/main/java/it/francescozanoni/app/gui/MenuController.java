package it.francescozanoni.app.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;

public class MenuController {

    @FXML
    MenuBar myMenuBar;

    @FXML
    protected void handleMenuClickedA(ActionEvent event) throws Exception {
        // https://stackoverflow.com/questions/45168721/how-to-change-scenes-in-menuitem-in-javafx-fxmlcontroller
        Shared.changeScene(myMenuBar, "home.fxml");
    }

    @FXML
    protected void handleMenuClickedB(ActionEvent event) throws Exception {
        // https://stackoverflow.com/questions/45168721/how-to-change-scenes-in-menuitem-in-javafx-fxmlcontroller
        Shared.changeScene(myMenuBar, "login.fxml");
    }

}
