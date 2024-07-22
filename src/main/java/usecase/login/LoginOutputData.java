package usecase.login;

import java.util.Objects;

public class LoginOutputData {
    private final String signedInAs;

    public LoginOutputData(String signedInAs) {
        this.signedInAs = signedInAs;
    }

    public String getSignedInAs() {
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
