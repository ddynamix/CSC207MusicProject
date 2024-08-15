package use_case.search_users;

import entity.user.User;

/**
 * input data for user search use case
 */
public class SearchUsersInputData {
    private final String keywordToSearch;
    private final Class<? extends User> userClassToSearch;

    /**
     * create instance of input data for user search use case
     * @param keywordToSearch string to be searched
     * @param userClassToSearch user object to search
     */
    public SearchUsersInputData(String keywordToSearch, Class<? extends User> userClassToSearch) {
        this.keywordToSearch = keywordToSearch;
        this.userClassToSearch = userClassToSearch;
    }

    /**
     * access key word
     * @return key word
     */
    public String getKeywordToSearch() {
        return keywordToSearch;
    }

    /**
     * access user type
     * @return user type
     */
    public Class<? extends User> getUserClassToSearch() {
        return userClassToSearch;
    }
}
