package edu.unipampa.poo2.mediaBank.Business;

import edu.unipampa.poo2.mediaBank.Domain.FilterMedia;
import edu.unipampa.poo2.mediaBank.Infra.Repository.DBRepository;
import edu.unipampa.poo2.mediaBank.Business.utils.MediaSorter;

public abstract class MediaHandler {
    protected DBRepository repository;
    protected MediaSorter mediaSorter;
    protected FilterMedia filter;
    protected boolean sortType = false;

    public abstract void sortByTitle(boolean type);
    public abstract boolean deleteMedia(int id);
    public abstract boolean query(String title, String genre);
}
