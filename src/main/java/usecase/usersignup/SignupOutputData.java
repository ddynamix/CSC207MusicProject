package usecase.usersignup;

/**
 * data to be passed from interactor
 */
public class SignupOutputData {

    private final String username;
    private String creationTime;

    /**
     * Create output data instance
     * @param username String   user identifier
     * @param creationTime  String  time created
     */
    public SignupOutputData(String username, String creationTime) {
        this.username = username;
        this.creationTime = creationTime;
    }

    /**
     * Return username
     * @return String username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Return time of creation
     * @return String creationTime
     */
    public String getCreationTime() {
        return creationTime;
    }

    /**
     * Set time of creation
     * @param creationTime String creationTime
     */
    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

}
