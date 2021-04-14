package edu.unipampa.poo2.mediaBank.Business.utils;

import java.util.ArrayList;
import java.util.List;

import edu.unipampa.poo2.mediaBank.Domain.MediaDomain;
import edu.unipampa.poo2.mediaBank.Domain.Movie;
import edu.unipampa.poo2.mediaBank.Domain.Photo;
import edu.unipampa.poo2.mediaBank.Domain.Song;

public abstract class FilterMediaListByType {
    public static List<Photo> extractPhotoList(List<MediaDomain> mediaList) {
        var photos = new ArrayList<Photo>();

        for (MediaDomain media : mediaList) {
            if (media instanceof Photo) {
                var photo = (Photo) media;
                photos.add(photo);
            }
        }

        return photos;
    }

    public static List<Movie> extractMovieList(List<MediaDomain> mediaList) {
        var movies = new ArrayList<Movie>();

        for (MediaDomain media : mediaList) {
            if (media instanceof Movie) {
                var movie = (Movie) media;
                movies.add(movie);
            }
        }

        return movies;
    }

    public static List<Song> extractSongList(List<MediaDomain> mediaList) {
        var songs = new ArrayList<Song>();

        for (MediaDomain media : mediaList) {
            if (media instanceof Song) {
                var song = (Song) media;
                songs.add(song);
            }
        }

        return songs;
    }
}
