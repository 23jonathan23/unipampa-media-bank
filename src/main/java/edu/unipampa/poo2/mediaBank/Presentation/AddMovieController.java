/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unipampa.poo2.mediaBank.Presentation;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author mathe
 */
public class AddMovieController implements Initializable {

    @FXML 
    private Label path;
    
    private String filePath;
    public void myFunction(String filePath) {
        this.filePath = filePath;
        path.setText(this.filePath);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
