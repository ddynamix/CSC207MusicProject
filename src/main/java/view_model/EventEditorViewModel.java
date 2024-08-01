package view_model;

import app.interface_adapter_tools.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class EventEditorViewModel extends ViewModel {
    public final String TITLE_LABEL = "Edit your event:";
    public final String CANCEL_BUTTON_LABEL = "Cancel";
    public final String PUBLISH_BUTTON_LABEL = "Publish";
    public final String EDIT_EVENT_TITLE_LABEL = "Edit event title";
    public final String EDIT_EVENT_DESCRIPTION_LABEL = "Edit event description";
    public final String EDIT_EVENT_DATE_LABEL = "Edit event date (\"yyyy-MM-dd HH:mm\")";
    public final String EDIT_EVENT_TAGS_LABEL = "Edit event tags (separated by \";\")";
    public final String EDIT_EVENT_ATTACHED_MEDIA_LABEL = "Edit event attached media";

    private EventEditorState state = new EventEditorState();

    public EventEditorViewModel() {
        super("event editor");
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

    public EventEditorState getState() {
        return state;
    }

    public void setState(EventEditorState state) {
        this.state = state;
    }
}
