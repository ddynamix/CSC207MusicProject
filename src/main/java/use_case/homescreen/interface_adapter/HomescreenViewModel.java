package use_case.homescreen.interface_adapter;

import app.interface_adapter_tools.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class HomescreenViewModel extends ViewModel {
    public final String TITLE_LABEL = "Homescreen View";
    public final String EVENT_PAGE_BUTTON_LABEL = "My Events";
    public final String SIGN_OUT_BUTTON_LABEL = "Sign Out";

    private HomescreenState state = new HomescreenState();

    public HomescreenViewModel() {
        super("home");
    }

    public void setState(HomescreenState state) {
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

    public HomescreenState getState() {
        return state;
    }
}
