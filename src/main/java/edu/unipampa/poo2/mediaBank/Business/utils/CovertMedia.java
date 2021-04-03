package edu.unipampa.poo2.mediaBank.Business.utils;

import java.util.ArrayList;
import java.util.List;

import edu.unipampa.poo2.mediaBank.Domain.Media;
import edu.unipampa.poo2.mediaBank.Domain.Movie;
import edu.unipampa.poo2.mediaBank.Domain.Photo;
import edu.unipampa.poo2.mediaBank.Domain.Song;

public abstract class CovertMedia {
    public static List<Photo> convertToPhoto(List<Media> listMedia) {
        var results = new ArrayList<Photo>();

        for(Media media : listMedia) {
            if(media instanceof Photo) {
                var photo = (Photo) media;
                results.add(photo);
            }
        }

        return results;
    }

    public static List<Movie> convertToMovie(List<Media> listMedia) {
        var results = new ArrayList<Movie>();

        for(Media media : listMedia) {
            if(media instanceof Movie) {
                var movie = (Movie) media;
                results.add(movie);
            }
        }

        return results;
    }

    public static List<Song> convertToSong(List<Media> listMedia) {
        var results = new ArrayList<Song>();

        for(Media media : listMedia) {
            if(media instanceof Song) {
                var song = (Song) media;
                results.add(song);
            }
        }

        return results;
    }
}
