package use_case.play_music.interface_adapter;

import data_access.spotify.SpotifyServiceInterface;
import entity.song.Song;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import use_case.play_music.NoPreviewAvailableException;
import use_case.play_music.PlayMusicInputBoundary;
import use_case.play_music.PlayMusicInputData;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PlayMusicControllerTest {

    private PlayMusicController playMusicController;
    private PlayMusicInputBoundary playMusicInteractor;
    private SpotifyServiceInterface spotifyService;

    @BeforeEach
    public void setUp() {
        playMusicInteractor = mock(PlayMusicInputBoundary.class);
        spotifyService = mock(SpotifyServiceInterface.class);
        playMusicController = new PlayMusicController(playMusicInteractor, spotifyService);
    }

    @Test
    public void testPlayMusic_WithPreview() throws NoPreviewAvailableException {
        Song songToPlay = new Song("Baby, I'm Yours", "testArtist", "testAlbum", LocalDate.now(), null,  "", 1);
        String previewURL = "https://p.scdn.co/mp3-preview/0d5a20ec4d3e9367944534d280a4461c67101218?cid=acf0c836dea14da9a440cf55706b4a90";

        when(spotifyService.getPreviewUrl(songToPlay.getName())).thenReturn(previewURL);

        playMusicController.playMusic(songToPlay);

        ArgumentCaptor<PlayMusicInputData> inputDataCaptor = ArgumentCaptor.forClass(PlayMusicInputData.class);
        verify(playMusicInteractor, times(1)).playMusic(inputDataCaptor.capture());

        PlayMusicInputData capturedInputData = inputDataCaptor.getValue();
        assertTrue(capturedInputData.getFilepath().contains("/songs/downloaded_file_"));
    }

    @Test
    public void testPlayMusic_NoPreview() throws NoPreviewAvailableException {
        Song songToPlay = new Song("testSong", "testArtist", "testAlbum", LocalDate.now(), null,  null, 1);

        when(spotifyService.getPreviewUrl(songToPlay.getName())).thenReturn(null);

        playMusicController.playMusic(songToPlay);

        verify(playMusicInteractor, times(1)).noPreview();
    }

    @Test
    public void testStopMusic() {
        playMusicController.stopMusic();

        verify(playMusicInteractor, times(1)).stopMusic();
    }
}
