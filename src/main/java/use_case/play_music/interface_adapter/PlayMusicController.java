package use_case.play_music.interface_adapter;

import data_access.spotify.PreviewMP3Downloader;
import data_access.spotify.SpotifyService;
import entity.song.Song;
import use_case.play_music.PlayMusicInputBoundary;
import use_case.play_music.PlayMusicInputData;

public class PlayMusicController {
    PlayMusicInputBoundary playMusicInteractor;

    public PlayMusicController(PlayMusicInputBoundary playMusicInteractor) {
        this.playMusicInteractor = playMusicInteractor;
    }

    public void playMusic(Song songToPlay) {
        PreviewMP3Downloader previewMP3Downloader = new PreviewMP3Downloader();
        SpotifyService spotifyService = new SpotifyService();
        String previewURL = spotifyService.getPreviewUrl(songToPlay.getName());
        String outputFilePath = "./songs/downloaded_file_" + System.currentTimeMillis() + ".mp3";
        try {
            previewMP3Downloader.downloadMP3(previewURL, outputFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        playMusicInteractor.playMusic(new PlayMusicInputData(outputFilePath));
    }
}
