package edu.unipampa.poo2.mediaBank.Business;

import edu.unipampa.poo2.mediaBank.Domain.FilterMedia;
import edu.unipampa.poo2.mediaBank.Domain.MediaDomain;
import edu.unipampa.poo2.mediaBank.Infra.Repository.DBRepository;

import java.io.IOException;
import java.util.List;

public abstract class MediaHandler {
    protected DBRepository repository = new DBRepository();
    protected static FilterMedia filter = new FilterMedia();

    public MediaHandler() throws IOException {
        this.repository = new DBRepository();
    }
    
    public void deleteMedia(int id) throws ClassNotFoundException, IOException {
        repository.delete(id);
    }

    public List<MediaDomain> getMedias() throws ClassNotFoundException, IOException {
        return repository.queryAll();
    }

    public void createMedia(MediaDomain media) throws ClassNotFoundException, IOException {
        repository.insert(media);
    }

    public void updateMedia(MediaDomain media) throws ClassNotFoundException, IOException {
        repository.update(media);
    }
}
