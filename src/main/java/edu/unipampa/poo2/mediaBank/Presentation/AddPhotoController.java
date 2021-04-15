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
import javafx.scene.control.Alert;
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
        if (sDay == null || sDay.isEmpty() || sDay.trim().isEmpty()) {
            UserInterfaceController.showMessage("Aviso","O dia informado não é valido, por favor informe outro valor", Alert.AlertType.WARNING);
            return;
        }
        
        String sMonth = month.getText();
        if (sMonth == null || sMonth.isEmpty() || sMonth.trim().isEmpty()) {
            UserInterfaceController.showMessage("Aviso","O mês informado não é valido, por favor informe outro valor", Alert.AlertType.WARNING);
            return;
        }
        
        String sYear = year.getText();
        if (sYear == null || sYear.isEmpty() || sYear.trim().isEmpty()) {
            UserInterfaceController.showMessage("Aviso","O ano informado não é valido, por favor informe outro valor", Alert.AlertType.WARNING);
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
            UserInterfaceController.showMessage("Aviso","A data informada não é valida, por favor informe outro valor", Alert.AlertType.WARNING);
            return;
        };
        
        Calendar data = Calendar.getInstance();
        data.set(ano, mes - 1, dia);
        
        String sTitle = title.getText();
        if (sTitle == null || sTitle.isEmpty() || sTitle.trim().isEmpty()) {
            UserInterfaceController.showMessage("Aviso","O titulo informado não é valido, por favor informe outro valor", Alert.AlertType.WARNING);
            return;
        }
        
        String sDescription = description.getText();
        if (sDescription == null || sDescription.isEmpty() || sDescription.trim().isEmpty()){
            UserInterfaceController.showMessage("Aviso","A descrição informada não é valida, por favor informe outro valor", Alert.AlertType.WARNING);
            return;
        }
        
        String sPhotographer = photographer.getText();
        if (sPhotographer == null || sPhotographer.isEmpty() || sPhotographer.trim().isEmpty()){
            UserInterfaceController.showMessage("Aviso","O Fotográfo informado não é valido, por favor informe outro valor", Alert.AlertType.WARNING);
            return;
        }
        
        String sPlace = place.getText();
        if (sPlace == null || sPlace.isEmpty() || sPlace.trim().isEmpty()){
            UserInterfaceController.showMessage("Aviso","O lugar informado não é valido, por favor informe outro valor", Alert.AlertType.WARNING);
            return;
        }
        
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
            UserInterfaceController.showMessage("Informativo","Cadastro realizado com sucesso", Alert.AlertType.INFORMATION);
        } catch (IOException e){
            UserInterfaceController.showMessage("Erro","Ouve um problema ao tentar realizar o cadastro, por favor tente novamente", Alert.AlertType.ERROR);
        } catch (ClassNotFoundException cnf) {
            UserInterfaceController.showMessage("Erro","Ouve um problema ao tentar realizar o cadastro, por favor tente novamente", Alert.AlertType.ERROR);
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
            UserInterfaceController.showMessage("Informativo","Atualização realizada com sucesso", Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            UserInterfaceController.showMessage("Erro","Ouve um problema ao tentar realizar a atualização, por favor tente novamente", Alert.AlertType.ERROR);
            return;
        };
    }
    
    public void addPersonCallMethod(ActionEvent event) {
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
