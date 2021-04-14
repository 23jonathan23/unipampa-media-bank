package edu.unipampa.poo2.mediaBank.Presentation;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        if (orderDropdown != null) {
            orderDropdown.getItems().add("A-Z");
        orderDropdown.getItems().add("Mais recentes");
        orderDropdown.getItems().add("Mais antigos");
        orderDropdown.getSelectionModel().select(0);
        
        }
        
        if (filterDropdown != null) {
            filterDropdown.getItems().add("Gênero");
        }

    }

    @FXML
    private void handleAddNewMedia(ActionEvent event) {
        Stage stage = (Stage) addNewMediaButton.getScene().getWindow();

        File file = fileChooser.showOpenDialog(stage);
        Path path = Paths.get(file.getAbsolutePath());
        String fileType = "";
        try {
            fileType = Files.probeContentType(path);
        } catch (IOException ioe) {};
        String[] typeSpliter = fileType.split("/");
        String type = typeSpliter[0];
        System.out.println(file.getAbsolutePath());
        
        switch (type) {
            case "video":
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AddMovie.fxml"));
                
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException ex) {};
                AddMovieController addM = loader.getController();
            
                Stage stage2 = new Stage();
                stage2.setScene(new Scene(root));
                stage2.show();
                addM.setPath(file.getAbsolutePath());
                
                break;

            case "audio":
                
                break;
            case "image":
                
                break;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadDropdownData();
    }
}
