
package edu.unipampa.poo2.mediaBank.Presentation;

import javafx.scene.control.Button;

public class Pessoa {
    private String nome;
    private Button delete;
    
    public Pessoa(String nome, Button delete) {
        this.nome = nome;
        this.delete = delete;
    }
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Button getDelete() {
        return delete;
    }
    public void setDelete(Button delete) {
        this.delete = delete;
    }
}
