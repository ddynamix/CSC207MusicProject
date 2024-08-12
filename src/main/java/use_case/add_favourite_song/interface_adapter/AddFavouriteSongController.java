package use_case.add_favourite_song.interface_adapter;

import data_access.SongDataAccessInterface;
import data_access.spotify.SpotifyService;
import entity.user.User;
import net.bytebuddy.asm.Advice;
import use_case.add_favourite_song.AddFavouriteSongInputBoundary;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AddFavouriteSongController {
    private final AddFavouriteSongInputBoundary addFavouriteSongInteractor;
    private final SpotifyService spotifyService;
    private final SongDataAccessInterface songDataAccess;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public AddFavouriteSongController(AddFavouriteSongInputBoundary addFavouriteSongInteractor, SpotifyService spotifyService, SongDataAccessInterface songDataAccess) {
        this.addFavouriteSongInteractor = addFavouriteSongInteractor;
        this.spotifyService = spotifyService;
        this.songDataAccess = songDataAccess;
    }

    public void addFavouriteSong(String songName, User user) {
        String name = SpotifyService.getSongName(songName);
        String artist = SpotifyService.getSongArtist(songName);
        String album = SpotifyService.getSongAlbum(songName);
        String releaseDate = SpotifyService.getSongReleaseDate(songName);
        String url = SpotifyService.getPreviewUrl(songName);
        String tags = SpotifyService.getSongTags(songName);
        int id = songDataAccess.getUniqueId();

        addFavouriteSongInteractor.addFavouriteSong(name, artist, album, parseDate(releaseDate), url, parseTags(tags), id, user);
    }

    private LocalDateTime parseDate(String date) {
        return LocalDateTime.parse(date, formatter);
    }

    private ArrayList<String> parseTags(String tags) {
        return new ArrayList<>(List.of(tags.split(",")));
    }
}
