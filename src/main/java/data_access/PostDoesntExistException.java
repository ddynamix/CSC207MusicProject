package data_access;

/**
 * exception for post not in database
 */
public class PostDoesntExistException extends Exception {
    public PostDoesntExistException() {
        super("Post does not exist in the database");
    }
}
