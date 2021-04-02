package edu.unipampa.poo2.mediaBank.Infra.Repository;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.nio.file.*;

import edu.unipampa.poo2.mediaBank.Domain.FilterMedia;
import edu.unipampa.poo2.mediaBank.Domain.Media;
import edu.unipampa.poo2.mediaBank.Domain.MediaPlayer;
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

        listMedia.add(media);

        _objectOutStream.writeObject(listMedia);

        disposeFileWrite();
    }

    public List<Media> queryList(FilterMedia filter) throws IOException, ClassNotFoundException {
        if(filter == null || !filter.isValid()) { 
            return null;
        }

        openFileRead(_pathDB.toString());

        var listMedia = (List<Media>) _objectInputStream.readObject();

        disposeFileRead();

        var genre = filter.getGenre() != null ? filter.getGenre() : "";
        var title = filter.getTitle() != null ? filter.getTitle() : "";

        
        Predicate<Media> predicate = media -> {
            if(media instanceof MediaPlayer) {
                var mediaPlayer = (MediaPlayer) media;
                
                if(!title.isEmpty() && !genre.isEmpty()){
                    return mediaPlayer.getGenre().equals(genre) && mediaPlayer.getTitle().equals(title);
                } else {
                    return mediaPlayer.getGenre().equals(genre) || mediaPlayer.getTitle().equals(title);
                }
            } else {
                return media.getTitle().equals(title);
            }
        };

        
        var results = listMedia.stream().filter(predicate).collect(Collectors.toList());

        return results;
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

    private List<Media> queryAll() throws IOException, ClassNotFoundException {
        openFileRead(_pathDB.toString());

        var listMedia = (List<Media>) _objectInputStream.readObject();

        disposeFileRead();

        return listMedia;
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
