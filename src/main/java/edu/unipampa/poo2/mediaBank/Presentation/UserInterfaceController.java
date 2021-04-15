package edu.unipampa.poo2.mediaBank.Presentation;

import edu.unipampa.poo2.mediaBank.Business.MediaHandler;
import edu.unipampa.poo2.mediaBank.Business.MovieHandler;
import edu.unipampa.poo2.mediaBank.Business.SongHandler;
import edu.unipampa.poo2.mediaBank.Business.PhotoHandler;
import edu.unipampa.poo2.mediaBank.Infra.Repository.DBRepository;
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
    
    private MovieHandler movieHandler;
    private SongHandler songHandler;
    private PhotoHandler photoHandler;
    
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
        if (file == null) {
            return;
        }
        
        Path path = Paths.get(file.getAbsolutePath());
        String fileType = "";
        try {
            fileType = Files.probeContentType(path);
        } catch (IOException ioe) {
            return;
        };
        String[] typeSpliter = fileType.split("/");
        String type = typeSpliter[0];
        System.out.println(file.getAbsolutePath());
        
        switch (type) {
            case "video":
                try {

                    movieHandler = new MovieHandler();
                } catch (Exception e){
                    return;
                };
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AddMovie.fxml"));
                
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException ex) {
                    return;
                };
                AddMovieController addM = loader.getController();
            
                Stage stage2 = new Stage();
                stage2.setScene(new Scene(root));
                stage2.show();
                Communication communication = new Communication();
                addM.setNewMovie(file, movieHandler, communication);
                
                break;

            case "audio":
                try {
                    songHandler = new SongHandler();
                } catch (Exception e) {
                    return;
                };
                FXMLLoader loader2 = new FXMLLoader(getClass().getResource("AddSong.fxml"));
                
                Parent root3 = null;
                try {
                    root3 = loader2.load();
                } catch (IOException ex) {
                    return;
                };
                AddSongController addS = loader2.getController();
            
                Stage stage3 = new Stage();
                stage3.setScene(new Scene(root3));
                stage3.show();
                Communication communication1 = new Communication();
                Communication communication2 = new Communication();
                addS.setNewSong(file, songHandler, communication1, communication2);
                
                
                break;
            case "image":
                try {
                    photoHandler = new PhotoHandler();
                } catch (Exception e) {
                    return;
                };
                FXMLLoader loader3 = new FXMLLoader(getClass().getResource("AddPhoto.fxml"));
                
                Parent root4 = null;
                try {
                    root4 = loader3.load();
                } catch (IOException ex) {
                    return;
                };
                AddPhotoController addP = loader3.getController();
            
                Stage stage4 = new Stage();
                stage4.setScene(new Scene(root4));
                stage4.show();
                Communication communication3 = new Communication();
                addP.setNewPhoto(file, photoHandler, communication3);
                
                break;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadDropdownData();
    }
}
