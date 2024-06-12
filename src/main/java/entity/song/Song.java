package entity.song;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Song implements ISong {
    private String name;
    private String artist;
    private String album;
    private LocalDateTime releaseDate;
    private ArrayList<String> tags;
    private String URL;

    public Song(String name, String artist, String album, LocalDateTime releaseDate, ArrayList<String> tags, String URL) {
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.releaseDate = releaseDate;
        this.tags = tags;
        this.URL = URL;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getArtist() {
        return artist;
    }

    @Override
    public String getAlbum() {
        return album;
    }

    @Override
    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    @Override
    public ArrayList<String> getTags() {
        return tags;
    }

    @Override
    public String getURL() {
        return URL;
    }
}
