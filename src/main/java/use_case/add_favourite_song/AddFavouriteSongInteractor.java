package use_case.add_favourite_song;

import data_access.SongDataAccessInterface;
import entity.song.Song;
import entity.user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class AddFavouriteSongInteractor implements AddFavouriteSongInputBoundary {
    private SongDataAccessInterface songDataAccessInterface;

    public AddFavouriteSongInteractor(SongDataAccessInterface songDataAccessInterface) {
        this.songDataAccessInterface = songDataAccessInterface;
    }

    @Override
    public void addFavouriteSong(String songName, String songArtist, String songAlbum, LocalDateTime songReleaseDate, String songURL, ArrayList<String> songTags, int id, User user) {
        Song song = new Song(songName, songArtist, songAlbum, songReleaseDate, songTags, songURL, id);
        songDataAccessInterface.createSong(song);
    }
}
