package view_model;

import app.interface_adapter_tools.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SearchUsersViewModel extends ViewModel {
    public final String TITLE_LABEL = "Search Users";
    public final String BACK_BUTTON_LABEL = "Home";

    private SearchUsersState state = new SearchUsersState();

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

    public SearchUsersState getState() {
        return state;
    }

    public void setState(SearchUsersState state) {
        this.state = state;
    }
}
