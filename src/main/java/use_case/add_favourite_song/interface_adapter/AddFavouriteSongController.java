package use_case.add_favourite_song.interface_adapter;

import data_access.SongDataAccessInterface;
import data_access.spotify.SpotifyService;
import data_access.spotify.SpotifyServiceInterface;
import entity.user.User;
import use_case.add_favourite_song.AddFavouriteSongInputBoundary;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AddFavouriteSongController {
    private final AddFavouriteSongInputBoundary addFavouriteSongInteractor;
    private final SpotifyServiceInterface spotifyService;
    private final SongDataAccessInterface songDataAccess;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public AddFavouriteSongController(AddFavouriteSongInputBoundary addFavouriteSongInteractor, SpotifyServiceInterface spotifyService, SongDataAccessInterface songDataAccess) {
        this.addFavouriteSongInteractor = addFavouriteSongInteractor;
        this.spotifyService = spotifyService;
        this.songDataAccess = songDataAccess;
    }

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
