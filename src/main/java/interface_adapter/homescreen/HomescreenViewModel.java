package interface_adapter.homescreen;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class HomescreenViewModel extends ViewModel {
    public final String TITLE_LABEL = "Homescreen View";
    public final String CREATE_EVENT_BUTTON_LABEL = "Create Event";

    private HomescreenState state = new HomescreenState();

    public HomescreenViewModel() {
        super("home");
    }

    public void setState(HomescreenState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public HomescreenState getState() {
        return state;
    }
}