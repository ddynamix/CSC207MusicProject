package use_case.play_music.interface_adapter;

import data_access.spotify.PreviewMP3Downloader;
import data_access.spotify.SpotifyService;
import entity.song.Song;
import use_case.play_music.NoPreviewAvailableException;
import use_case.play_music.PlayMusicInputBoundary;
import use_case.play_music.PlayMusicInputData;

public class PlayMusicController {
    PlayMusicInputBoundary playMusicInteractor;
    SpotifyService spotifyService;

    public PlayMusicController(PlayMusicInputBoundary playMusicInteractor, SpotifyService spotifyService) {
        this.playMusicInteractor = playMusicInteractor;
        this.spotifyService = spotifyService;
    }

    public void playMusic(Song songToPlay) throws NoPreviewAvailableException {
        PreviewMP3Downloader previewMP3Downloader = new PreviewMP3Downloader();
        String previewURL = spotifyService.getPreviewUrl(songToPlay.getName());

        // If there is no preview available for that spotify song; not all songs have previews
        if (previewURL == null || previewURL.isEmpty()) {
            playMusicInteractor.noPreview();
        }
        System.out.println("Preview URL: " + previewURL);
        String outputFilePath = "/songs/downloaded_file_" + System.currentTimeMillis() + ".mp3";
        try {
            previewMP3Downloader.downloadMP3(previewURL, outputFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        playMusicInteractor.playMusic(new PlayMusicInputData(outputFilePath));
    }

    public void stopMusic() {
        playMusicInteractor.stopMusic();
    }
}
