package data_access;

/**
 * exception for creating username with username already in database
 */
public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException() {
        super("User already exists in the database");
    }
}
