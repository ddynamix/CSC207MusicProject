package use_case.play_music.interface_adapter;

import data_access.spotify.PreviewMP3Downloader;
import data_access.spotify.SpotifyServiceInterface;
import entity.song.Song;
import use_case.play_music.NoPreviewAvailableException;
import use_case.play_music.PlayMusicInputBoundary;
import use_case.play_music.PlayMusicInputData;

import java.io.IOException;

/**
 * create controllers for play music use case
 */
public class PlayMusicController {
    PlayMusicInputBoundary playMusicInteractor;
    SpotifyServiceInterface spotifyService;

    /**
     * create an instance of controller for play music use case
     * @param playMusicInteractor interactor for play music use case
     * @param spotifyService music access
     */
    public PlayMusicController(PlayMusicInputBoundary playMusicInteractor, SpotifyServiceInterface spotifyService) {
        this.playMusicInteractor = playMusicInteractor;
        this.spotifyService = spotifyService;
    }

    /**
     * start audio
     * @param song to be players
     * @throws NoPreviewAvailableException exception for preview error
     */
    public void playMusic(Song song) throws NoPreviewAvailableException {
        String previewURL = spotifyService.getPreviewUrl(song.getName());
        if (previewURL == null || previewURL.isEmpty() || previewURL.equals("null")) {
            playMusicInteractor.noPreview();
            return;
        }

        String filepath = "src/main/resources/songs/downloaded_file_" + song.getId() + ".mp3";
        try {
            System.out.println("Downloading preview" + previewURL + "to" + filepath);
            PreviewMP3Downloader.downloadMP3(previewURL, filepath);
            PlayMusicInputData inputData = new PlayMusicInputData(filepath);
            playMusicInteractor.playMusic(inputData);
        } catch (IOException e) {
            throw new NoPreviewAvailableException("Failed to download preview");
        }
    }

    /**
     * end audio
     */
    public void stopMusic() {
        playMusicInteractor.stopMusic();
    }
}
