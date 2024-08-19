package use_case.edit_user;

/**
 * interface for output data for edit user use case
 */
public interface EditUserOutputBoundary {
    void goToUserEditor(EditUserOutputData outputData);
    void prepareSuccessView(EditUserSuccessOutputData outputData);
}