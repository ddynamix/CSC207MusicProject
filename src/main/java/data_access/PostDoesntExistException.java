package data_access;

public class PostDoesntExistException extends Exception {
    public PostDoesntExistException() {
        super("Post does not exist in the database");
    }
}
