package edu.unipampa.poo2.mediaBank.Business;

import edu.unipampa.poo2.mediaBank.Domain.FilterMedia;
import edu.unipampa.poo2.mediaBank.Infra.Repository.DBRepository;

import java.io.IOException;

import edu.unipampa.poo2.mediaBank.Business.utils.MediaSorter;

public abstract class MediaHandler {
    protected DBRepository repository;
    protected MediaSorter mediaSorter;
    protected FilterMedia filter;
    protected boolean sortType = false;

    public void sortByTitle(boolean type) {
        sortType = type;
    }
    public boolean deleteMedia(int id) {
        try {
            repository.delete(id);
        } catch (ClassNotFoundException cnf) {
            return false;
        } catch (IOException ioe) {
            return false;
        }

        return query(filter.getTitle(), filter.getGenre());
    }
    
    public abstract boolean query(String title, String genre);
}
