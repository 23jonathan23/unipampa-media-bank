package edu.unipampa.poo2.mediaBank.Domain;

public class FilterMedia {
    private String _title;
    private String _genre;

    public String getTitle() {
        return _title;
    }

    public void setTitle(String title) {
        _title = title;
    }

    public String getGenre() {
        return _genre;
    }

    public void setGenre(String genre) {
        _genre = genre;
    }

    public boolean isValid() {
        return (_title != null && !_title.isEmpty()) || (_genre != null && !_genre.isEmpty());
    }
}
