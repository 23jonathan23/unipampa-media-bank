package edu.unipampa.poo2.mediaBank.Presentation;

import edu.unipampa.poo2.mediaBank.Domain.Song;
import edu.unipampa.poo2.mediaBank.Domain.MediaDomain;
import edu.unipampa.poo2.mediaBank.Business.MediaHandler;
import edu.unipampa.poo2.mediaBank.Business.SongHandler;

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

public class AddSongController implements Initializable {

    public static double seconds;
    private File file;
    private SongHandler songHandler;
    private Communication communication1;
    private Communication communication2;
    private Song musicao;
    
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
    private void createSong(ActionEvent event) {
        long longSec = (long) AddSongController.seconds;
        int hours =(int) longSec / 3600;
        longSec -= hours * 3600;
        int minutes =(int) longSec / 60;
        longSec -= minutes * 60;
        int intSec = (int) longSec;
        
        LocalTime time = LocalTime.of(hours, minutes, intSec);
        
        String sTitle = title.getText();
        if (sTitle == null || sTitle.isEmpty() || sTitle.trim().isEmpty()){
            UserInterfaceController.showMessage("Aviso","O titulo informado não é valido, por favor informe outro valor", Alert.AlertType.WARNING);
            return;
        }
        
        String sDescription = description.getText();
        if (sDescription == null || sDescription.isEmpty() || sDescription.trim().isEmpty()){
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
        
        String sYear = year.getText();
        if (sYear == null || sYear.isEmpty() || sYear.trim().isEmpty()){
            UserInterfaceController.showMessage("Aviso","O ano informado não é valido, por favor informe outro valor", Alert.AlertType.WARNING);
            return;
        }
        int ano = 0;
        try {
            ano = Integer.parseInt(sYear);
        } catch (Exception e) {
            UserInterfaceController.showMessage("Aviso","O ano informado não é valido, por favor informe outro valor", Alert.AlertType.WARNING);
            return;
        };
        
        if (musicao != null) {
            updateSong();
            return;
        }
        
        Song song = new Song(sTitle, sDescription, sGenre, sLanguage, time, ano, this.file.getAbsolutePath().toString());
        song.setAuthors(communication1.getArrayStrings());
        song.setInterpreters(communication2.getArrayStrings());
        communication1.setArrayStrings(null);
        communication2.setArrayStrings(null);
        
        MediaDomain mediaSong = (MediaDomain) song;
        
        try {
            songHandler.createMedia(mediaSong);
            UserInterfaceController.showMessage("Informativo","Cadastro realizado com sucesso", Alert.AlertType.INFORMATION);
        } catch (IOException e){
            UserInterfaceController.showMessage("Erro","Ouve um problema ao tentar realizar o cadastro, por favor tente novamente", Alert.AlertType.ERROR);
        } catch (ClassNotFoundException cnf) {
            UserInterfaceController.showMessage("Erro","Ouve um problema ao tentar realizar o cadastro, por favor tente novamente", Alert.AlertType.ERROR);
        }
        
        Stage sta = (Stage) save.getScene().getWindow();
        sta.close();
    }
    
    private void updateSong() {
        musicao.setTitle(title.getText());
        musicao.setDescription(description.getText());
        musicao.setGenre(genre.getText());
        musicao.setLanguage(language.getText());
        musicao.setAuthors(communication1.getArrayStrings());
        musicao.setInterpreters(communication2.getArrayStrings());
        int ano = 0;
        try {
            ano = Integer.parseInt(year.getText());
        } catch (Exception e) {
            UserInterfaceController.showMessage("Aviso","O ano informado não é valido, por favor informe outro valor", Alert.AlertType.WARNING);
            return;
        }
        musicao.setYear(ano);
        
        try{
            songHandler.updateMedia(musicao);
            UserInterfaceController.showMessage("Informativo","Atualização realizada com sucesso", Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            UserInterfaceController.showMessage("Erro","Ouve um problema ao tentar realizar a atualização, por favor tente novamente", Alert.AlertType.ERROR);
        };
    }
    
    public void addPersonCallMethod() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddPerson.fxml"));
                
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            UserInterfaceController.showMessage("Erro","Ouve um problema inesperado, por favor tente novamente", Alert.AlertType.ERROR);
            return;
        };
        AddPersonController addP = loader.getController();
            
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        
        List<String> stri = null;
        if (musicao != null) {
            stri = musicao.getAuthors();
        }
        
        addP.loadTableFromOutside(stri, "Autores", "Adicionar adicionar", communication1);
        stage.show();
    }
    
    public void addPersonCallMethod2() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddPerson.fxml"));
                
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            UserInterfaceController.showMessage("Erro","Ouve um problema inesperado, por favor tente novamente", Alert.AlertType.ERROR);
            return;
        };
        AddPersonController addP = loader.getController();
            
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        
        List<String> stri = null;
        if (musicao != null) {
            stri = musicao.getInterpreters();
        }
        
        addP.loadTableFromOutside(stri, "Interpreters", "Adicionar interpreter", communication2);
        stage.show();
    }
    
    private void setDuration(File file) {
        Media m = null;
        try {
            String resultado = file.toURI().toString();   
            m = new Media(resultado);
        } catch (Exception e){
            UserInterfaceController.showMessage("Erro","Ouve um problema inesperado, por favor tente novamente", Alert.AlertType.ERROR);
            return;
        };
        MediaPlayer mp = new MediaPlayer(m);
        mp.setOnReady(new Runnable() {
            @Override
            public void run () {
                System.out.println(mp.getMedia().getDuration().toSeconds());
                AddSongController.seconds = mp.getMedia().getDuration().toSeconds();
                return;
            }
        });
    }
    
    public void setNewSong(File filePath, SongHandler sh, Communication cm1, Communication cm2) {
        path.setText(filePath.getAbsolutePath().toString());
        communication1 = cm1;
        communication2 = cm2;
        file = filePath;
        songHandler = sh;
        setDuration(filePath);
    }
    
    public void editSong(SongHandler sh, Communication cm1, Communication cm2, Song musica) {
        communication1 = cm1;
        communication2 = cm2;
        songHandler = sh;
        musicao = musica;
    } 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
