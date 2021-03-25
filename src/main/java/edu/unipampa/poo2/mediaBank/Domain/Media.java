package edu.unipampa.poo2.mediaBank.Domain;

public abstract class Media {
    protected int Id;
    protected String PathFile;
    protected String Title;
    protected String Description;

    protected Media(String pathFile, String title, String description) {
        PathFile = pathFile;
        Title = title;
        Description = description;
    }

    protected int getId() {
        return Id;
    }

    protected void setId(int id) {
        Id = id;
    }

    protected String getPathFile() {
        return PathFile;
    }

    protected void setPathFile(String pathFile) {
        PathFile = pathFile;
    }

    protected String getTitle() {
        return Title;
    }

    protected void setTitle(String title) {
        Title = title;
    }

    protected String getDescription() {
        return Description;
    }

    protected void setDescription(String description) {
        Description = description;
    }
}
