package it.francescozanoni.gui.counter;

import javafx.fxml.FXML;

public class OuterController {

    // controller of counter.fxml injected here
    @FXML
    private CounterController countController;

    @FXML
    private void incrementFromOut() {
        countController.increment();
    }

}