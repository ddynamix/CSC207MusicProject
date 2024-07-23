package interface_adapter.eventcrafter;

import interface_adapter.ViewModel;
import interface_adapter.homescreen.HomescreenState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class EventCrafterViewModel extends ViewModel {
    public final String TITLE_LABEL = "Event Crafter View";
    public final String EVENT_NAME_LABEL = "Enter event name";
    public final String EVENT_DESCRIPTION_LABEL = "Enter event description";
    public final String EVENT_DATE_LABEL = "Enter event date";
    public final String EVENT_LOCATION_LABEL = "Enter event location";

    private EventCrafterState state = new EventCrafterState(null);

    public EventCrafterViewModel() {
        super("eventcrafter view");
    }

    public void setState(EventCrafterState state) {
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

    public EventCrafterState getState() {
        return state;
    }
}
