package edu.unipampa.poo2.mediaBank.Business.utils;

import java.util.ArrayList;
import java.util.List;

import edu.unipampa.poo2.mediaBank.Domain.Media;
import edu.unipampa.poo2.mediaBank.Domain.Movie;
import edu.unipampa.poo2.mediaBank.Domain.Photo;
import edu.unipampa.poo2.mediaBank.Domain.Song;

public abstract class FilterMediaListByType {
    public static List<Photo> extractPhotoList(List<Media> mediaList) {
        var photos = new ArrayList<Photo>();

        for (Media media : mediaList) {
            if (media instanceof Photo) {
                var photo = (Photo) media;
                photos.add(photo);
            }
        }

        return photos;
    }

    public static List<Movie> extractMovieList(List<Media> mediaList) {
        var movies = new ArrayList<Movie>();

        for (Media media : mediaList) {
            if (media instanceof Movie) {
                var movie = (Movie) media;
                movies.add(movie);
            }
        }

        return movies;
    }

    public static List<Song> extractSongList(List<Media> mediaList) {
        var songs = new ArrayList<Song>();

        for (Media media : mediaList) {
            if (media instanceof Song) {
                var song = (Song) media;
                songs.add(song);
            }
        }

        return songs;
    }
}
