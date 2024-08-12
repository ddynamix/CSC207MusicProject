package use_case.play_music.interface_adapter;

import data_access.spotify.SpotifyService;
import entity.song.Song;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import use_case.play_music.NoPreviewAvailableException;
import use_case.play_music.PlayMusicInputBoundary;
import use_case.play_music.PlayMusicInputData;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PlayMusicControllerTest {

    private PlayMusicController playMusicController;
    private PlayMusicInputBoundary playMusicInteractor;
    private SpotifyService spotifyService;

    @BeforeEach
    public void setUp() {
        playMusicInteractor = mock(PlayMusicInputBoundary.class);
        spotifyService = mock(SpotifyService.class);
        playMusicController = new PlayMusicController(playMusicInteractor, spotifyService);
    }

    @Test
    public void testPlayMusic_WithPreview() throws NoPreviewAvailableException {
        Song songToPlay = new Song("testSong", "testArtist", "testAlbum", LocalDateTime.now(), null,  "", 1);
        String previewURL = "http://example.com/preview.mp3";

        when(spotifyService.getPreviewUrl(songToPlay.getName())).thenReturn(previewURL);

        playMusicController.playMusic(songToPlay);

        ArgumentCaptor<PlayMusicInputData> inputDataCaptor = ArgumentCaptor.forClass(PlayMusicInputData.class);
        verify(playMusicInteractor, times(1)).playMusic(inputDataCaptor.capture());

        PlayMusicInputData capturedInputData = inputDataCaptor.getValue();
        assertTrue(capturedInputData.getFilepath().contains("/songs/downloaded_file_"));
    }

    @Test
    public void testPlayMusic_NoPreview() throws NoPreviewAvailableException {
        Song songToPlay = new Song("testSong", "testArtist", "testAlbum", LocalDateTime.now(), null,  "", 1);

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