package use_case.play_music;

public interface PlayMusicInputBoundary {
    void playMusic(PlayMusicInputData inputData);
    void stopMusic();
    void noPreview() throws NoPreviewAvailableException;
}
