package usecase.login;

public class LoginOutputData {
    private final String signedInAs;

    public LoginOutputData(String signedInAs) {
        this.signedInAs = signedInAs;
    }

    public String getSignedInAs() {
        return signedInAs;
    }
}
