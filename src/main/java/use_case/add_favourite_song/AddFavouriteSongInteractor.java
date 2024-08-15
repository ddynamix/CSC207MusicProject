package use_case.add_favourite_song;

import data_access.RelationalSongDataAccessInterface;
import data_access.SongDataAccessInterface;
import entity.song.Song;
import entity.user.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Create interactors for add favourite song use case
 */
public class AddFavouriteSongInteractor implements AddFavouriteSongInputBoundary {
    private SongDataAccessInterface songDataAccessInterface;
    private RelationalSongDataAccessInterface relationalSongDataAccessInterface;
    private AddFavouriteSongOutputBoundary addFavouriteSongPresenter;

    /**
     * create instance of interactor for add favourite song use case
     * @param addFavouriteSongPresenter presenter to pass information to view
     * @param songDataAccessInterface data access for songs
     * @param relationalSongDataAccessInterface data access for user : songs
     */
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
