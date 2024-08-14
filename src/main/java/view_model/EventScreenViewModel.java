package view_model;

import app.interface_adapter_tools.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * view model for event screen
 */
public class EventScreenViewModel extends ViewModel {
    public final String TITLE_LABEL = "Your Events";
    public final String CREATE_EVENT_BUTTON_LABEL = "Create Event";
    public final String BACK_BUTTON_LABEL = "Back";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public EventScreenState state = new EventScreenState();

    /**
     * create new instance of model
     */
    public EventScreenViewModel() {
        super("event screen");
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
     * @return current state
     */
    public EventScreenState getState() {
        return state;
    }

    /**
     * update state
     * @param state new state
     */
    public void setState(EventScreenState state) {
        this.state = state;
    }
}
