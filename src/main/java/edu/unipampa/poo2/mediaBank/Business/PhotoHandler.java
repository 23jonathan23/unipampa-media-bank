package edu.unipampa.poo2.mediaBank.Business;

import edu.unipampa.poo2.mediaBank.Domain.Photo;
import edu.unipampa.poo2.mediaBank.Infra.Repository.DBRepository;
import edu.unipampa.poo2.mediaBank.Business.utils.MediaSorter;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

public class PhotoHandler extends MediaHandler {
    
    private List<Photo> photos;

    public PhotoHandler(DBRepository repository, MediaSorter mediaSorter){
        super(repository, mediaSorter);
    }

    public boolean deleteMedia(int id) {
        Photo photo = getMedia(id);

        if (photo == null) {
            return false;
        }

        File file = new File(photo.getPathFile());
        file.delete();

        if (!removeFromSystem(id)) {
            return false;
        }

        return query(filter.getTitle(), filter.getGenre());
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

    public boolean addPhoto(File file, String title, String description, String photographer, //it has no validation for the photo name
        List<String> people, String place, Calendar date){

        Photo photo = new Photo(title, description, photographer, people, place, date, file.toPath().toString());
        try {
            photo.setId(repository.getNewId());
        } catch (ClassNotFoundException cnf) {
            return false;
        } catch (IOException ioe) {
            return false;
        }

        try{
            repository.insert(photo);
        } catch(IOException ioe) {
            return false;
        } catch(ClassNotFoundException cnf) {
            return false;
        }

        return query(filter.getTitle(), filter.getGenre());
    }

    private Photo getMedia(int id) {
        for (Photo p : photos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
}
