package entity.song;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class SongFactoryTest {

    @Test
    void testCreateSong() {
        SongFactory songFactory = new SongFactory();
        String name = "Imagine";
        String artist = "John Lennon";
        String album = "Imagine";
        LocalDateTime releaseDate = LocalDateTime.of(1971, 10, 11, 0, 0);
        ArrayList<String> tags = new ArrayList<>();
        tags.add("Rock");
        tags.add("Classic");
        String URL = "https://example.com/imagine";

        Song song = songFactory.createSong(name, artist, album, releaseDate, tags, URL);

        assertNotNull(song, "The song should not be null");
        assertEquals(name, song.getName(), "The name should be the same");
        assertEquals(artist, song.getArtist(), "The artist should be the same");
        assertEquals(album, song.getAlbum(), "The album should be the same");
        assertEquals(releaseDate, song.getReleaseDate(), "The release date should be the same");
        assertEquals(tags, song.getTags(), "The tags should be the same");
        assertEquals(URL, song.getURL(), "The URL should be the same");
    }
}
