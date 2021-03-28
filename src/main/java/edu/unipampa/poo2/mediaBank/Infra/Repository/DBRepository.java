package edu.unipampa.poo2.mediaBank.Infra.Repository;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.nio.file.*;

import edu.unipampa.poo2.mediaBank.Domain.Media;
import edu.unipampa.poo2.mediaBank.Infra.Interface.IDBRepository;

public class DBRepository implements IDBRepository {
    private final String CONFIG_FILE = "config.properties";
    private final int INDEX_DIFF = 1;
    private Path _pathDB;
    private FileOutputStream _fileOutStream;
    private ObjectOutputStream _objectOutStream;
    private FileInputStream _fileInputStream;
    private ObjectInputStream _objectInputStream;

    public DBRepository() throws IOException {
        var props = getConfig();
        _pathDB = Paths
                .get((System.getProperty("user.dir") + props.getProperty("db.path") + props.getProperty("db.name")));
    }

    public void insert(Media media) throws IOException, ClassNotFoundException {
        List<Media> listMedia = new ArrayList();

        if (Files.exists(_pathDB)) {
            openFileRead(_pathDB.toString());

            listMedia = (List<Media>) _objectInputStream.readObject();

            disposeFileRead();
        }

        openFileWrite(_pathDB.toString());

        var lastId = listMedia.size() == 0 
            ? listMedia.size() 
            : listMedia.get(listMedia.size() - INDEX_DIFF).getId();

        media.setId(lastId + INDEX_DIFF);
        media.setPathFile(_pathDB.toString());

        listMedia.add(media);

        _objectOutStream.writeObject(listMedia);

        disposeFileWrite();
    }

    public List<Media> queryList(List<Integer> ids) throws IOException, ClassNotFoundException {
        openFileRead(_pathDB.toString());

        var listMedia = (List<Media>) _objectInputStream.readObject();

        disposeFileRead();

        List<Media> result = new ArrayList();

        for (var media : listMedia) {
            for (var id : ids) {
                if (media.getId() == id) {
                    result.add(media);
                }
            }
        }

        return result;
    }

    public Media query(int id) throws ClassNotFoundException, IOException {
        var listMedia = queryAll();

        return listMedia.get(id - INDEX_DIFF);
    }

    public List<Media> queryAll() throws IOException, ClassNotFoundException {
        openFileRead(_pathDB.toString());

        var listMedia = (List<Media>) _objectInputStream.readObject();

        disposeFileRead();

        return listMedia;
    }

    public void update(Media media) throws IOException, ClassNotFoundException {
        var listMedia = queryAll();

        for (var mediaOld : listMedia) {
            if (mediaOld.getId() == media.getId()) {
                listMedia.set(listMedia.indexOf(mediaOld), media);
                break;
            }
        }

        openFileWrite(_pathDB.toString());

        _objectOutStream.writeObject(listMedia);

        disposeFileWrite();
    }

    public void delete(int id) throws IOException, ClassNotFoundException {
        var listMedia = queryAll();

        for (var media : listMedia) {
            if (media.getId() == id) {
                listMedia.remove(listMedia.indexOf(media));
                break;
            }
        }

        openFileWrite(_pathDB.toString());

        _objectOutStream.writeObject(listMedia);

        disposeFileWrite();
    }

    private void openFileWrite(String file) throws IOException {
        _fileOutStream = new FileOutputStream(file);
        _objectOutStream = new ObjectOutputStream(_fileOutStream);
    }

    private void disposeFileWrite() throws IOException {
        _fileOutStream.close();
        _objectOutStream.close();
    }

    private void openFileRead(String file) throws IOException {
        _fileInputStream = new FileInputStream(file);
        _objectInputStream = new ObjectInputStream(_fileInputStream);
    }

    private void disposeFileRead() throws IOException {
        _fileInputStream.close();
        _objectInputStream.close();
    }

    private Properties getConfig() throws IOException {
        Properties props = new Properties();

        props.load(new FileInputStream(CONFIG_FILE));

        return props;
    }
}
