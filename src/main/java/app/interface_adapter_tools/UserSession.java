package app.interface_adapter_tools;

import entity.user.User;

/**
 * Signleton pattern to access the current signed in user accross all files
 */
public class UserSession {
    private static UserSession instance;
    private User loggedInUser;

    /**
     * Empty singleton constructor
     */
    private UserSession() {
    }

    /**
     * creates instance
     * @return new instance of UserSession
     */
    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    /**
     * Resets loggedInUser
     */
    public void signOut() {
        loggedInUser = null;
    }

    /**
     * change loggedInUser
     * @param user new logged in accour
     */
    public void setLoggedInUser(User user) {
        this.loggedInUser = user;
    }

    /**
     * return logged in user
     * @return User loggedInUser
     */
    public User getLoggedInUser() {
        return loggedInUser;
    }
}
