package use_case.play_music;

import entity.song.Song;

public class PlayMusicInputData {
    private final String songFilePath;

    public PlayMusicInputData(String filePath) {
        this.songFilePath = filePath;
    }

    public String getFilepath() {
        return songFilePath;
    }
}
