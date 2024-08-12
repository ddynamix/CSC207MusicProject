package use_case.play_music;

import javazoom.jl.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PlayMusicInteractorTest {

    private PlayMusicInteractor playMusicInteractor;
    private PlayMusicInputData playMusicInputData;

    @BeforeEach
    public void setUp() {
        playMusicInteractor = new PlayMusicInteractor();
        playMusicInputData = mock(PlayMusicInputData.class);
    }

    @Test
    public void testPlayMusic_WithLocalFile() throws Exception {
        String filePath = "path/to/local/file.mp3";
        when(playMusicInputData.getFilepath()).thenReturn(filePath);

        Player player = mock(Player.class);
        playMusicInteractor.playMusic(playMusicInputData);

        assertNotNull(player);
        verify(playMusicInputData, times(1)).getFilepath();
    }

    @Test
    public void testPlayMusic_WithURL() throws Exception {
        String filePath = "http://example.com/file.mp3";
        when(playMusicInputData.getFilepath()).thenReturn(filePath);

        Player player = mock(Player.class);
        playMusicInteractor.playMusic(playMusicInputData);

        assertNotNull(player);
        verify(playMusicInputData, times(1)).getFilepath();
    }

    @Test
    public void testStopMusic() {
        playMusicInteractor.stopMusic();

        Player player = mock(Player.class);
        playMusicInteractor.stopMusic();
        verify(player, times(1)).close();
    }

    @Test
    public void testNoPreview() {
        assertThrows(NoPreviewAvailableException.class, () -> {
            playMusicInteractor.noPreview();
        });
    }
}