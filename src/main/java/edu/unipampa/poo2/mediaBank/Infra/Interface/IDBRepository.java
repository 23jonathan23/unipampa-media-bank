package edu.unipampa.poo2.mediaBank.Infra.Interface;
import java.io.IOException;
import java.util.List;

import edu.unipampa.poo2.mediaBank.Domain.FilterMedia;
import edu.unipampa.poo2.mediaBank.Domain.Media;

public interface IDBRepository {
    void insert(Media media) throws IOException, ClassNotFoundException;
    List<Media> queryList(FilterMedia filter) throws IOException, ClassNotFoundException;
    void update(Media media) throws IOException, ClassNotFoundException;
    void delete(int id) throws IOException, ClassNotFoundException;
}
