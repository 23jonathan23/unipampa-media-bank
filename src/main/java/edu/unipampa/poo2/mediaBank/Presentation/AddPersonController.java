/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unipampa.poo2.mediaBank.Presentation;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddPersonController implements Initializable {

    @FXML
    private TextField novo;
    
    @FXML
    private Button add;
    
    @FXML
    private Button save;
    
    @FXML
    private TableView<Pessoa> table;
    
    private String type;
    private String message;
    private List<String> pessoa;
    Button[] button;
    ObservableList<Pessoa> pessoal;
    TableColumn nome;
    TableColumn delete;
    Communication communication;
    
    public void loadTable(List<String> people, String type, String message) {
        pessoa = people;
        this.type = type;
        this.message = message;
        
        table.getColumns().clear();
        table.getColumns().addAll(nome, delete);
        
        if (pessoal != null){
            pessoal.removeAll();
        }
        pessoal = getList(people);
        
        table.setItems(pessoal);
    }
    
    public void register(ActionEvent event) {
        communication.setArrayStrings(pessoa);
        
        Stage sta = (Stage) save.getScene().getWindow();
        sta.close();
    }
    
    public void createPerson(ActionEvent event) {
        if (novo.getText() == null) {
            return;
        }
        String newPerson = novo.getText();
        pessoa.add(newPerson);
        loadTable(this.pessoa, this.type, this.message);
    }
    
    public void loadTableFromOutside(List<String> people, String type, String message, Communication cm) {
        nome = new TableColumn<>("Nome");
        delete = new TableColumn<>("Delete");
        communication = cm;
        
        if (people == null) {
            people = new ArrayList<>();
        }
        nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        delete.setCellValueFactory(new PropertyValueFactory<>("delete"));
        loadTable(people, type, message);
    }
    
    private void handleButtonAction(ActionEvent event) {
        for (int i = 0; i < pessoa.size(); i++) {
            if (event.getSource() == button[i]){
                pessoa.remove(i);
                break;
            }
        }
        loadTable(this.pessoa, this.type, this.message);
    }
    
    private ObservableList<Pessoa> getList(List<String> people) {
        List<Pessoa> pessoal = new ArrayList<>();
        button = new Button[people.size()];
        Pessoa pessoa;
        for (int i = 0; i < people.size(); i++) {
            button[i] = new Button("delete");
            button[i].setOnAction(this::handleButtonAction);
            pessoa = new Pessoa(people.get(i), button[i]);
            pessoal.add(pessoa);
        }
        
        ObservableList<Pessoa> observablePessoa = FXCollections.observableArrayList(pessoal);
        return observablePessoa;
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
