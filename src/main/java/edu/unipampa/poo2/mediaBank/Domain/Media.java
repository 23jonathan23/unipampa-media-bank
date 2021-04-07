package edu.unipampa.poo2.mediaBank.Domain;

import java.io.Serializable;

public abstract class Media implements Serializable {
    private int _id;
    private String _pathFile;
    private String _title;
    private String _description;
    private static final long serialVersionUID = 2247710441387437004L;

    protected Media(String title, String description, String pathFile) {
        _title = title;
        _description = description;
        _pathFile = pathFile;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        _id = id;
    }

    public String getPathFile() {
        return _pathFile;
    }

    public void setPathFile(String pathFile) {
        _pathFile = pathFile;
    }

    public String getTitle() {
        return _title;
    }

    public void setTitle(String title) {
        _title = title;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }
}
