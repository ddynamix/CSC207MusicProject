package view_model;

import app.interface_adapter_tools.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import view_model.ProfileState;



public class ProfileViewModel extends ViewModel{
    public final String TITLE_LABEL = "Profile View";
    public final String USERNAME_LABEL = "Username:";
    public final String NAME_LABEL = "Name:";
    public final String EMAIL_LABEL = "Email:";

    private ProfileState state = new ProfileState();

    public ProfileViewModel() { super("Profile");}

    public void setState(ProfileState state) {
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

    public ProfileState getState() {
        return state;
    }
}
