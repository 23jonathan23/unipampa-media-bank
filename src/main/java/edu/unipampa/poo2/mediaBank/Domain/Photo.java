package edu.unipampa.poo2.mediaBank.Domain;

import java.util.List;
import java.util.Calendar;

public class Photo extends Media {
    private String Photographer;
    private List<String> People;
    private String Place;
    private Calendar Date;
    
    public Photo(String title, String description,
         String photographer, List<String> people, String place, Calendar date) {

            super(title, description);

            Photographer = photographer;
            People = people;
            Place = place;
            Date = date;
    }

    public String getPhotographer() {
        return Photographer;
    }

    public void setPhotographer(String photographer) {
        Photographer = photographer;
    }

    public List<String> getPeople() {
        return  People;
    }

    public void setPeople(List<String> people) {
        People = people;
    }

    public String getPlace() {
        return Place;
    }

    public void setPlace(String place) {
        Place = place;
    }

    public Calendar getDate() {
        return Date;
    }

    public void setDate(Calendar date) {
        Date = date;
    }
}
