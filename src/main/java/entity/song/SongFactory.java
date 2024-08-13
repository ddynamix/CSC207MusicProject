package entity.song;

import data_access.SongDataAccessInterface;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * create songs
 */
public class SongFactory {
    SongDataAccessInterface songDataAccessInterface;

    public SongFactory(SongDataAccessInterface songDataAccessInterface) {
        this.songDataAccessInterface = songDataAccessInterface;
    }


    public Song createSong(String name, String artist, String album, LocalDate releaseDate, ArrayList<String> tags, String URL) {
        int id = songDataAccessInterface.getUniqueId();
        return new Song(name, artist, album, releaseDate, tags, URL, id);
    }
}
