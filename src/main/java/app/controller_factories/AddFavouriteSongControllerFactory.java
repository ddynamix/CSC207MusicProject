package app.controller_factories;

import data_access.RelationalSongDataAccessInterface;
import data_access.SongDataAccessInterface;
import data_access.spotify.SpotifyService;
import data_access.spotify.SpotifyServiceInterface;
import use_case.add_favourite_song.AddFavouriteSongInputBoundary;
import use_case.add_favourite_song.AddFavouriteSongInteractor;
import use_case.add_favourite_song.interface_adapter.AddFavouriteSongController;

public class AddFavouriteSongControllerFactory {
    private AddFavouriteSongControllerFactory() {}

    public static AddFavouriteSongController createAddFavouriteSongController(SongDataAccessInterface songDataAccess, RelationalSongDataAccessInterface relationalSongDataAccess) {
        AddFavouriteSongInputBoundary addFavouriteSongInteractor = new AddFavouriteSongInteractor(songDataAccess, relationalSongDataAccess);
        SpotifyServiceInterface spotifyService = new SpotifyService();

        return new AddFavouriteSongController(addFavouriteSongInteractor, spotifyService, songDataAccess);
    }
}
