package use_case.add_favourite_song.interface_adapter;

import data_access.SongDataAccessInterface;
import data_access.spotify.SpotifyServiceInterface;
import entity.user.User;
import use_case.add_favourite_song.AddFavouriteSongInputBoundary;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Create controllers for add favourite song use case
 */
public class AddFavouriteSongController {
    private final AddFavouriteSongInputBoundary addFavouriteSongInteractor;
    private final SpotifyServiceInterface spotifyService;
    private final SongDataAccessInterface songDataAccess;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * create an instance of controller for the add favourite song use case3
     * @param addFavouriteSongInteractor interactor to pass information to controller
     * @param spotifyService data access to Spotify APi
     * @param songDataAccess data access for song
     */
    public AddFavouriteSongController(AddFavouriteSongInputBoundary addFavouriteSongInteractor, SpotifyServiceInterface spotifyService, SongDataAccessInterface songDataAccess) {
        this.addFavouriteSongInteractor = addFavouriteSongInteractor;
        this.spotifyService = spotifyService;
        this.songDataAccess = songDataAccess;
    }

    /**
     * set attributes for favourite song
     * @param songName string name
     * @param user User type of the favourite song
     */
    public void addFavouriteSong(String songName, User user) {
        String name = spotifyService.getSongName(songName);
        String artist = spotifyService.getSongArtist(songName);
        String album = spotifyService.getSongAlbum(songName);
        String releaseDate = spotifyService.getSongReleaseDate(songName);
        String url = spotifyService.getPreviewUrl(songName);
        String tags = spotifyService.getSongTags(songName);
        int id = songDataAccess.getUniqueId();

        addFavouriteSongInteractor.addFavouriteSong(name, artist, album, parseDate(releaseDate), url, parseTags(tags), id, user);
    }

    private LocalDate parseDate(String date) {
        return LocalDate.parse(date, formatter);
    }

    private ArrayList<String> parseTags(String tags) {
        return new ArrayList<>(List.of(tags.split(",")));
    }
}
