package edu.unipampa.poo2.mediaBank.Business.utils;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

import edu.unipampa.poo2.mediaBank.Infra.Repository.DBRepository;
import edu.unipampa.poo2.mediaBank.Domain.*;

public class MediaHandler {
    private DBRepository repository;

    public MediaHandler(DBRepository repository) {
        this.repository = repository;
    }

    public List<Photo> getPhotosByDate(FilterMedia filter) throws IOException, ClassNotFoundException {
        List<Photo> filteredMedia = FilterMediaListByType.extractPhotoList(repository.queryList(filter));

        filteredMedia.sort((p1, p2) -> p1.getDate().getTimeInMillis() > p2.getDate().getTimeInMillis() ? 1 : 0);
        return filteredMedia;
    }

    public List<Movie> getMoviesByDate(FilterMedia filter) throws IOException, ClassNotFoundException {
        List<Movie> filteredMedia = FilterMediaListByType.extractMovieList(repository.queryList(filter));

        filteredMedia.sort((m1, m2) -> m1.getYear() > m2.getYear() ? 1 : 0);
        return filteredMedia;
    }

    public List<Song> getSongsByDate(FilterMedia filter) throws IOException, ClassNotFoundException {
        List<Song> filteredMedia = FilterMediaListByType.extractSongList(repository.queryList(filter));

        filteredMedia.sort((s1, s2) -> s1.getYear() > s2.getYear() ? 1 : 0);
        return filteredMedia;
    }

    public List<Photo> getPhotosByTitle(FilterMedia filter) throws IOException, ClassNotFoundException {
        List<Photo> filteredMedia = FilterMediaListByType.extractPhotoList(repository.queryList(filter));

        filteredMedia.sort((p1, p2) -> p1.getTitle().compareTo(p2.getTitle()) < 0 ? 1 : 0);
        return filteredMedia;
    }

    public List<Movie> getMovieByTitle(FilterMedia filter) throws IOException, ClassNotFoundException {
        List<Movie> filteredMedia = FilterMediaListByType.extractMovieList(repository.queryList(filter));

        filteredMedia.sort((m1, m2) -> m1.getTitle().compareTo(m2.getTitle()) < 0 ? 1 : 0);
        return filteredMedia;
    }

    public List<Song> getSongByTitle(FilterMedia filter) throws IOException, ClassNotFoundException {
        List<Song> filteredMedia = FilterMediaListByType.extractSongList(repository.queryList(filter));

        filteredMedia.sort((s1, s2) -> s1.getTitle().compareTo(s2.getTitle()) < 0 ? 1 : 0);
        return filteredMedia;
    }
}
