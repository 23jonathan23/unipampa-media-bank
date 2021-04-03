package edu.unipampa.poo2.mediaBank.Presentation;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class UserInterfaceController implements Initializable {
    private Label labelTest;

    private void handleClickTest(ActionEvent event) {
        labelTest.setText("Hey, don't click on me, it hurts :(");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // todo
    }
}
