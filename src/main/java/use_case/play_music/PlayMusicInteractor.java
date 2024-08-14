package use_case.play_music;

import javazoom.jl.player.Player;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

public class PlayMusicInteractor implements PlayMusicInputBoundary {
    private Player player;
    private Thread playerThread;

    private boolean isPlaying = false;

    public PlayMusicInteractor() {
    }

    public void playMusic(PlayMusicInputData inputData) {
        if (isPlaying) {
            stopMusic();
        }
        try {
            InputStream inputStream = getInputStream(inputData.getFilepath());
            player = new Player(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        playerThread = new Thread(() -> {
            try {
                player.play();
                // Once the player finishes playing, delete the file
                deleteFile(inputData.getFilepath());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        playerThread.start();
        isPlaying = true;
    }

    InputStream getInputStream(String path) throws Exception {
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
        if (playerThread != null && playerThread.isAlive()) {
            playerThread.interrupt();
        }
        isPlaying = false;
    }

    private void deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("File deleted: " + filePath);
            } else {
                System.out.println("Failed to delete file: " + filePath);
            }
        } else {
            System.out.println("File not found: " + filePath);
        }
    }

    public void noPreview() throws NoPreviewAvailableException {
        throw new NoPreviewAvailableException("No preview available.");
    }
}
