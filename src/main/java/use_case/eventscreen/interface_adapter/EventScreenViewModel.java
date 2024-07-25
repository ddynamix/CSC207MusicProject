package use_case.eventscreen.interface_adapter;

import app.interface_adapter_tools.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class EventScreenViewModel extends ViewModel {
    public final String TITLE_LABEL = "Your Events";
    public final String CREATE_EVENT_BUTTON_LABEL = "Create Event";
    public final String BACK_BUTTON_LABEL = "Back";

    public EventScreenState state = new EventScreenState();

    public EventScreenViewModel() {
        super("event screen");
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

    public EventScreenState getState() {
        return state;
    }

    public void setState(EventScreenState state) {
        this.state = state;
    }
}
