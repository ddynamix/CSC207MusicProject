package view_model;

import app.interface_adapter_tools.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * create view models for user editor use case
 */
public class UserEditorViewModel extends ViewModel {
    public final String TITLE_LABEL = "Edit your user:";
    public final String CANCEL_BUTTON_LABEL = "Cancel";
    public final String PUBLISH_BUTTON_LABEL = "Publish";
    public final String EDIT_POST_TITLE_LABEL = "Edit user title";
    public final String EDIT_POST_TEXT_LABEL = "Edit user content";
    public final String EDIT_POST_ATTACHED_MEDIA_LABEL = "Edit user attached media";

    private UserEditorState state = new UserEditorState();

    /**
     * create instance of view model for user ediotr use case
     */
    public UserEditorViewModel() {
        super("user editor");
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
    public UserEditorState getState() {
        return state;
    }

    /**
     * change state
     * @param state to be set
     */
    public void setState(UserEditorState state) {
        this.state = state;
    }
}
