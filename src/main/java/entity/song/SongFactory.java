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

    /**
     * create instance of song
     * @param songDataAccessInterface song DAO interface
     */
    public SongFactory(SongDataAccessInterface songDataAccessInterface) {
        this.songDataAccessInterface = songDataAccessInterface;
    }


    /**
     * create a song object
     * @param name of song
     * @param artist of song
     * @param album of song
     * @param releaseDate of song
     * @param tags applied to song
     * @param URL of song link
     * @return song object
     */
    public Song createSong(String name, String artist, String album, LocalDate releaseDate, ArrayList<String> tags, String URL) {
        int id = songDataAccessInterface.getUniqueId();
        return new Song(name, artist, album, releaseDate, tags, URL, id);
    }
}
