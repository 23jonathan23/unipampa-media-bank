package edu.unipampa.poo2.mediaBank.Domain;

import java.util.List;
import java.util.ArrayList;

public class Movie extends Media{
    String genre;
    String language;
    String director;
    List <String> actors = new ArrayList<>();
    int[] duration = new int[3];
    int ano;

    public Movie(String title, String description, String genre, 
                String language, String director, List<String> actors, int[] dutation, int ano){
        super(title, description);

        this.genre = genre;
        this.language = language;
        this.director = director;
        this.actors.addAll(actors);
        setDuration(duration);
        this.ano = ano;
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
    public void setDuration(int[] duration){
        for (int i = 0; i < duration.length; i++){
            this.duration[i] = duration[i];
        }
    }
    public int[] getDuration(){
        return duration;
    }
    public void setHour(int hour){
        this.duration[0] = hour;
    }
    public int getHour(){
        return this.duration[0];
    }
    public void setMinute(int minute){
        this.duration[1] = minute;
    }
    public int getMinute(){
        return this.duration[1];
    }
    public void setSecond(int second){
        this.duration[2] = second;
    }
    public int getSecond(){
        return this.duration[2];
    }
    public void setAno(int ano){
        this.ano = ano;
    }
    public int getAno(){
        return ano;
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