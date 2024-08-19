package use_case.edit_user;

import entity.user.User;

/**
 * input data for edit user use case
 */
public class EditUserInputData {
    private final User userToAlter;

    private final String title;
    private final String text;
    private final String media;


    /**
     * create instance of input data for edit user use case
     * @param userToAlter data
     */
    public EditUserInputData(User userToAlter) {
        this.userToAlter = userToAlter;

        this.title = "";
        this.text = "";
        this.media = "";
    }

    /**
     * update data
     * @param userToAlter current user
     * @param title of user
     * @param text of user
     * @param media of user
     */
    public EditUserInputData(User userToAlter, String title, String text, String media) {
        this.title = title;
        this.text = text;
        this.media = media;

        this.userToAlter = userToAlter;
    }

    /**
     * access current user
     * @return current user
     */
    public User getUserToAlter() {
        return userToAlter;
    }

    /**
     * access title
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * access text
     * @return text
     */
    public String getText() { return text;}

    /**
     * access media
     * @return media
     */
    public String getMedia() {
        return media;
    }
}
