package use_case.usersignup.interface_adapter;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * ViewModel instance for user signup
 */
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

    /**
     * Create instance of view model
     * Super with parameter for title
     */
    public UserSignupViewModel() {
        super("user sign up");
    }

    /**
     * Set type of user
     * @param type String
     */
    public void setType(String type){
        type = type;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Update all listeners of the changes made
     * Note: Observable Design Pattern
     */
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Add a listener to the ViewModel to be notified about changes
     * @param listener PropertyChangeListener entity/component to be updated
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Return state of viewmodel
     * @return UserSignupState
     */
    public UserSignupState getState() {
        return state;
    }

    /**
     * Set the state of viewmodel
     * @param state UserSignupState
     */
    public void setState(UserSignupState state) {
        this.state = state;
    }
}
