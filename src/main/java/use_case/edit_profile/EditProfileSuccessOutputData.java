package use_case.edit_profile;

import entity.post.Post;
import entity.user.User;

import java.util.ArrayList;

/**
 * correct output data for edit profile use case
 */
public class EditProfileSuccessOutputData {
    private final User profile;

    /**
     * create instance of correct output data for edit post use case
     * @param profile user changed
     */
    public EditProfileSuccessOutputData(User profile) {
        this.profile = profile;
    }

    /**
     * access user
     * @return user
     */
    public User getProfile() {
        return profile;
    }
}
