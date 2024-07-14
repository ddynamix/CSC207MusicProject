package interface_adapter;

public class FailedUserCreation extends RuntimeException {
    public FailedUserCreation(String error) {
        super(error);
    }
}
