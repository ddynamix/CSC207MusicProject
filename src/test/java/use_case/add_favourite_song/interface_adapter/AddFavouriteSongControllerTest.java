package use_case.add_favourite_song.interface_adapter;

import data_access.SongDataAccessInterface;
import data_access.UserAlreadyExistsException;
import data_access.UserDataAccessInterface;
import data_access.spotify.SpotifyService;
import entity.user.ArtistUser;
import entity.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_case.add_favourite_song.AddFavouriteSongInputBoundary;
import use_case.add_favourite_song.AddFavouriteSongInteractor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AddFavouriteSongControllerTest {

    private AddFavouriteSongController addFavouriteSongController;
    private SpotifyService spotifyService;
    private UserDataAccessInterface userRepository;
    private SongDataAccessInterface songDataAccess;
    private AddFavouriteSongInputBoundary addFavouriteSongInteractor;
    private User user;

    @BeforeEach
    public void setUp() {
        spotifyService = mock(SpotifyService.class);
        userRepository = mock(UserDataAccessInterface.class);
        songDataAccess = mock(SongDataAccessInterface.class);
        addFavouriteSongInteractor = new AddFavouriteSongInteractor(songDataAccess);
        addFavouriteSongController = new AddFavouriteSongController(addFavouriteSongInteractor, spotifyService, songDataAccess);
        user = new ArtistUser("testUser", "Test User", "test password", "test email");
    }

    @Test
    public void testAddFavouriteSong() {
        String songName = "Test Song";
        String songId = "testSongId";

        when(spotifyService.getSongName(songName)).thenReturn(songName);
        when(spotifyService.getSongArtist(songName)).thenReturn("Test Artist");
        when(spotifyService.getSongAlbum(songName)).thenReturn("Test Album");
        when(spotifyService.getSongReleaseDate(songName)).thenReturn("2023-10-05");
        when(spotifyService.getSongTags(songName)).thenReturn("Test Genre");
        when(spotifyService.getPreviewUrl(songName)).thenReturn("http://testurl.com");

        addFavouriteSongController.addFavouriteSong(songName, user);

        try {
            verify(userRepository, times(1)).create(user);
        } catch (UserAlreadyExistsException e) {
            e.printStackTrace();
        }
        assertEquals(songName, user.getFeaturedSong().getName());
    }
}