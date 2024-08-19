package entity.song;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Song object
 */
public class Song implements ISong {
    private String name;
    private String artist;
    private String album;
    private LocalDate releaseDate;
    private ArrayList<String> tags;
    private String URL;
    private int id;

    /**
     * create instance of song
     * @param name of song
     * @param artist of song
     * @param album of song
     * @param releaseDate of song
     * @param tags apllied to song
     * @param URL of song through API
     * @param id of the song
     */
    public Song(String name, String artist, String album, LocalDate releaseDate, ArrayList<String> tags, String URL, int id) {
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.releaseDate = releaseDate;
        this.tags = tags;
        this.URL = URL;
        this.id = id;
    }

    /**
     * access name
     * @return name of song
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * access artist
     * @return name of artist
     */
    @Override
    public String getArtist() {
        return artist;
    }

    /**
     * access album
     * @return name of album
     */
    @Override
    public String getAlbum() {
        return album;
    }

    /**
     * access release date
     * @return release date
     */
    @Override
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    /**
     * access tags of the song
     * @return list of the tags
     */
    @Override
    public ArrayList<String> getTags() {
        return tags;
    }

    /**
     * access song URL
     * @return URL link
     */
    @Override
    public String getURL() {
        return URL;
    }

    /**
     * access id
     * @return int id
     */
    public int getId() {
        return id;
    }
}
