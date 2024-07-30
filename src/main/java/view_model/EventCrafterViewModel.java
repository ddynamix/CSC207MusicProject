package view_model;

import app.interface_adapter_tools.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class EventCrafterViewModel extends ViewModel {
    public final String TITLE_LABEL = "Create Your Event";
    public final String EVENT_NAME_LABEL = "Enter event name";
    public final String EVENT_DESCRIPTION_LABEL = "Enter event description";
    public final String EVENT_DATE_LABEL = "Enter event date (\"yyyy-MM-dd HH:mm\")";
    public final String EVENT_TAGS_LABEL = "Enter event tags (separated by \";\")";
    public final String EVENT_ATTACHED_MEDIA_LABEL = "Enter event attached media";
    public final String POST_EVENT_BUTTON_LABEL = "Post Event";
    public final String CANCEL_BUTTON_LABEL = "Cancel";

    private EventCrafterState state = new EventCrafterState();

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
