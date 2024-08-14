package data_access;

import entity.song.Song;
import entity.user.User;

public interface RelationalSongDataAccessInterface {
    void addFavourite(User user, Song song);
    void removeFavourite(User user);
}
