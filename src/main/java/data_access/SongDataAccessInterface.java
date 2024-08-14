package data_access;

import entity.song.Song;

public interface SongDataAccessInterface {
    void createSong(Song song);
    public Song getSongFromId(int id);
    public int getUniqueId();
}
