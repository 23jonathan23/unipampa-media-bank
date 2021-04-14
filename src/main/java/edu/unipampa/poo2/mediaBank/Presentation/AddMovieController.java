/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unipampa.poo2.mediaBank.Presentation;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;

/**
 * FXML Controller class
 *
 * @author mathe
 */
public class AddMovieController implements Initializable {
    
    @FXML
    private Label duration;
    
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
        
        String sTitle = title.getText();
        if (sTitle == null)
            return;
        
        String sDescription = description.getText();
        if (sDescription == null)
            return;
        
        String sGenre = genre.getText();
        if (sGenre == null)
            return;
        
        String sLanguage = language.getText();
        if (sLanguage == null)
            return;
        
        String sDirector = director.getText();
        if (sDirector == null)
            return;
        
        String sYear = year.getText();
        if (sYear == null)
            return;
        
    }
    
    private void setDuration() {
        File file = new File(path.getText());
        Media m = null;
        try {
            String[] a = file.toURI().toURL().toString().split(":");
            String resultado = a[0] + "://" + a[1] + ":" + a[2];
            System.out.println(resultado);
            m = new Media(resultado);
        } catch (Exception e){
            System.out.println("Algo deu errado :(");
            return;
        };
        long timeDuration =(long) m.getDuration().toSeconds();
        String stringDuration = "" + timeDuration;
        duration.setText(stringDuration);
    }
    
    public void setPath(String filePath) {
        path.setText(filePath);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setDuration();
    }    
}
