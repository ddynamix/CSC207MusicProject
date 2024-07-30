package view_model;

import app.interface_adapter_tools.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MyFollowersViewModel extends ViewModel {
    public final String TITLE_LABEL = "My Followers";
    public final String HOME_BUTTON_LABEL = "Home";

    private MyFollowersState state = new MyFollowersState();

    /**
     * Create instance
     */
    public MyFollowersViewModel() {
        super("my followers");
    }

    @Override
    public void firePropertyChanged() {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public MyFollowersState getState() {
        return state;
    }

    public void setState(MyFollowersState state) {
        this.state = state;
    }
}
