package view_model;

import app.interface_adapter_tools.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class IsFollowingViewModel extends ViewModel {
    public final String TITLE_LABEL = "Following";
    public final String HOME_BUTTON_LABEL = "Home";

    private IsFollowingState state = new IsFollowingState();

    /**
     * Create instance
     */
    public IsFollowingViewModel() {
        super("is following");
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

    public IsFollowingState getState() {
        return state;
    }

    public void setState(IsFollowingState state) {
        this.state = state;
    }
}
