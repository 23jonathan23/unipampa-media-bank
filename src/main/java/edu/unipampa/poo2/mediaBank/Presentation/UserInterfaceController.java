package edu.unipampa.poo2.mediaBank.Presentation;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class UserInterfaceController implements Initializable {
    @FXML
    private Button addNewMediaButton;
    @FXML
    private ChoiceBox<String> orderDropdown;
    @FXML
    private ChoiceBox<String> filterDropdown;

    FileChooser fileChooser = new FileChooser();

    private void loadDropdownData() {
        orderDropdown.getItems().add("A-Z");
        orderDropdown.getItems().add("Mais recentes");
        orderDropdown.getItems().add("Mais antigos");
        orderDropdown.getSelectionModel().select(0);

        filterDropdown.getItems().add("Gênero");
    }

    @FXML
    private void handleAddNewMedia(ActionEvent event) {
        Stage stage = (Stage) addNewMediaButton.getScene().getWindow();

        File file = fileChooser.showOpenDialog(stage);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadDropdownData();
    }
}
