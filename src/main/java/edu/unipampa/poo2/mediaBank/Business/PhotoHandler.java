package edu.unipampa.poo2.mediaBank.Business;

import edu.unipampa.poo2.mediaBank.Domain.Photo;
import edu.unipampa.poo2.mediaBank.Domain.FilterMedia;
import edu.unipampa.poo2.mediaBank.Infra.Repository.DBRepository;
import edu.unipampa.poo2.mediaBank.Business.utils.MediaSorter;

import java.io.IOException;
import java.util.List;

public class PhotoHandler extends MediaHandler {
    
    private List<Photo> photos;

    public PhotoHandler(DBRepository repository, MediaSorter mediaSorter){
        this.repository = repository;
        this.mediaSorter = mediaSorter;
        filter = new FilterMedia();
    }

    public boolean query(String title, String genre){
        filter.setTitle(title);
        filter.setGenre(genre);

        try{
            if (sortType) {
                photos = mediaSorter.getPhotosByTitle(filter);
            } else {
                photos = mediaSorter.getPhotosByDate(filter);
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
    public boolean edit(Photo photo) {
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
