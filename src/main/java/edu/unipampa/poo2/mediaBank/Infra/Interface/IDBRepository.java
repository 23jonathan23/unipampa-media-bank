package edu.unipampa.poo2.mediaBank.Infra.Interface;
import java.io.IOException;
import java.util.List;

import edu.unipampa.poo2.mediaBank.Domain.FilterMedia;
import edu.unipampa.poo2.mediaBank.Domain.MediaDomain;

public interface IDBRepository {
    void insert(MediaDomain media) throws IOException, ClassNotFoundException;
    List<MediaDomain> queryAll() throws IOException, ClassNotFoundException;
    List<MediaDomain> queryList(FilterMedia filter) throws IOException, ClassNotFoundException;
    void update(MediaDomain media) throws IOException, ClassNotFoundException;
    void delete(int id) throws IOException, ClassNotFoundException;
}
