package use_case.edit_user;

/**
 * interface for input data for edit user use case
 */
public interface EditUserInputBoundary {
    void editUser(EditUserInputData inputData);
    void deleteUser(EditUserInputData inputData);
    void updateUser(EditUserInputData inputData);
}
