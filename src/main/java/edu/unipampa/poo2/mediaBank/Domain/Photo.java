package edu.unipampa.poo2.mediaBank.Domain;

import java.util.List;
import java.util.Calendar;

public class Photo extends Media {
    private String _photographer;
    private List<String> _people;
    private String _place;
    private Calendar _date;
    private static final long serialVersionUID = -1271934389395100274L;
    
    public Photo(String title, String description, String photographer, 
        List<String> people, String place, Calendar date, String pathFile) {

        super(title, description, pathFile);

        _photographer = photographer;
        _people = people;
        _place = place;
        _date = date;
    }

    public String getPhotographer() {
        return _photographer;
    }

    public void setPhotographer(String photographer) {
        _photographer = photographer;
    }

    public List<String> getPeople() {
        return  _people;
    }

    public void setPeople(List<String> people) {
        _people = people;
    }

    public String getPlace() {
        return _place;
    }

    public void setPlace(String place) {
        _place = place;
    }

    public Calendar getDate() {
        return _date;
    }

    public void setDate(Calendar date) {
        _date = date;
    }
}
