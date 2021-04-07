package edu.unipampa.poo2.mediaBank.Domain;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalTime;

public class Movie extends MediaPlayer {
    private String _director;
    private List <String> _actors = new ArrayList<>();
    private static final long serialVersionUID = 7582232704491799370L;

    public Movie(String title, String description, String genre, String language, 
        String director, List<String> actors, LocalTime duration, int year, String pathFile) {
        
        super(title, description, pathFile, genre, language, duration, year);

        _director = director;
        _actors.addAll(actors);
    }

    public void setDirector(String director) {
        _director = director;
    }
    
    public String getDirector() {
        return _director;
    }
    
    public void setActor(String actor) throws IllegalArgumentException {
        boolean found = findActor(actor);

        if (found) {
            throw new IllegalArgumentException("O ator ja está no filme");
        }

        _actors.add(actor);
    }
    
    public void removeActor(String actor) throws IllegalArgumentException {
        boolean found = findActor(actor);

        if (!found) {
            throw new IllegalArgumentException("O ator não está no filme");
        }

        String actualActor = _actors.get(getIndex(actor));

        _actors.remove(actualActor);
    }
    
    public String actorsToString() {
        if (_actors.size() == 0) {
            return null;
        }

        String allActors = "";

        for (String s : _actors) {
            allActors += s + ";";
        }

        return allActors;
    }

    private int getIndex(String actor) {
        for (String s : _actors){
            if (s.equalsIgnoreCase(actor)) {
                return _actors.indexOf(s);
            }
        }
        return 0;
    }

    private boolean findActor(String actor) {
        for (String s : _actors){
            if (s.equalsIgnoreCase(actor)) {
                return true;
            }
        }
        return false;
    }
}
