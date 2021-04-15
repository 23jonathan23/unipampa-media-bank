package edu.unipampa.poo2.mediaBank.Business;

import edu.unipampa.poo2.mediaBank.Domain.Photo;
import edu.unipampa.poo2.mediaBank.Domain.SortType;
import edu.unipampa.poo2.mediaBank.Business.utils.FilterMediaListByType;

import java.io.IOException;
import java.util.List;

public class PhotoHandler extends MediaHandler {
    public PhotoHandler() throws IOException {}

    public List<Photo> getPhotos() throws ClassNotFoundException, IOException{
        var mediaList = repository.queryAll();

        var photoList = FilterMediaListByType.extractPhotoList(mediaList);

        return photoList;
    }

    public List<Photo> getPhotosByFilter(String title, SortType sortType) throws ClassNotFoundException, IOException{
        filter.setTitle(title);

        var photoList = FilterMediaListByType.extractPhotoList(repository.queryList(filter));
        
        if (sortType == SortType.TITLE) {
            photoList.sort((p1, p2) -> p1.getTitle().compareTo(p2.getTitle()) < 0 ? 1 : 0);
        } else if (sortType == SortType.DATE) {
            photoList.sort((p1, p2) -> p1.getDate().getTimeInMillis() > p2.getDate().getTimeInMillis() ? 1 : 0);
        }

        return photoList;
    }
}