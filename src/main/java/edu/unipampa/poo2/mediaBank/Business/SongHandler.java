package edu.unipampa.poo2.mediaBank.Business;

import edu.unipampa.poo2.mediaBank.Domain.Song;
import edu.unipampa.poo2.mediaBank.Domain.FilterMedia;
import edu.unipampa.poo2.mediaBank.Infra.Repository.DBRepository;
import edu.unipampa.poo2.mediaBank.Business.utils.MediaSorter;

import java.io.IOException;
import java.util.List;

public class SongHandler extends MediaHandler {
    
    List<Song> songs;

    public SongHandler(DBRepository repository, MediaSorter mediaSorter) {
        this.repository = repository;
        this.mediaSorter = mediaSorter;
        filter = new FilterMedia();
    }

    public boolean query(String title, String genre){
        filter.setTitle(title);
        filter.setGenre(genre);

        try{
            if (sortType) {
                songs = mediaSorter.getSongsByTitle(filter);
            } else {
                songs = mediaSorter.getSongsByDate(filter);
            }
        } catch (ClassNotFoundException cnf) {
            return false;
        } catch (IOException ioe) {
            return false;
        }
        return true;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public boolean edit(Song song) {
        try {
            repository.update(song);
        } catch (ClassNotFoundException cnf) {
            return false;
        } catch (IOException ioe) {
            return false;
        }

        return query(filter.getTitle(), filter.getGenre());
    }

    public boolean addSong(Song song){
        try{
            repository.insert(song);
        } catch(IOException ioe) {
            return false;
        } catch(ClassNotFoundException cnf) {
            return false;
        }

        return query(filter.getTitle(), filter.getGenre());
    }
}
