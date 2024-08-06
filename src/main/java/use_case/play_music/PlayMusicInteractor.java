package use_case.play_music;

import javazoom.jl.player.Player;

import java.io.InputStream;
import java.net.URL;

public class PlayMusicInteractor {
    private Player player;

    public PlayMusicInteractor(PlayMusicInputBoundary inputBoundary) {
    }

    public void playMusic(PlayMusicInputData inputData) {
        try (InputStream inputStream = new URL(inputData.getFilepath()).openStream()) {
            player = new Player(inputStream);
            player.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        if (player != null) {
            player.close();
        }
    }
}
