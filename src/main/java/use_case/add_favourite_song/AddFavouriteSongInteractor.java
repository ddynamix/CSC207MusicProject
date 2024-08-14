package use_case.add_favourite_song;

import data_access.RelationalSongDataAccessInterface;
import data_access.SongDataAccessInterface;
import entity.song.Song;
import entity.user.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class AddFavouriteSongInteractor implements AddFavouriteSongInputBoundary {
    private SongDataAccessInterface songDataAccessInterface;
    private RelationalSongDataAccessInterface relationalSongDataAccessInterface;
    private AddFavouriteSongOutputBoundary addFavouriteSongPresenter;

    public AddFavouriteSongInteractor(AddFavouriteSongOutputBoundary addFavouriteSongPresenter, SongDataAccessInterface songDataAccessInterface, RelationalSongDataAccessInterface relationalSongDataAccessInterface) {
        this.songDataAccessInterface = songDataAccessInterface;
        this.relationalSongDataAccessInterface = relationalSongDataAccessInterface;
        this.addFavouriteSongPresenter = addFavouriteSongPresenter;
    }

    @Override
    public void addFavouriteSong(String songName, String songArtist, String songAlbum, LocalDate songReleaseDate, String songURL, ArrayList<String> songTags, int id, User user) {
        Song song = new Song(songName, songArtist, songAlbum, songReleaseDate, songTags, songURL, id);
        songDataAccessInterface.createSong(song);
        relationalSongDataAccessInterface.addFavourite(user, song);
        addFavouriteSongPresenter.updateFavouriteSong();
    }
}
