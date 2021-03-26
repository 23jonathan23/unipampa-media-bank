package edu.unipampa.poo2.mediaBank.Infra.Interface;
import java.io.IOException;
import java.util.List;

import edu.unipampa.poo2.mediaBank.Domain.Media;

public interface IDBRepository {
    void insert(Media media) throws IOException, ClassNotFoundException;
    List<Media> queryList(List<Integer> ids) throws IOException, ClassNotFoundException;
    Media query(int id) throws ClassNotFoundException, IOException;
    List<Media> queryAll() throws IOException, ClassNotFoundException;
    void update(Media media) throws IOException, ClassNotFoundException;
    void delete(int id) throws IOException, ClassNotFoundException;
}
