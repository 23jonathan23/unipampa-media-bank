package edu.unipampa.poo2.mediaBank.Presentation;

import edu.unipampa.poo2.mediaBank.Business.MovieHandler;
import edu.unipampa.poo2.mediaBank.Business.SongHandler;
import edu.unipampa.poo2.mediaBank.Business.PhotoHandler;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
// import java.time.LocalTime;
// import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import edu.unipampa.poo2.mediaBank.Domain.MediaDomain;
// import edu.unipampa.poo2.mediaBank.Domain.Movie;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class UserInterfaceController implements Initializable {
    private MovieHandler movieHandler;
    private SongHandler songHandler;
    private PhotoHandler photoHandler;
    
    @FXML
    private Button addNewMediaButton;
    
    @FXML
    private Button searchByTitleButton;
    @FXML
    private TextField searchByTitleInput;
    @FXML
    private Button searchByGenreButton;
    @FXML
    private TextField searchByGenreInput;
    @FXML
    private TableView<MediaDomain> tableView;

    FileChooser fileChooser = new FileChooser();

    @FXML
    private void handleSearchByTitle(ActionEvent event) {
        String inputText = searchByTitleInput.getText();
        
        if (inputText == "") {
            tableView.setItems(getMediaList());
        } else {
            try {
                MovieHandler movieHandler = new MovieHandler();
                List<MediaDomain> mediasFound = movieHandler.getMediasByFilter(inputText, null);
                ObservableList<MediaDomain> mediasFoundObservableList = FXCollections.observableArrayList(mediasFound);
                tableView.setItems(mediasFoundObservableList);
            } catch (Exception e) {
                UserInterfaceController.showMessage("Erro","Ouve um problema inesperado, por favor tente novamente", Alert.AlertType.ERROR);
            }
        }

        searchByGenreInput.setText("");
    }

    @FXML
    private void handleSearchByGenre(ActionEvent event) {
        String inputText = searchByGenreInput.getText();
        
        if (inputText == "") {
            tableView.setItems(getMediaList());
        } else {
            try {
                MovieHandler movieHandler = new MovieHandler();
                List<MediaDomain> mediasFound = movieHandler.getMediasByFilter(null, inputText);
                ObservableList<MediaDomain> mediasFoundObservableList = FXCollections.observableArrayList(mediasFound);
                tableView.setItems(mediasFoundObservableList);
            } catch (Exception e) {
                UserInterfaceController.showMessage("Erro","Ouve um problema inesperado, por favor tente novamente", Alert.AlertType.ERROR);
            }
        }

        searchByTitleInput.setText("");
    }
    
    public static void showMessage(String title, String message, Alert.AlertType alertType) {
         Alert component = new Alert(Alert.AlertType.NONE);

        component.setAlertType(alertType);
        component.setTitle(title);
        component.setContentText(message);
        component.show();
    }

    private void loadMediaTable() {
        try {
            TableColumn<MediaDomain, String> pathColumn = new TableColumn<>("Caminho");
            pathColumn.setMinWidth(150);
            pathColumn.setCellValueFactory(new PropertyValueFactory<>("pathFile"));
    
            TableColumn<MediaDomain, String> titleColumn = new TableColumn<>("Título");
            titleColumn.setMinWidth(150);
            titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
    
            TableColumn<MediaDomain, String> descriptionColumn = new TableColumn<>("Descrição");
            descriptionColumn.setMinWidth(150);
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

            TableColumn<MediaDomain, String> genreColumn = new TableColumn<>("Gênero");
            genreColumn.setMinWidth(150);
            genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
    
            TableColumn<MediaDomain, String> languageColumn = new TableColumn<>("Idioma");
            languageColumn.setMinWidth(150);
            languageColumn.setCellValueFactory(new PropertyValueFactory<>("language"));
    
            TableColumn<MediaDomain, String> durationColumn = new TableColumn<>("Duração");
            durationColumn.setMinWidth(150);
            durationColumn.setCellValueFactory(new PropertyValueFactory<>("formattedDuration"));

            // TableColumn<MediaDomain, Calendar> dateColumn = new TableColumn<>("Data");
            // dateColumn.setMinWidth(150);
            // dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
    
            tableView.getColumns().addAll(
                pathColumn, titleColumn, descriptionColumn, genreColumn,
                languageColumn, durationColumn
            );
    
            tableView.setItems(getMediaList());   
        } catch (Exception e) {
            UserInterfaceController.showMessage("Erro","Ouve um problema inesperado, por favor feiche e abrar novamente", Alert.AlertType.ERROR);
        }
    }

    public static ObservableList<MediaDomain> getMediaList() {
        try {
            MovieHandler movieHandler = new MovieHandler();

            List<MediaDomain> mediaList = movieHandler.getMedias();

            ObservableList<MediaDomain> mediaObservableList = FXCollections.observableArrayList(mediaList);

            return mediaObservableList;
        } catch (Exception e) {
            UserInterfaceController.showMessage("Erro","Ouve um problema inesperado ao carregar as medias, por favor tente novamente", Alert.AlertType.ERROR);
        }

        return null;
    }

    @FXML
    private void handleAddNewMedia(ActionEvent event) {
        Stage stage = (Stage) addNewMediaButton.getScene().getWindow();

        File file = fileChooser.showOpenDialog(stage);
        if (file == null) {
            UserInterfaceController.showMessage("Aviso","Media não forneceida, por favor selecione um arquivo", Alert.AlertType.WARNING);
            return;
        }
        
        Path path = Paths.get(file.getAbsolutePath());
        String fileType = "";
        try {
            fileType = Files.probeContentType(path);
        } catch (IOException ioe) {
            UserInterfaceController.showMessage("Aviso","Arquivo de media invalido", Alert.AlertType.WARNING);
            return;
        };
        String[] typeSpliter = fileType.split("/");
        String type = typeSpliter[0];
        
        switch (type) {
            case "video":
                try {
                    movieHandler = new MovieHandler();
                } catch (Exception e){
                    UserInterfaceController.showMessage("Erro","Ouve um problema inesperado, por favor tente novamente", Alert.AlertType.ERROR);
                    return;
                };
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AddMovie.fxml"));
                
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException ex) {
                    UserInterfaceController.showMessage("Erro","Ouve um problema inesperado, por favor tente novamente", Alert.AlertType.ERROR);
                    return;
                };
                AddMovieController addM = loader.getController();
            
                Stage stage2 = new Stage();
                stage2.setScene(new Scene(root));
                stage2.show();
                Communication communication = new Communication();
                addM.setNewMovie(file, movieHandler, communication, tableView);
                
                break;

            case "audio":
                try {
                    songHandler = new SongHandler();
                } catch (Exception e) {
                    UserInterfaceController.showMessage("Erro","Ouve um problema inesperado, por favor tente novamente", Alert.AlertType.ERROR);
                    return;
                };
                FXMLLoader loader2 = new FXMLLoader(getClass().getResource("AddSong.fxml"));
                
                Parent root3 = null;
                try {
                    root3 = loader2.load();
                } catch (IOException ex) {
                    UserInterfaceController.showMessage("Erro","Ouve um problema inesperado, por favor tente novamente", Alert.AlertType.ERROR);
                    return;
                };
                AddSongController addS = loader2.getController();
            
                Stage stage3 = new Stage();
                stage3.setScene(new Scene(root3));
                stage3.show();
                Communication communication1 = new Communication();
                Communication communication2 = new Communication();
                addS.setNewSong(file, songHandler, communication1, communication2, tableView);
                
                
                break;
            case "image":
                try {
                    photoHandler = new PhotoHandler();
                } catch (Exception e) {
                    UserInterfaceController.showMessage("Erro","Ouve um problema inesperado, por favor tente novamente", Alert.AlertType.ERROR);
                    return;
                };
                FXMLLoader loader3 = new FXMLLoader(getClass().getResource("AddPhoto.fxml"));
                
                Parent root4 = null;
                try {
                    root4 = loader3.load();
                } catch (IOException ex) {
                    UserInterfaceController.showMessage("Erro","Ouve um problema inesperado, por favor tente novamente", Alert.AlertType.ERROR);
                    return;
                };
                AddPhotoController addP = loader3.getController();
            
                Stage stage4 = new Stage();
                stage4.setScene(new Scene(root4));
                stage4.show();
                Communication communication3 = new Communication();
                addP.setNewPhoto(file, photoHandler, communication3, tableView);
                
                break;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadMediaTable();
    }
}
