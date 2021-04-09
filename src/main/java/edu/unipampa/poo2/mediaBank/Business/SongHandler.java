package edu.unipampa.poo2.mediaBank.Business;

import edu.unipampa.poo2.mediaBank.Domain.Song;
import edu.unipampa.poo2.mediaBank.Infra.Repository.DBRepository;
import edu.unipampa.poo2.mediaBank.Business.utils.MediaSorter;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

public class SongHandler extends MediaHandler {
    
    List<Song> songs;

    public SongHandler(DBRepository repository, MediaSorter mediaSorter) {
        super(repository, mediaSorter);
    }

    public boolean deleteMedia(int id) {
        Song song = getMedia(id);

        if (song == null) {
            return false;
        }

        File file = new File(song.getPathFile());
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

    public boolean addSong(File file, String title, String description, String genre, String language,
        List<String> authors, List<String> interpreters, LocalTime duration, int year){

        Song song = new Song(title, description, genre, language, authors, interpreters, duration, year, file.toPath().toString());

        try {
            song.setId(repository.getNewId());
        } catch (ClassNotFoundException cnf) {
            return false;
        } catch (IOException ioe) {
            return false;
        }

        try{
            repository.insert(song);
        } catch(IOException ioe) {
            return false;
        } catch(ClassNotFoundException cnf) {
            return false;
        }

        return query(filter.getTitle(), filter.getGenre());
    }

    private Song getMedia(int id) {
        for (Song p : songs) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
}
