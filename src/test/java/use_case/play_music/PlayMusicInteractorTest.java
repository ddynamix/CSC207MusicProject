package use_case.play_music;

import javazoom.jl.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PlayMusicInteractorTest {

    @InjectMocks
    private PlayMusicInteractor playMusicInteractor;

    @Mock
    private PlayMusicInputData playMusicInputData;

    @Mock
    private Player player;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

@Test
public void testPlayMusic_WithLocalFile() throws Exception {
    String filePath = "src/test/resources/test.mp3";
    when(playMusicInputData.getFilepath()).thenReturn(filePath);

    playMusicInteractor.playMusic(playMusicInputData);

    verify(playMusicInputData, times(1)).getFilepath();
}


    @Test
    public void testPlayMusic_WithURL() throws Exception {
        String filePath = "https://p.scdn.co/mp3-preview/0d5a20ec4d3e9367944534d280a4461c67101218?cid=acf0c836dea14da9a440cf55706b4a90";
        when(playMusicInputData.getFilepath()).thenReturn(filePath);

        playMusicInteractor.playMusic(playMusicInputData);

        verify(playMusicInputData, times(1)).getFilepath();
    }

    @Test
    public void testStopMusic() {
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
