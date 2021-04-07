package edu.unipampa.poo2.mediaBank.Business;

import edu.unipampa.poo2.mediaBank.Domain.Photo;
import edu.unipampa.poo2.mediaBank.Domain.FilterMedia;
import edu.unipampa.poo2.mediaBank.Infra.Repository.DBRepository;
import edu.unipampa.poo2.mediaBank.Business.utils.MediaHandler;

import java.io.IOException;
import java.util.List;

public class PhotoHandler {
    
    private List<Photo> photos;
    private DBRepository repository;
    private MediaHandler mediaHandler;
    private FilterMedia filter;
    private boolean sortType = false; //true is Title, false is Date

    public PhotoHandler(DBRepository repository, MediaHandler mediaHandler){
        this.repository = repository;
        this.mediaHandler = mediaHandler;
        filter = new FilterMedia();
    }

    public void sortByTitle(boolean type) {
        sortType = type;
    }

    public boolean deletePhoto(int id){
        try {
            repository.delete(id);
        } catch (ClassNotFoundException cnf) {
            return false;
        } catch (IOException ioe) {
            return false;
        }

        return query(filter.getTitle(), filter.getGenre());
    }
    public boolean query(String title, String genre){
        filter.setTitle(title);
        filter.setGenre(genre);

        try{
            if (sortType) {
                photos = mediaHandler.getPhotosByTitle(filter);
            } else {
                photos = mediaHandler.getPhotosByDate(filter);
            }
        } catch (ClassNotFoundException cnf) {
            return false;
        } catch (IOException ioe) {
            return false;
        }
        return true;
    }
    public List<Photo> getPhotos(){
        return photos;
    }
    public boolean editPhoto(Photo photo) {
        try {
            repository.update(photo);
        } catch (ClassNotFoundException cnf) {
            return false;
        } catch (IOException ioe) {
            return false;
        }

        return query(filter.getTitle(), filter.getGenre());
    }

    public boolean addPhoto(Photo photo){
        try{
            repository.insert(photo);
        } catch(IOException ioe) {
            return false;
        } catch(ClassNotFoundException cnf) {
            return false;
        }

        return query(filter.getTitle(), filter.getGenre());
    }
}
