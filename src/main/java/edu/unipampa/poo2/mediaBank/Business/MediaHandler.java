package edu.unipampa.poo2.mediaBank.Business;

import edu.unipampa.poo2.mediaBank.Domain.FilterMedia;
import edu.unipampa.poo2.mediaBank.Domain.Media;
import edu.unipampa.poo2.mediaBank.Infra.Repository.DBRepository;

import java.io.IOException;

public abstract class MediaHandler {
    protected DBRepository repository;
    protected static FilterMedia filter = new FilterMedia();

    public MediaHandler(DBRepository repository) {
        this.repository = repository;
    }
    
    public void deleteMedia(int id) throws ClassNotFoundException, IOException {
        repository.delete(id);
    }
    public void createMedia(Media media) throws ClassNotFoundException, IOException {
        repository.insert(media);
    }
    public void updateMedia(Media media) throws ClassNotFoundException, IOException {
        repository.update(media);
    }
}
