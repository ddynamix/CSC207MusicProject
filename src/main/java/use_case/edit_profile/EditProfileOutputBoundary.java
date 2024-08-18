package use_case.edit_profile;

/**
 * interface for output data for edit profile use case
 */
public interface EditProfileOutputBoundary {
    void goToUserEditor(EditProfileOutputData outputData);
    void prepareSuccessView(EditProfileSuccessOutputData outputData);
}
