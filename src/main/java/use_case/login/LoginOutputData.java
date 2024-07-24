package use_case.login;

import entity.user.User;

import java.util.Objects;

public class LoginOutputData {
    private final User signedInAs;

    public LoginOutputData(User signedInAs) {
        this.signedInAs = signedInAs;
    }

    public User getSignedInAs() {
        return signedInAs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginOutputData that = (LoginOutputData) o;
        return Objects.equals(signedInAs, that.getSignedInAs());
    }

    @Override
    public int hashCode() {
        return Objects.hash(signedInAs);
    }
}
