package edu.unipampa.poo2.mediaBank.Domain;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalTime;

public class Song extends MediaPlayer {
    private List<String> _authors = new ArrayList<>();
    private List<String> _interpreters = new ArrayList<>();
    private static final long serialVersionUID = 7884718496500956708L;

    public Song(String title, String description, String genre,
        String language, List<String> authors, List<String> interpreters, 
        LocalTime duration, int year, String pathFile) {
        
        super(title, description, pathFile, genre, language, duration, year);
        
        _authors.addAll(authors);
        _interpreters.addAll(interpreters);
    }

    public void setAuthor(String author) throws IllegalArgumentException {
        boolean authorFound = findAuthor(author);

        if (authorFound) {
            throw new IllegalArgumentException("Já é autor da música");
        }

        _authors.add(author);
    }

    public void removeAuthor(String author) throws IllegalArgumentException {
        boolean authorFound = findAuthor(author);

        if (!authorFound) {
            throw new IllegalArgumentException("Não é autor da música");
        }

        String actualAuthor = _authors.get(getIndexAuthor(author));

        _authors.remove(actualAuthor);
    }

    private int getIndexAuthor(String author) {
        for (String s : _authors) {
            if (s.equalsIgnoreCase(author)) {
                return _authors.indexOf(s);
            }
        }
        return 0;
    }

    public String authorsToString() {
        if (_authors.size() == 0) {
            return null;
        }

        String allAuthors = "";

        for (String s : _authors) {
            allAuthors += s + ";";
        }

        return allAuthors;
    }

    public void setInterpreter(String interpreter) throws IllegalArgumentException {
        boolean interpreterFound = findInterpreter(interpreter);

        if (interpreterFound) {
            throw new IllegalArgumentException("Já é intérprete da música");
        }

        _interpreters.add(interpreter);
    }

    public void removeInterpreter(String interpreter) throws IllegalArgumentException {
        boolean interpreterFound = findInterpreter(interpreter);

        if (!interpreterFound){
            throw new IllegalArgumentException("Não é intérprete da música");
        }

        String actualInterpreter = _interpreters.get(getIndexInterpreter(interpreter));
        _interpreters.remove(actualInterpreter);
    }

    public String interpretersToString() {
        if (_interpreters.size() == 0){
            return null;
        }

        String allInterpreters = "";

        for (String s : _interpreters) {
            allInterpreters += s + ";";
        }

        return allInterpreters;
    }

    private int getIndexInterpreter(String interpreter) {
        for (String s : _interpreters) {
            if (s.equalsIgnoreCase(interpreter)) {
                return _interpreters.indexOf(s);
            }
        }
        return 0;
    }

    private boolean findInterpreter(String interpreter) {
        for (String s : _interpreters){
            if (s.equalsIgnoreCase(interpreter)) {
                return true;
            }
        }
        return false;
    }

    private boolean findAuthor(String author) {
        for (String s : _authors){
            if (s.equalsIgnoreCase(author)) {
                return true;
            }
        }
        return false;
    } 
}
