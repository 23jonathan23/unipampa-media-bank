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
import edu.unipampa.poo2.mediaBank.Domain.MediaDomain;
import edu.unipampa.poo2.mediaBank.Domain.MediaPlayerDomain;
import edu.unipampa.poo2.mediaBank.Infra.Interface.IDBRepository;

@SuppressWarnings("unchecked")
public class DBRepository implements IDBRepository {
    private final String CONFIG_FILE = "config.properties";
    private final int INDEX_DIFF = 1;
    private Path _pathDB;
    private FileOutputStream _fileOutStream;
    private ObjectOutputStream _objectOutStream;
    private FileInputStream _fileInputStream;
    private ObjectInputStream _objectInputStream;
    private List<MediaDomain> _cache;
    private boolean _updateCache = true;

    public DBRepository() throws IOException {
        var props = getConfig();

        _pathDB = Paths.get(
            (System.getProperty("user.dir") + 
            props.getProperty("db.path") + 
            props.getProperty("db.name"))
        );
    }

    public void insert(MediaDomain media) throws IOException, ClassNotFoundException {
        List<MediaDomain> listMedia = new ArrayList<>();

        if (Files.exists(_pathDB)) {
            openFileRead(_pathDB.toString());

            listMedia = (List<MediaDomain>) _objectInputStream.readObject();

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
        
        _updateCache = true;
    }

    public List<MediaDomain> queryList(FilterMedia filter) throws IOException, ClassNotFoundException {
        if(filter == null || !filter.isValid()) { 
            return null;
        }

        openFileRead(_pathDB.toString());

        var listMedia = (List<MediaDomain>) _objectInputStream.readObject();

        disposeFileRead();

        var genre = filter.getGenre();
        var title = filter.getTitle();

        Predicate<MediaDomain> predicate = media -> {
            if (media instanceof MediaPlayerDomain) {
                var mediaPlayer = (MediaPlayerDomain) media;
            
                boolean genreWasFound = false;
                boolean titleWasFound = false;

                if (genre != null) {
                    genreWasFound = mediaPlayer.getGenre().toLowerCase().contains(genre.toLowerCase());
                }

                if (title != null) {
                    titleWasFound = mediaPlayer.getTitle().toLowerCase().contains(title.toLowerCase());
                }

                return genreWasFound || titleWasFound;
            } else {
                return media.getTitle().toLowerCase().contains(title.toLowerCase());
            }
        };

        
        var results = listMedia.stream().filter(predicate).collect(Collectors.toList());

        return results;
    }

    public void update(MediaDomain media) throws IOException, ClassNotFoundException {
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

    public List<MediaDomain> queryAll() throws IOException, ClassNotFoundException {
        if (_updateCache) {
            openFileRead(_pathDB.toString());
    
            var listMedia = (List<MediaDomain>) _objectInputStream.readObject();
    
            disposeFileRead();

            _updateCache = false;
    
            return listMedia;
        }

        return _cache;
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