package data_access;

import entity.song.Song;

/**
 * interface for song data access
 */
public interface SongDataAccessInterface {
    void createSong(Song song);
    Song getSongFromId(int id);
    int getUniqueId();
}
