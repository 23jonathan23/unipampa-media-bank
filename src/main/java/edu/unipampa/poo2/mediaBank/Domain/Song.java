package edu.unipampa.poo2.mediaBank.Domain;

import java.util.List;

public class Song extends Media{
    String genre;
    String language;
    List<String> authors;
    List<String> interpreters;
    int[] duration = new int[3];
    int ano;

    public Song(String title, String description, String genre, 
                String language, List<String> authors, List<String> interpreters, int[] duration, int ano){
        super(title, description);
        
        this.genre = genre;
        this.language = language;
        this.authors.addAll(authors);
        this.interpreters.addAll(interpreters);
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
    
}