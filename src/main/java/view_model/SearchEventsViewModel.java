package view_model;

import app.interface_adapter_tools.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SearchEventsViewModel extends ViewModel {
    public final String TITLE_LABEL = "Search Events";
    public final String BACK_BUTTON_LABEL = "Home";

    private SearchEventsState state = new SearchEventsState();

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

    public SearchEventsState getState() {
        return state;
    }

    public void setState(SearchEventsState state) {
        this.state = state;
    }
}
