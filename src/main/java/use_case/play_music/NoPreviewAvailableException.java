package use_case.play_music;

/**
 * exception for no preview
 */
public class NoPreviewAvailableException extends Exception {
    public NoPreviewAvailableException(String message) {
        super(message);
    }
}
