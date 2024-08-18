package use_case.edit_profile;

import entity.user.User;

/**
 * input data for edit profile use case
 */
public class EditProfileInputData {
    private final User profileToAlter;
    private final String username;
    private final String email;
    private final String name;


    /**
     * create instance of input data for edit profile use case
     * @param profile data
     */
    public EditProfileInputData(User profile) {
        this.profileToAlter = profile;

        this.username = "";
        this.email = "";
        this.name = "";
    }

    /**
     * update data
     * @param profileToAlter current user
     * @param username of user
     * @param email of user
     * @param name of user
     */
    public EditProfileInputData(User profileToAlter, String username, String email, String name) {
        this.username = username;
        this.email = email;
        this.name = name;

        this.profileToAlter = profileToAlter;
    }

    /**
     * access current profile
     * @return current profile
     */
    public User getUserToAlter() {
        return profileToAlter;
    }

    /**
     * access username
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * access name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * access email
     * @return email
     */
    public String getEmail() { return email;}
}
