/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unipampa.poo2.mediaBank.Presentation;

import edu.unipampa.poo2.mediaBank.Domain.Movie;
import edu.unipampa.poo2.mediaBank.Domain.MediaDomain;
import edu.unipampa.poo2.mediaBank.Business.MediaHandler;
import edu.unipampa.poo2.mediaBank.Business.MovieHandler;

import java.io.IOException;

import java.lang.Thread;
import java.time.LocalTime;
import java.io.File;
import java.net.URI;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.util.List;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


/**
 * FXML Controller class
 *
 * @author mathe
 */
public class AddMovieController implements Initializable {
    
    public static double seconds;
    private File file;
    private MovieHandler movieHandler;
    private Communication communication;
    private Movie filmao;
    
    @FXML
    private Button save;
    
    @FXML 
    private Label path;
    
    @FXML
    private TextField title;
    
    @FXML
    private TextField description;
    
    @FXML
    private TextField genre;
    
    @FXML
    private TextField language;
    
    @FXML
    private TextField director;
    
    @FXML
    private TextField year;
    
    @FXML
    private void createMovie(ActionEvent event) {
        long longSec = (long) AddMovieController.seconds;
        int hours =(int) longSec / 3600;
        longSec -= hours * 3600;
        int minutes =(int) longSec / 60;
        longSec -= minutes * 60;
        int intSec = (int) longSec;
        
        LocalTime time = LocalTime.of(hours, minutes, intSec);
        
        String sTitle = title.getText();
        if (sTitle == null || sTitle.isEmpty() || sTitle.trim().isEmpty()) {
            UserInterfaceController.showMessage("Aviso","O titulo informado não é valido, por favor informe outro valor", Alert.AlertType.WARNING);
            return;
        }
        
        String sDescription = description.getText();
        if (sDescription == null){
            UserInterfaceController.showMessage("Aviso","A descrição informada não é valida, por favor informe outro valor", Alert.AlertType.WARNING);
            return;
        }
        
        String sGenre = genre.getText();
        if (sGenre == null || sGenre.isEmpty() || sGenre.trim().isEmpty()){
            UserInterfaceController.showMessage("Aviso","O genero informado não é valido, por favor informe outro valor", Alert.AlertType.WARNING);
            return;
        }
        
        String sLanguage = language.getText();
        if (sLanguage == null || sLanguage.isEmpty() || sLanguage.trim().isEmpty()){
            UserInterfaceController.showMessage("Aviso","A linguagem informada não é valida, por favor informe outro valor", Alert.AlertType.WARNING);
            return;
        }
        
        String sDirector = director.getText();
        if (sDirector == null || sDirector.isEmpty() || sDirector.trim().isEmpty()){
            UserInterfaceController.showMessage("Aviso","O diretor informado não é valida, por favor informe outro valor", Alert.AlertType.WARNING);
            return;
        }
        
        String sYear = year.getText();
        if (sYear == null || sYear.isEmpty() || sYear.trim().isEmpty()){
            UserInterfaceController.showMessage("Aviso","O ano informado não é valido, por favor informe outro valor", Alert.AlertType.WARNING);
            return;
        }
        
        int ano = 0;
        try {
            ano = Integer.parseInt(sYear);
        } catch (Exception e) {
            UserInterfaceController.showMessage("Aviso","O ano informado não é valido, por favor informe outro valor", AlertType.WARNING);
            return;
        };
        
        if (filmao != null) {
            updateMovie();
            return;
        }
        
        Movie movie = new Movie(sTitle, sDescription, sGenre, sLanguage, sDirector, time, ano, this.file.getAbsolutePath().toString());
        movie.setActors(communication.getArrayStrings());
        communication.setArrayStrings(null);
        
        MediaDomain mediaMovie = (MediaDomain) movie;
        
        try {
            movieHandler.createMedia(mediaMovie);
            UserInterfaceController.showMessage("Informativo","Cadastro realizado com sucesso", AlertType.INFORMATION);
        } catch (IOException e){
            UserInterfaceController.showMessage("Erro","Ouve um problema ao tentar realizar o cadastro, por favor tente novamente", AlertType.ERROR);
        } catch (ClassNotFoundException cnf) {
            UserInterfaceController.showMessage("Erro","Ouve um problema ao tentar realizar o cadastro, por favor tente novamente", AlertType.ERROR);
        }
        
        Stage sta = (Stage) save.getScene().getWindow();
        sta.close();
    }
    
    private void updateMovie() {
        filmao.setTitle(title.getText());
        filmao.setDescription(description.getText());
        filmao.setGenre(genre.getText());
        filmao.setLanguage(language.getText());
        filmao.setDirector(director.getText());
        filmao.setActors(communication.getArrayStrings());
        int ano = 0;
        try {
            ano = Integer.parseInt(year.getText());
        } catch (Exception e) {
            UserInterfaceController.showMessage("Aviso","O ano informado não é valido, por favor informe outro valor", AlertType.WARNING);
            return;
        }
        filmao.setYear(ano);
        
        try{
            movieHandler.updateMedia(filmao);
            UserInterfaceController.showMessage("Informativo","Atualização realizada com sucesso", Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            UserInterfaceController.showMessage("Erro","Ouve um problema ao tentar realizar a atualização, por favor tente novamente", AlertType.ERROR);
        };
    }
    
    public void addPersonCallMethod() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddPerson.fxml"));
                
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            UserInterfaceController.showMessage("Erro","Ouve um problema inesperado, por favor tente novamente", AlertType.ERROR);
            return;
        };
        AddPersonController addP = loader.getController();
            
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        
        List<String> stri = null;
        if (filmao != null) {
            stri = filmao.getActors();
        }
        
        addP.loadTableFromOutside(stri, "Atores", "Adicionar atores", communication);
        stage.show();
    }
    
    private void setDuration(File file) {
        Media m = null;
        try {
            String resultado = file.toURI().toString();     
            m = new Media(resultado);
        } catch (Exception e){
            UserInterfaceController.showMessage("Erro","Ouve um problema inesperado, por favor tente novamente", AlertType.ERROR);
            return;
        };
        MediaPlayer mp = new MediaPlayer(m);
        mp.setOnReady(new Runnable() {
            @Override
            public void run () {
                System.out.println(mp.getMedia().getDuration().toSeconds());
                AddMovieController.seconds = mp.getMedia().getDuration().toSeconds();
                return;
            }
        });
    }
    
    public void setNewMovie(File filePath, MovieHandler mh, Communication cm) {
        path.setText(filePath.getAbsolutePath().toString());
        communication = cm;
        file = filePath;
        movieHandler = mh;
        setDuration(filePath);
    }
    
    public void editMovie(MovieHandler mh, Communication cm, Movie filme) {
        communication = cm;
        movieHandler = mh;
        filmao = filme;
    } 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
