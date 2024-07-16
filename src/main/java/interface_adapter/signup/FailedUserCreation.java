package interface_adapter.signup;

public class FailedUserCreation extends RuntimeException {
    public FailedUserCreation(String error) {
        super(error);
    }
}
