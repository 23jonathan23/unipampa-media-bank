package edu.unipampa.poo2.mediaBank.Domain;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalTime;

public class Movie extends Media{
    String genre;
    String language;
    String director;
    List <String> actors = new ArrayList<>();
    LocalTime duration;
    int year;

    public Movie(String title, String description, String genre, 
                String language, String director, List<String> actors, LocalTime duration, int year){
        super(title, description);

        this.genre = genre;
        this.language = language;
        this.director = director;
        this.actors.addAll(actors);
        this.duration = duration;
        this.year = year;
    }

    public void setGenre(String genre){
        this.genre = genre;
    }
    public String getGenre(){
        return genre;
    }
    public void setLanguage(String language){
        this.language = language;
    }
    public String getLanguage(){
        return language;
    }
    public void setDirector(String director){
        this.director = director;
    }
    public String getDirector(){
        return director;
    }
    public void setActor(String actor) throws IllegalArgumentException{
        boolean found = findActor(actor);
        if (found){
            throw new IllegalArgumentException("O ator ja está no filme");
        }
        actors.add(actor);
    }
    public void removeActor(String actor) throws IllegalArgumentException{
        boolean found = findActor(actor);
        if (!found){
            throw new IllegalArgumentException("O ator não está no filme");
        }
        String actualActor = actors.get(getIndex(actor));
        actors.remove(actualActor);
    }
    public String actorsToString(){
        if (actors.size() == 0){
            return null;
        }
        String allActors = "";
        for (String s : actors){
            allActors += s + ";";
        }
        return allActors;
    }
    public void setDuration(LocalTime duration){
        this.duration = duration;
    }
    public LocalTime getDuration(){
        return duration;
    }
    public void setHour(int hour){
        this.duration = LocalTime.of(hour, duration.getMinute(), duration.getSecond());
    }
    public int getHour(){
        return duration.getHour();
    }
    public void setMinute(int minute){
        this.duration = LocalTime.of(duration.getHour(), minute, duration.getSecond());
    }
    public int getMinute(){
        return duration.getMinute();
    }
    public void setSecond(int second){
        this.duration = LocalTime.of(duration.getHour(), duration.getMinute(), second);
    }
    public int getSecond(){
        return duration.getSecond();
    }
    public void setYear(int year){
        this.year = year;
    }
    public int getYear(){
        return year;
    }

    private int getIndex(String actor){
        for (String s : actors){
            if (s.equalsIgnoreCase(actor)){
                return actors.indexOf(s);
            }
        }
        return 0;
    }

    private boolean findActor(String actor){
        for (String s : actors){
            if (s.equalsIgnoreCase(actor)){
                return true;
            }
        }
        return false;
    }
}