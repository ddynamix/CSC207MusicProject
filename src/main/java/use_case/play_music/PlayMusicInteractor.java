package use_case.play_music;

import javazoom.jl.player.Player;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

public class PlayMusicInteractor implements PlayMusicInputBoundary {
    private Player player;

    public PlayMusicInteractor() {
    }

    public void playMusic(PlayMusicInputData inputData) {
        try (InputStream inputStream = getInputStream(inputData.getFilepath())) {
            System.out.println("Playing music..." + inputData.getFilepath());
            player = new Player(inputStream);
            player.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private InputStream getInputStream(String path) throws Exception {
        if (path.startsWith("http://") || path.startsWith("https://")) {
            return new URL(path).openStream();
        } else {
            return new FileInputStream(path);
        }
    }

    public void stopMusic() {
        if (player != null) {
            player.close();
        }
    }

    public void noPreview() throws NoPreviewAvailableException {
        throw new NoPreviewAvailableException("No preview available.");
    }
}
