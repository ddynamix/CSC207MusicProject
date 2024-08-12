package use_case.add_favourite_song;

import entity.user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface AddFavouriteSongInputBoundary {
    void addFavouriteSong(String songName, String songArtist, String songAlbum, LocalDateTime songReleaseDate, String songURL, ArrayList<String> songTags, int id, User user);
}
