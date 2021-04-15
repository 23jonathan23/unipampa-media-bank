package edu.unipampa.poo2.mediaBank.Domain;

import java.util.List;
import java.time.LocalTime;

public class Song extends MediaPlayerDomain {
    private List<String> _authors;
    private List<String> _interpreters;
    private static final long serialVersionUID = 7884718496500956708L;

    public Song(String title, String description, String genre,
        String language, LocalTime duration, int year, String pathFile) {
        
        super(title, description, pathFile, genre, language, duration, year);
    }


    public void setAuthors(List<String> authors) {
        _authors = authors;
    }

    public List<String> getAuthors() {
        return _authors;

    }

    public void setInterpreters(List<String> interpreters) {
        _interpreters = interpreters;
    }

    public List<String> getInterpreters() {
        return _interpreters;
    }
}
