package interface_adapter.artistsignup;

import interface_adapter.ViewModel;
import interface_adapter.UserSignupState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ArtistSignupViewModel extends ViewModel {

    public final String TITLE_LABEL = "Artist Sign Up";
    public final String USERNAME_LABEL = "Choose username";
    public final String PASSWORD_LABEL = "Choose password";
    public final String REPEAT_PASSWORD_LABEL = "Enter password again";
    public final String EMAIL_LABEL = "Enter email";
    public final String STAGE_NAME_LABEL = "Enter stage name";

    public final String SIGNUP_BUTTON_LABEL = "Sign up as Artist";
    public final String CANCEL_BUTTON_LABEL = "Cancel";

    private UserSignupState state = new UserSignupState();

    public ArtistSignupViewModel() {
        super("artist sign up");
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public UserSignupState getState() {
        return state;
    }

    public void setState(UserSignupState state) {
        this.state = state;
    }
}
