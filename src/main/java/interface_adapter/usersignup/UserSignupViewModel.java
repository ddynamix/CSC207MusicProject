package interface_adapter.usersignup;

import interface_adapter.ViewModel;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class UserSignupViewModel extends ViewModel {
    public final String AUDIENCE_BUTTON_LABEL = "Sign Up as Audience";
    public final String ARTIST_BUTTON_LABEL = "Sign Up as Artist";
    public final String VENUE_BUTTON_LABEL = "Sign Up as Venue";


    public final String TITLE_LABEL = "Audience Sign Up";
    public final String USERNAME_LABEL = "Choose username";
    public final String PASSWORD_LABEL = "Choose password";
    public final String REPEAT_PASSWORD_LABEL = "Enter password again";
    public final String EMAIL_LABEL = "Enter email";
    public final String NAME_LABEL = "Enter name";

    public final String SIGNUP_BUTTON_LABEL = "Sign up";
    public final String CANCEL_BUTTON_LABEL = "Cancel";

    private UserSignupState state = new UserSignupState();

    public UserSignupViewModel() {
        super("user sign up");
    }

    public void setType(String type){
        type = type;
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