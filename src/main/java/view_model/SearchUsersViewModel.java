package view_model;

import app.interface_adapter_tools.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * view model for search users use case
 */
public class SearchUsersViewModel extends ViewModel {
    public final String TITLE_LABEL = "Search Users";
    public final String BACK_BUTTON_LABEL = "Home";

    private SearchUsersState state = new SearchUsersState();

    /**
     * create instance of view model for search users use case
     */
    public SearchUsersViewModel() {
        super("search users");
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * access state
     * @return state
     */
    public SearchUsersState getState() {
        return state;
    }

    /**
     * change state
     * @param state to be set
     */
    public void setState(SearchUsersState state) {
        this.state = state;
    }
}
