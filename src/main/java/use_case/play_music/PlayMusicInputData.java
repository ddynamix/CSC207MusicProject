package use_case.play_music;

import entity.song.Song;

/**
 * create input data for play music use case
 */
public class PlayMusicInputData {
    private final String songFilePath;

    /**
     * create instance for play music use case
     * @param filePath to find
     */
    public PlayMusicInputData(String filePath) {
        this.songFilePath = filePath;
    }

    /**
     * return file path
     * @return path
     */
    public String getFilepath() {
        return songFilePath;
    }
}
