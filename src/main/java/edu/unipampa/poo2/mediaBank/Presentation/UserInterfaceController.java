package edu.unipampa.poo2.mediaBank.Presentation;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

import edu.unipampa.poo2.mediaBank.Business.MovieHandler;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class UserInterfaceController implements Initializable {
    @FXML
    private Button addNewMediaButton;
    @FXML
    private ChoiceBox<String> orderDropdown;
    @FXML
    private ChoiceBox<String> filterDropdown;
    @FXML
    private TableView<Movie> tableView;

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

    private void loadMediaTable() {
        TableColumn<Movie, String> titleColumn = (TableColumn<Movie, String>) tableView.getColumns().get(0);
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Movie, String> descriptionColumn = (TableColumn<Movie, String>) tableView.getColumns().get(1);
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        tableView.setItems(getMovieList());;
    }

    private ObservableList<Movie> getMovieList() {
        try {
            MovieHandler movieHandler = new MovieHandler();

            Movie movie1 = new Movie("Movie 1", "1", "1", "1", "1", LocalTime.now(), 2021, "C:/test");
            Movie movie2 = new Movie("Movie 2", "2", "2", "2", "2", LocalTime.now(), 2021, "C:/test");
            movieHandler.createMedia(movie1);
            movieHandler.createMedia(movie2);

            List<Movie> movieList = movieHandler.getMovies();

            ObservableList<Movie> movieObservableList = FXCollections.observableArrayList(movieList);

            return movieObservableList;
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
        loadDropdownData();
        loadMediaTable();
    }
}
