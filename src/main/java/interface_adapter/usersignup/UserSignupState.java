package interface_adapter.usersignup;

/**
 * Template user for creation
 */
public class UserSignupState {
    private String username = "";
    private String usernameError = null;
    private String password = "";
    private String passwordError = null;
    private String repeatPassword = "";
    private String repeatPasswordError = null;
    private String email = "";
    private String emailError = null;

    /**
     * create instance
     * @param copy UserSignupState duplication
     */
    public UserSignupState(UserSignupState copy) {
        username = copy.username;
        usernameError = copy.usernameError;
        password = copy.password;
        passwordError = copy.passwordError;
        repeatPassword = copy.repeatPassword;
        repeatPasswordError = copy.repeatPasswordError;
        email = copy.email;
        emailError = copy.emailError;
    }

    /**
     * Return username
     * @return String username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Return username errror message
     * @return String usernameError
     */
    public String getUsernameError() {
        return usernameError;
    }

    /**
     * Return password
     * @return String password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Return password error message
     * @return String passwordError
     */
    public String getPasswordError() {
        return passwordError;
    }

    /**
     * Return repeated password entry
     * @return String repeatPassword
     */
    public String getRepeatPassword() {
        return repeatPassword;
    }

    /**
     * Return Repeated password entry error
     * @return String repeatPasswordError
     */
    public String getRepeatPasswordError() {
        return repeatPasswordError;
    }

    /**
     * Return email
     * @return String email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Return email error message
     * @return String emailError
     */
    public String getEmailError() {
        return emailError;
    }

    /**
     * Mutate username
     * @param username string
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Create username error
     * @param usernameError string
     */
    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    /**
     * Mutate password
     * @param password String
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * create password error
     * @param passwordError String
     */
    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    /**
     * Mutate repeat password
     * @param repeatPassword String
     */
    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    /**
     * Create repeated password entry error
     * @param repeatPasswordError String
     */
    public void setRepeatPasswordError(String repeatPasswordError) {
        this.repeatPasswordError = repeatPasswordError;
    }

    /**
     * Mutate email
     * @param email String
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Create email error
     * @param emailError String
     */
    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }
}