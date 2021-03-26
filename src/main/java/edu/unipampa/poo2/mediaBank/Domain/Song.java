package edu.unipampa.poo2.mediaBank.Domain;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalTime;

public class Song extends Media{
    String genre;
    String language;
    List<String> authors = new ArrayList<>();
    List<String> interpreters = new ArrayList<>();
    LocalTime duration;
    int year;

    public Song(String title, String description, String genre, 
                String language, List<String> authors, List<String> interpreters, LocalTime duration, int year){
        super(title, description);
        
        this.genre = genre;
        this.language = language;
        this.authors.addAll(authors);
        this.interpreters.addAll(interpreters);
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
    public void setAuthor(String author) throws IllegalArgumentException{
        boolean found = findAuthor(author);
        if (found){
            throw new IllegalArgumentException("Já é autor");
        }
        authors.add(author);
    }

    public void removeAuthor(String author) throws IllegalArgumentException{
        boolean found = findAuthor(author);
        if (!found){
            throw new IllegalArgumentException("Não é autor da Música");
        }
        String actualAuthor = authors.get(getIndexAuthor(author));
        authors.remove(actualAuthor);
    }

    private int getIndexAuthor(String author){
        for (String s : authors){
            if (s.equalsIgnoreCase(author)){
                return authors.indexOf(s);
            }
        }
        return 0;
    }
    public String authorsToString(){
        if (authors.size() == 0){
            return null;
        }
        String allAuthors = "";
        for (String s : authors){
            allAuthors += s + ";";
        }
        return allAuthors;
    }
    public void setInterpreter(String interpreter) throws IllegalArgumentException{
        boolean found = findInterpreter(interpreter);
        if (found){
            throw new IllegalArgumentException("Já é intérpreter");
        }
        interpreters.add(interpreter);
    }
    public void removeInterpreter(String interpreter) throws IllegalArgumentException{
        boolean found = findInterpreter(interpreter);
        if (!found){
            throw new IllegalArgumentException("Não é interpreter da música");
        }
        String actualInterpreter = interpreters.get(getIndexInterpreter(interpreter));
        interpreters.remove(actualInterpreter);
    }

    public String interpretersToString(){
        if (interpreters.size() == 0){
            return null;
        }
        String allInterpreters = "";
        for (String s : interpreters){
            allInterpreters += s + ";";
        }
        return allInterpreters;
    }

    private int getIndexInterpreter(String interpreter){
        for (String s : interpreters){
            if (s.equalsIgnoreCase(interpreter)){
                return interpreters.indexOf(s);
            }
        }
        return 0;
    }

    private boolean findInterpreter(String interpreter){
        for (String s : interpreters){
            if (s.equalsIgnoreCase(interpreter)){
                return true;
            }
        }
        return false;
    }

    private boolean findAuthor(String author){
        for (String s : authors){
            if (s.equalsIgnoreCase(author)){
                return true;
            }
        }
        return false;
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
    
}