package edu.unipampa.poo2.mediaBank.Business;

import edu.unipampa.poo2.mediaBank.Domain.Song;
import edu.unipampa.poo2.mediaBank.Domain.SortType;
import edu.unipampa.poo2.mediaBank.Business.utils.FilterMediaListByType;

import java.io.IOException;
import java.util.List;

public class SongHandler extends MediaHandler {
    public SongHandler() throws IOException {}

    public List<Song> getSongs() throws ClassNotFoundException, IOException{
        var mediaList = repository.queryAll();

        var songList = FilterMediaListByType.extractSongList(mediaList);

        return songList;
    }

    public List<Song> getSongsByFilter(String title, String genre, SortType sortType) throws ClassNotFoundException, IOException{
        filter.setTitle(title);
        filter.setGenre(genre);
        var songList = FilterMediaListByType.extractSongList(repository.queryList(filter));
        
        if (sortType == SortType.TITLE) {
            songList.sort((p1, p2) -> p1.getTitle().compareTo(p2.getTitle()) < 0 ? 1 : 0);
        } else if (sortType == SortType.DATE) {
            songList.sort((p1, p2) -> p1.getYear() > p2.getYear() ? 1 : 0);
        }

        return songList;
    }
}