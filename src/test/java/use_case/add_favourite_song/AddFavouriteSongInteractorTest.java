package use_case.add_favourite_song;

import data_access.RelationalSongDataAccessInterface;
import data_access.SongDataAccessInterface;
import entity.song.Song;
import entity.user.ArtistUser;
import entity.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AddFavouriteSongInteractorTest {

    private AddFavouriteSongInteractor addFavouriteSongInteractor;
    private SongDataAccessInterface songDataAccessInterface;
    private RelationalSongDataAccessInterface relationalSongDataAccessInterface;
    private User user;

    @BeforeEach
    public void setUp() {
        songDataAccessInterface = mock(SongDataAccessInterface.class);
        relationalSongDataAccessInterface = mock(RelationalSongDataAccessInterface.class);
        addFavouriteSongInteractor = new AddFavouriteSongInteractor(songDataAccessInterface, relationalSongDataAccessInterface);
        user = new ArtistUser("testUser", "Test User", "testPass", "testMail");
    }

    @Test
    public void testAddFavouriteSong() {
        String songName = "Test Song";
        String songArtist = "Test Artist";
        String songAlbum = "Test Album";
        LocalDate songReleaseDate = LocalDate.now();
        String songURL = "http://testurl.com";
        ArrayList<String> songTags = new ArrayList<>();
        songTags.add("Test Genre");
        int id = 1;

        addFavouriteSongInteractor.addFavouriteSong(songName, songArtist, songAlbum, songReleaseDate, songURL, songTags, id, user);

        ArgumentCaptor<Song> songCaptor = ArgumentCaptor.forClass(Song.class);
        verify(songDataAccessInterface, times(1)).createSong(songCaptor.capture());

        Song capturedSong = songCaptor.getValue();
        assertEquals(songName, capturedSong.getName());
        assertEquals(songArtist, capturedSong.getArtist());
        assertEquals(songAlbum, capturedSong.getAlbum());
        assertEquals(songReleaseDate, capturedSong.getReleaseDate());
        assertEquals(songURL, capturedSong.getURL());
        assertEquals(songTags, capturedSong.getTags());
        assertEquals(id, capturedSong.getId());
    }
}
