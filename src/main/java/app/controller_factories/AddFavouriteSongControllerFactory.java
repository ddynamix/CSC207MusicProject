package app.controller_factories;

import data_access.RelationalSongDataAccessInterface;
import data_access.SongDataAccessInterface;
import data_access.spotify.SpotifyService;
import data_access.spotify.SpotifyServiceInterface;
import use_case.add_favourite_song.AddFavouriteSongInputBoundary;
import use_case.add_favourite_song.AddFavouriteSongInteractor;
import use_case.add_favourite_song.AddFavouriteSongOutputBoundary;
import use_case.add_favourite_song.interface_adapter.AddFavouriteSongController;
import use_case.add_favourite_song.interface_adapter.AddFavouriteSongPresenter;
import view_model.ProfileViewModel;

public class AddFavouriteSongControllerFactory {
    private AddFavouriteSongControllerFactory() {}

    public static AddFavouriteSongController createAddFavouriteSongController(ProfileViewModel profileViewModel, SongDataAccessInterface songDataAccess, RelationalSongDataAccessInterface relationalSongDataAccess) {
        AddFavouriteSongOutputBoundary addFavouriteSongPresenter = new AddFavouriteSongPresenter(profileViewModel);
        AddFavouriteSongInputBoundary addFavouriteSongInteractor = new AddFavouriteSongInteractor(addFavouriteSongPresenter, songDataAccess, relationalSongDataAccess);
        SpotifyServiceInterface spotifyService = new SpotifyService();

        return new AddFavouriteSongController(addFavouriteSongInteractor, spotifyService, songDataAccess);
    }
}
