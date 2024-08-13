package use_case.search_users;

/**
 * interface for output data for user search use case
 */
public interface SearchUsersOutputBoundary {
    void updateDisplayedUsers(SearchUsersOutputData outputData);
}
