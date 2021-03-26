package edu.unipampa.poo2.mediaBank.Domain;

import java.io.Serializable;

public abstract class Media implements Serializable{
    protected int Id;
    protected String PathFile;
    protected String Title;
    protected String Description;

    protected Media(String title, String description) {
        Title = title;
        Description = description;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getPathFile() {
        return PathFile;
    }

    public void setPathFile(String pathFile) {
        PathFile = pathFile;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
