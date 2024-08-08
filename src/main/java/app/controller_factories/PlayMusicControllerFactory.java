package app.controller_factories;

import data_access.spotify.SpotifyService;
import use_case.play_music.PlayMusicInputBoundary;
import use_case.play_music.PlayMusicInteractor;
import use_case.play_music.interface_adapter.PlayMusicController;

public class PlayMusicControllerFactory {

    private PlayMusicControllerFactory() {}

    public static PlayMusicController createPlayMusicController() {
        PlayMusicInputBoundary playMusicInteractor = new PlayMusicInteractor();
        SpotifyService spotifyService = new SpotifyService();

        return new PlayMusicController(playMusicInteractor, spotifyService);
    }
}
