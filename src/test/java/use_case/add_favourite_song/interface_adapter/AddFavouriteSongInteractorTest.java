package use_case.add_favourite_song.interface_adapter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import data_access.RelationalSongDataAccessInterface;
import data_access.SongDataAccessInterface;
import entity.song.Song;
import entity.user.AudienceUser;
import entity.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import use_case.add_favourite_song.AddFavouriteSongInteractor;

import java.time.LocalDate;
import java.util.ArrayList;

public class AddFavouriteSongInteractorTest {

    private SongDataAccessInterface songDataAccess;
    private RelationalSongDataAccessInterface relationalSongDataAccess;
    private AddFavouriteSongInteractor interactor;

    @BeforeEach
    public void setUp() {
        songDataAccess = Mockito.mock(SongDataAccessInterface.class);
        relationalSongDataAccess = Mockito.mock(RelationalSongDataAccessInterface.class);
        interactor = new AddFavouriteSongInteractor(songDataAccess, relationalSongDataAccess);
    }

    @Test
    public void testAddFavouriteSong() {
        User user = new AudienceUser("Test User", "testUser", "testPassword", "testEmail");
        LocalDate releaseDate = LocalDate.of(2024, 8, 13);
        ArrayList<String> tags = new ArrayList<>();
        tags.add("tag1");
        tags.add("tag2");
        int songId = 1;

        Song expectedSong = new Song("Test Song", "Test Artist", "Test Album", releaseDate, tags, "testUrl", songId);

        when(songDataAccess.getUniqueId()).thenReturn(songId);

        ArgumentCaptor<Song> songCaptor = ArgumentCaptor.forClass(Song.class);

        interactor.addFavouriteSong(
                "Test Song",
                "Test Artist",
                "Test Album",
                releaseDate,
                "testUrl",
                tags,
                songId,
                user
        );

        verify(songDataAccess).createSong(songCaptor.capture());
        Song actualSong = songCaptor.getValue();

        assertNotNull(actualSong, "The created song should not be null");
        assertEquals(expectedSong.getName(), actualSong.getName(), "The created song should match the expected song");

        // Also verify the relational data access was called correctly
        verify(relationalSongDataAccess).addFavourite(user, actualSong);
        assertEquals(expectedSong, user.getFeaturedSong(), "The user's featured song should be set correctly");
    }
}
