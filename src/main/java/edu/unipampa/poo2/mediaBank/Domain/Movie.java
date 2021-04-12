package edu.unipampa.poo2.mediaBank.Domain;

import java.util.List;
import java.time.LocalTime;

public class Movie extends MediaPlayer {
    private String _director;
    private List <String> _actors;
    private static final long serialVersionUID = 7582232704491799370L;

    public Movie(String title, String description, String genre, String language, 
        String director, List<String> actors, LocalTime duration, int year, String pathFile) {
        
        super(title, description, pathFile, genre, language, duration, year);

        _director = director;
        _actors = actors;
    }

    public void setDirector(String director) {
        _director = director;
    }
    
    public String getDirector() {
        return _director;
    }
    
    public void setActors(List<String> actors) {
        _actors = actors;
    }
    
    public List<String> getActors() {
        return _actors;
    }
}
