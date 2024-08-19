package view_model;

/**
 * state for login use case
 */
public class LoginState {
    private String username = "";
    private String usernameError = null;
    private String password = "";
    private String passwordError = null;

    /**
     * create copy of loginstate
     * @param copy original
     */
    public LoginState(LoginState copy) {
        username = copy.username;
        usernameError = copy.usernameError;
        password = copy.password;
        passwordError = copy.passwordError;
    }

    /**
     * create empty instance
     */
    public LoginState() {}

    /**
     * access username
     * @return username of user
     */
    public String getUsername() {
        return username;
    }

    /**
     * access password
     * @return password of user
     */
    public String getPassword() {
        return password;
    }

    /**
     * change username
     * @param username to be set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * change password
     * @param password to be set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
