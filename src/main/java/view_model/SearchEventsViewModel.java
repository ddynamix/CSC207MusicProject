package view_model;

import app.interface_adapter_tools.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * view model for search events use case
 */
public class SearchEventsViewModel extends ViewModel {
    public final String TITLE_LABEL = "Search Events";
    public final String BACK_BUTTON_LABEL = "Home";

    private SearchEventsState state = new SearchEventsState();

    /**
     * create instance of view model for search events use case
     */
    public SearchEventsViewModel() {
        super("search events");
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
    public SearchEventsState getState() {
        return state;
    }

    /**
     * change state
     * @param state to be set
     */
    public void setState(SearchEventsState state) {
        this.state = state;
    }
}
