package edu.unipampa.poo2.mediaBank.Business;

import edu.unipampa.poo2.mediaBank.Domain.Song;
import edu.unipampa.poo2.mediaBank.Domain.SortType;
import edu.unipampa.poo2.mediaBank.Infra.Repository.DBRepository;
import edu.unipampa.poo2.mediaBank.Business.utils.FilterMediaListByType;

import java.io.IOException;
import java.util.List;

public class SongHandler extends MediaHandler {

    public SongHandler(DBRepository repository){
        super(repository);
    }

    public List<Song> getSong(String title, String genre, SortType sortType) throws ClassNotFoundException, IOException{
        filter.setTitle(title);
        filter.setGenre(genre);
        var songList = FilterMediaListByType.extractSongList(repository.queryList(filter));
        
        if(sortType == SortType.TITLE) {
           songList.sort((p1, p2) -> p1.getTitle().compareTo(p2.getTitle()) < 0 ? 1 : 0);
        } else {
            songList.sort((p1, p2) -> p1.getYear() > p2.getYear() ? 1 : 0);
        }

        return songList;
    }
}