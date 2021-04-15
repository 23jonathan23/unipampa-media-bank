/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unipampa.poo2.mediaBank.Presentation;

import edu.unipampa.poo2.mediaBank.Domain.Photo;
import edu.unipampa.poo2.mediaBank.Domain.MediaDomain;
import edu.unipampa.poo2.mediaBank.Business.MediaHandler;
import edu.unipampa.poo2.mediaBank.Business.PhotoHandler;
import java.io.File;
import java.io.IOException;

import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddPhotoController implements Initializable {
    
    private File file;
    private PhotoHandler photoHandler;
    private Communication communication;
    private Photo fotao;
    
    @FXML
    private Button pessoas;
    
    @FXML
    private Button save;
    
    @FXML 
    private Label path;
    
    @FXML
    private TextField title;
    
    @FXML
    private TextField description;
    
    @FXML
    private TextField photographer;
    
    @FXML
    private TextField place;
    
    @FXML
    private TextField day;
    
    @FXML
    private TextField month;
    
    @FXML
    private TextField year;
    
    @FXML
    private void createPhoto(ActionEvent event) {
        
        String sDay = day.getText();
        if (sDay == null) {
            return;
        }
        
        String sMonth = month.getText();
        if (sMonth == null) {
            return;
        }
        
        String sYear = year.getText();
        if (sYear == null) {
            return;
        }
        int dia = 0;
        int mes = 0;
        int ano = 0;
        try {
            dia = Integer.parseInt(sDay);
            mes = Integer.parseInt(sMonth);
            ano = Integer.parseInt(sYear);
        } catch (Exception e) {
            return;
        };
        
        Calendar data = Calendar.getInstance();
        data.set(ano, mes - 1, dia);
        
        String sTitle = title.getText();
        if (sTitle == null)
            return;
        
        String sDescription = description.getText();
        if (sDescription == null)
            return;
        
        String sPhotographer = photographer.getText();
        if (sPhotographer == null)
            return;
        
        String sPlace = place.getText();
        if (sPlace == null)
            return;
        
        if (fotao != null) {
            updatePhoto(data);
            return;
        }
        
        Photo photo = new Photo(sTitle, sDescription, sPhotographer, sPlace, data, file.getAbsolutePath().toString());
        photo.setPeople(communication.getArrayStrings());
        communication.setArrayStrings(null);
        
        MediaDomain mediaPhoto = (MediaDomain) photo;
        
        try {
            photoHandler.createMedia(mediaPhoto);
            System.out.println("deu certo o cadastro");
        } catch (IOException e){
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException cnf) {
            System.out.println(cnf.getMessage());
        }
        
        Stage sta = (Stage) save.getScene().getWindow();
        sta.close();
    }
    
    private void updatePhoto(Calendar data) {
        fotao.setTitle(title.getText());
        fotao.setDescription(description.getText());
        fotao.setPhotographer(photographer.getText());
        fotao.setPlace(place.getText());
        fotao.setDate(data);
        
        try {
            photoHandler.updateMedia(fotao);
        } catch (Exception e) {
            return;
        };
    }
    
    public void addPersonCallMethod(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddPerson.fxml"));
                
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            return;
        };
        AddPersonController addP = loader.getController();
            
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        
        List<String> stri = null;
        if (fotao != null) {
            stri = fotao.getPeople();
        }
        
        addP.loadTableFromOutside(stri, "Pessoas", "Adicionar pessoa", communication);
        stage.show();
    }

    public void setNewPhoto(File filePath, PhotoHandler ph, Communication cm) {
        path.setText(filePath.getAbsolutePath().toString());
        communication = cm;
        file = filePath;
        photoHandler = ph;
    }
    
    public void editPhoto(PhotoHandler ph, Communication cm, Photo foto) {
        communication = cm;
        photoHandler = ph;
        fotao = foto;
    } 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
