package use_case.play_music;

/**
 * interface for play music use case
 */
public interface PlayMusicInputBoundary {
    void playMusic(PlayMusicInputData inputData);
    void stopMusic();
    void noPreview() throws NoPreviewAvailableException;
}
