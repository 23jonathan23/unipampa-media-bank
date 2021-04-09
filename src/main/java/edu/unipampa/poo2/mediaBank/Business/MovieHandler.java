package edu.unipampa.poo2.mediaBank.Business;

import edu.unipampa.poo2.mediaBank.Domain.Movie;
import edu.unipampa.poo2.mediaBank.Domain.FilterMedia;
import edu.unipampa.poo2.mediaBank.Infra.Repository.DBRepository;
import edu.unipampa.poo2.mediaBank.Business.utils.MediaSorter;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MovieHandler extends MediaHandler{

    List<Movie> movies;

    public MovieHandler(DBRepository repository, MediaSorter mediaSorter){
        super(repository, mediaSorter);
    }

    public boolean deleteMedia(int id) {
        Movie movie = getMedia(id);

        if (movie == null) {
            return false;
        }

        File file = new File(movie.getPathFile());
        file.delete();

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
                movies = mediaSorter.getMoviesByTitle(filter);
            } else {
                movies = mediaSorter.getMoviesByDate(filter);
            }
        } catch (ClassNotFoundException cnf) {
            return false;
        } catch (IOException ioe) {
            return false;
        }
        return true;
    }

    public List<Movie> getMovies(){
        return movies;
    }

    public boolean edit(Movie movie) {
        try {
            repository.update(movie);
        } catch (ClassNotFoundException cnf) {
            return false;
        } catch (IOException ioe) {
            return false;
        }

        return query(filter.getTitle(), filter.getGenre());
    }

    public boolean addMovie(Movie movie){
        try{
            repository.insert(movie);
        } catch(IOException ioe) {
            return false;
        } catch(ClassNotFoundException cnf) {
            return false;
        }

        return query(filter.getTitle(), filter.getGenre());
    }

    private Movie getMedia(int id) {
        for (Movie p : movies) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
}
