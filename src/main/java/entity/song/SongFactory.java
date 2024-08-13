package entity.song;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * create songs
 */
public class SongFactory {
    public Song createSong(String name, String artist, String album, LocalDateTime releaseDate, ArrayList<String> tags, String URL) {
        return new Song(name, artist, album, releaseDate, tags, URL);
    }
}
