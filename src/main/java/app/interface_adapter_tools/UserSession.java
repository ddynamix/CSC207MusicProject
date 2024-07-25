package app.interface_adapter_tools;

import entity.user.User;

public class UserSession {
    private static UserSession instance;
    private User loggedInUser;

    // Singleton
    private UserSession() {
    }

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public void setLoggedInUser(User user) {
        this.loggedInUser = user;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }
}
