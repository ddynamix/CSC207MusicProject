package view_model;

import app.interface_adapter_tools.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import view_model.ProfileState;


/**
 * create viewmodels for profile screen
 */
public class ProfileViewModel extends ViewModel{
    public final String TITLE_LABEL = "Profile of: ";
    public final String USERNAME_LABEL = "Username: ";
    public final String NAME_LABEL = "Name: ";
    public final String PLAY_MUSIC_LABEL = "Play Music";
    public final String FAVOURITE_SONG_LABEL = "Favourite Song: ";
    public final String FOLLOWERS_LABEL = "Followers: ";
    public final String FOLLOWING_LABEL = "Following: ";
    public final String NO_PREVIEW_ERROR = "No preview available for this song";
    public final String NO_SONG_LABEL = "No favourite song";
    public final String STOP_MUSIC_LABEL = "Stop Music";
    public final String ADD_SONG_LABEL = "Add Song";

    private ProfileState state = new ProfileState();

    /**
     * create instance of view model
     */
    public ProfileViewModel() { super("profile");}

    /**
     * change state
     * @param state to be set
     */
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

    /**
     * access state
     * @return state
     */
    public ProfileState getState() {
        return state;
    }
}
