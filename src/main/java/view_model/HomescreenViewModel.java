package view_model;

import app.interface_adapter_tools.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * View Model for homescreen
 */
public class HomescreenViewModel extends ViewModel {
    public final String TITLE_LABEL = "Homescreen View";
    public final String EVENT_PAGE_BUTTON_LABEL = "My Events";
    public final String SIGN_OUT_BUTTON_LABEL = "Sign Out";
    public final String POST_BUTTON_LABEL = "Make a Post";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private HomescreenState state = new HomescreenState();

    /**
     * create instance of view model for homescreen
     */
    public HomescreenViewModel() {
        super("home");
    }

    /**
     * update state
     * @param state to be changed to
     */
    public void setState(HomescreenState state) {
        this.state = state;
    }

    /**
     * Observer action listener User input update
     */
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * @param listener stakeholder
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * access state
     * @return state
     */
    public HomescreenState getState() {
        return state;
    }
}
