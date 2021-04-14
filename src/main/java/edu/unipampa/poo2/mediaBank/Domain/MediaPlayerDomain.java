package edu.unipampa.poo2.mediaBank.Domain;

import java.time.LocalTime;

public class MediaPlayerDomain extends MediaDomain {
    private int _year;
    private String _genre;
    private String _language;
    private LocalTime _duration;
    private static final long serialVersionUID = -6355229307470263995L;

    protected MediaPlayerDomain(String title, String description, String pathFile, 
        String genre, String language, LocalTime duration, int year) {
        
        super(title, description, pathFile);
        
        _genre = genre;
        _language = language;
        _duration = duration;
        _year = year;
    }

    public void setGenre(String genre) {
        _genre = genre;
    }
    
    public String getGenre() {
        return _genre;
    }

    public void setLanguage(String language) {
        _language = language;
    }

    public String getLanguage() {
        return _language;
    }

    public void setDuration(LocalTime duration) {
        _duration = duration;
    }

    public LocalTime getDuration() {
        return _duration;
    }
    
    public void setYear(int year) {
        _year = year;
    }

    public int getYear() {
        return _year;
    }
}