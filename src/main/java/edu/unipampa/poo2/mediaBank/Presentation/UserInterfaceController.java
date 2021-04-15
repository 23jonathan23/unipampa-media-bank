package edu.unipampa.poo2.mediaBank.Presentation;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import edu.unipampa.poo2.mediaBank.Business.MovieHandler;
import edu.unipampa.poo2.mediaBank.Domain.MediaDomain;
import edu.unipampa.poo2.mediaBank.Domain.Movie;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class UserInterfaceController implements Initializable {
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
        
        if (inputText == "") tableView.setItems(getMediaList());

        try {
            MovieHandler movieHandler = new MovieHandler();
            List<MediaDomain> mediasFound = movieHandler.getMediasByFilter(inputText, "");
            ObservableList<MediaDomain> mediasFoundObservableList = FXCollections.observableArrayList(mediasFound);
            tableView.setItems(mediasFoundObservableList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        searchByGenreInput.setText("");
    }

    @FXML
    private void handleSearchByGenre(ActionEvent event) {
        String inputText = searchByGenreInput.getText();
        
        if (inputText == "") tableView.setItems(getMediaList());

        try {
            MovieHandler movieHandler = new MovieHandler();
            List<MediaDomain> mediasFound = movieHandler.getMediasByFilter("", inputText);
            ObservableList<MediaDomain> mediasFoundObservableList = FXCollections.observableArrayList(mediasFound);
            tableView.setItems(mediasFoundObservableList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        searchByTitleInput.setText("");
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

            TableColumn<MediaDomain, Calendar> dateColumn = new TableColumn<>("Data");
            dateColumn.setMinWidth(150);
            dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
    
            tableView.getColumns().addAll(
                pathColumn, titleColumn, descriptionColumn, genreColumn,
                languageColumn, durationColumn, dateColumn
            );
    
            tableView.setItems(getMediaList());   
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private ObservableList<MediaDomain> getMediaList() {
        try {
            MovieHandler movieHandler = new MovieHandler();

            Movie movie1 = new Movie("A - Título do filme 1", "A - Descrição do filme 1", "Ação", "Inglês", "1", LocalTime.now(), 2021, "C:/user/filme-1");
            movieHandler.createMedia(movie1);
            Movie movie2 = new Movie("B - Título do filme 2", "B - Descrição do filme 2", "Terror", "Português", "2", LocalTime.now(), 2021, "C:/user/filme-2");
            movieHandler.createMedia(movie2);
            Movie movie3 = new Movie("C - Título do filme 3", "C - Descrição do filme 3", "Aventura", "Francês", "3", LocalTime.now(), 3021, "C:/user/filme-3");
            movieHandler.createMedia(movie3);

            List<MediaDomain> mediaList = movieHandler.getMedias();

            ObservableList<MediaDomain> mediaObservableList = FXCollections.observableArrayList(mediaList);

            return mediaObservableList;
        } catch (Exception e) {
            System.out.println("Algo deu errado :(");
            System.out.println(e);
        }

        return null;
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
        loadMediaTable();
    }
}
