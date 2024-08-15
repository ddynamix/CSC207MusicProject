package use_case.login;

import entity.user.User;

import java.util.Objects;

/**
 * output data for login use case
 */
public class LoginOutputData {
    private final User signedInAs;

    /**
     * create instance of output data for login use case
     * @param signedInAs current login
     */
    public LoginOutputData(User signedInAs) {
        this.signedInAs = signedInAs;
    }

    /**
     * access current login
     * @return logged in user
     */
    public User getSignedInAs() {
        return signedInAs;
    }

    /**
     * check that the password is correct
     * @param o object to compare
     * @return boolean
     */
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
