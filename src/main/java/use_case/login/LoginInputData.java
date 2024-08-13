package use_case.login;

/**
 * input data for login use case
 */
public class LoginInputData {
    private String username;
    private String password;

    /**
     * create instancee of input data for login use case
     * @param username of login
     * @param password of login
     */
    public LoginInputData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * access username
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /** access password
     * @return password
     */
    public String getPassword() {
        return password;
    }
}
