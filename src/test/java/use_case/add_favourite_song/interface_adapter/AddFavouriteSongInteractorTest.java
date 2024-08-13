package use_case.add_favourite_song.interface_adapter;

import data_access.RelationalSongDataAccessInterface;
import data_access.SongDataAccessInterface;
import entity.song.Song;
import entity.user.AudienceUser;
import entity.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import use_case.add_favourite_song.AddFavouriteSongInteractor;
import use_case.add_favourite_song.AddFavouriteSongOutputBoundary;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class AddFavouriteSongInteractorTest {
    private AddFavouriteSongInteractor interactor;
    private SongDataAccessInterface songDataAccess;
    private RelationalSongDataAccessInterface relationalSongDataAccess;
    private AddFavouriteSongOutputBoundary addFavouriteSongOutputBoundary;

    @BeforeEach
    public void setUp() {
        addFavouriteSongOutputBoundary = mock(AddFavouriteSongOutputBoundary.class);
        songDataAccess = mock(SongDataAccessInterface.class);
        relationalSongDataAccess = mock(RelationalSongDataAccessInterface.class);
        interactor = new AddFavouriteSongInteractor(addFavouriteSongOutputBoundary, songDataAccess, relationalSongDataAccess);
    }

    @Test
    public void testAddFavouriteSong() {
        User user = new AudienceUser("Test User", "testUser", "testPass", "testMail");
        LocalDate releaseDate = LocalDate.of(2024, 8, 13);
        ArrayList<String> tags = new ArrayList<>();
        tags.add("tag1");
        tags.add("tag2");
        int songId = 1;

        when(songDataAccess.getUniqueId()).thenReturn(songId);

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

        verify(songDataAccess, times(1)).createSong(any(Song.class));

        verify(relationalSongDataAccess, times(1)).addFavourite(eq(user), any(Song.class));
    }
}
