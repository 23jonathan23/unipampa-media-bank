package edu.unipampa.poo2.mediaBank.Business;

import edu.unipampa.poo2.mediaBank.Domain.Movie;
import edu.unipampa.poo2.mediaBank.Domain.SortType;
import edu.unipampa.poo2.mediaBank.Infra.Repository.DBRepository;
import edu.unipampa.poo2.mediaBank.Business.utils.FilterMediaListByType;

import java.io.IOException;
import java.util.List;

public class MovieHandler extends MediaHandler {

    public MovieHandler(DBRepository repository){
        super(repository);
    }

    public List<Movie> getMovie(String title, String genre, SortType sortType) throws ClassNotFoundException, IOException{
        filter.setTitle(title);
        filter.setGenre(genre);
        var movieList = FilterMediaListByType.extractMovieList(repository.queryList(filter));
        
        if(sortType == SortType.TITLE) {
           movieList.sort((p1, p2) -> p1.getTitle().compareTo(p2.getTitle()) < 0 ? 1 : 0);
        } else {
            movieList.sort((p1, p2) -> p1.getYear() > p2.getYear() ? 1 : 0);
        }

        return movieList;
    }
}
