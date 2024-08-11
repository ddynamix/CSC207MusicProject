package use_case.search_users;

import entity.user.User;

public class SearchUsersInputData {
    private final String keywordToSearch;
    private final Class<? extends User> userClassToSearch;

    public SearchUsersInputData(String keywordToSearch, Class<? extends User> userClassToSearch) {
        this.keywordToSearch = keywordToSearch;
        this.userClassToSearch = userClassToSearch;
    }

    public String getKeywordToSearch() {
        return keywordToSearch;
    }

    public Class<? extends User> getUserClassToSearch() {
        return userClassToSearch;
    }
}
