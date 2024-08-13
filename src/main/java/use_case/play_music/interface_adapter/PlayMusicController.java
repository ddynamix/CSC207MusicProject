package use_case.play_music.interface_adapter;

import data_access.spotify.PreviewMP3Downloader;
import data_access.spotify.SpotifyService;
import data_access.spotify.SpotifyServiceInterface;
import entity.song.Song;
import use_case.play_music.NoPreviewAvailableException;
import use_case.play_music.PlayMusicInputBoundary;
import use_case.play_music.PlayMusicInputData;

import java.io.IOException;

public class PlayMusicController {
    PlayMusicInputBoundary playMusicInteractor;
    SpotifyServiceInterface spotifyService;

    public PlayMusicController(PlayMusicInputBoundary playMusicInteractor, SpotifyServiceInterface spotifyService) {
        this.playMusicInteractor = playMusicInteractor;
        this.spotifyService = spotifyService;
    }

    public void playMusic(Song song) throws NoPreviewAvailableException {
        String previewURL = spotifyService.getPreviewUrl(song.getName());
        if (previewURL == null || previewURL.isEmpty()) {
            playMusicInteractor.noPreview();
            return;
        }

        String filepath = "/songs/downloaded_file_" + song.getId() + ".mp3";
        try {
            PreviewMP3Downloader.downloadMP3(previewURL, filepath);
            PlayMusicInputData inputData = new PlayMusicInputData(filepath);
            playMusicInteractor.playMusic(inputData);
        } catch (IOException e) {
            throw new NoPreviewAvailableException("Failed to download preview");
        }
    }

    public void stopMusic() {
        playMusicInteractor.stopMusic();
    }
}
