/*
 * Copyright (c) 2011, 2012 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  - Neither the name of Oracle nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package it.francescozanoni.app.gui.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class FXMLExampleController {

    @FXML
    private Text actionTarget;
    @FXML
    private Text warningTarget;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;

    public void initialize() {
        // Focus out
        // https://stackoverflow.com/questions/42943652/how-to-trigger-an-event-on-focus-out-for-a-textfield-in-javafx-using-fxml?rq=1
        usernameField.focusedProperty().addListener((ov, oldV, newV) -> {
            if (!newV) {
                // https://stackoverflow.com/questions/35308219/how-to-format-a-text-field-javafx
                usernameField.setStyle("-fx-font-weight: normal");
            }
        });
    }

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        warningTarget.setText("");
        actionTarget.setText("");

        if (passwordField.getText().equals("")) {
            warningTarget.setText("EMPTY PASSWORD");
            return;
        }

        actionTarget.setText("Sign in button pressed");
    }

    @FXML
    protected void handleUsernameClicked(MouseEvent event) {
        TextField usernameField = (TextField) event.getSource();
        String usernameFieldContent = usernameField.getText();

        actionTarget.setText("Username: " + usernameFieldContent);

        // https://stackoverflow.com/questions/35308219/how-to-format-a-text-field-javafx
        usernameField.setStyle("-fx-font-weight: bold");
    }

}
