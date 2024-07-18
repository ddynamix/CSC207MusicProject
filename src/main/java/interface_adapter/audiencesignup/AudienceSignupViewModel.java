package interface_adapter.audiencesignup;

import interface_adapter.UserSignupState;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AudienceSignupViewModel extends ViewModel {

    public final String TITLE_LABEL = "Audience Sign Up";
    public final String USERNAME_LABEL = "Choose username";
    public final String PASSWORD_LABEL = "Choose password";
    public final String REPEAT_PASSWORD_LABEL = "Enter password again";
    public final String EMAIL_LABEL = "Enter email";
    public final String FIRST_NAME_LABEL = "Enter first name";
    public final String LAST_NAME_LABEL = "Enter last name";

    public final String SIGNUP_BUTTON_LABEL = "Sign up";
    public final String CANCEL_BUTTON_LABEL = "Cancel";

    private UserSignupState state = new UserSignupState();

    public AudienceSignupViewModel() {
        super("audience sign up");
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