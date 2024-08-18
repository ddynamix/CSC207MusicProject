package view_model;

import app.interface_adapter_tools.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * create view models for profile editor use case
 */
public class ProfileEditViewModel extends ViewModel {
    public final String TITLE_LABEL = "Edit your profile:";
    public final String CANCEL_BUTTON_LABEL = "Cancel";
    public final String PUBLISH_BUTTON_LABEL = "Publish";
    public final String EDIT_USER_USERNAME_LABEL = "Edit username";
    public final String EDIT_USER_EMAIL_LABEL = "Edit email";
    public final String EDIT_USER_NAME_LABEL = "Edit name";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private ProfileEditorState state = new ProfileEditorState();

    /**
     * create instance of view model for profile editor use case
     */
    public ProfileEditViewModel() {super("profile editor");}

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * access state
     * @return state
     */
    public ProfileEditorState getState() {
        return state;
    }

    /**
     * change state
     * @param state to be set
     */
    public void setState(ProfileEditorState state) {
        this.state = state;
    }
}
