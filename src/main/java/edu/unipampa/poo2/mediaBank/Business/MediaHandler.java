package edu.unipampa.poo2.mediaBank.Business;

import edu.unipampa.poo2.mediaBank.Domain.FilterMedia;
import edu.unipampa.poo2.mediaBank.Infra.Repository.DBRepository;

import java.io.IOException;

import edu.unipampa.poo2.mediaBank.Business.utils.MediaSorter;

public abstract class MediaHandler {
    protected DBRepository repository;
    protected MediaSorter mediaSorter;
    protected static FilterMedia filter = new FilterMedia();
    protected boolean sortType = false;

    public MediaHandler(DBRepository repository, MediaSorter mediaSorter) {
        this.repository = repository;
        this.mediaSorter = mediaSorter;
    }

    public void sortByTitle(boolean type) {
        sortType = type;
    }

    public boolean removeFromSystem(int id) {
        try {
            repository.delete(id);
        } catch (ClassNotFoundException cnf) {
            return false;
        } catch (IOException ioe) {
            return false;
        }
        return true;
    }
    
    public abstract boolean deleteMedia(int id);
    public abstract boolean query(String title, String genre);
}
