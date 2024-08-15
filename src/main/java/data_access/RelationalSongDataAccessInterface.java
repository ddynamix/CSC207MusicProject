package data_access;

import entity.song.Song;
import entity.user.User;

/**
 * interface for user -> songs DAO
 */
public interface RelationalSongDataAccessInterface {
    void addFavourite(User user, Song song);
    void removeFavourite(User user);
}
