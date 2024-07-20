package interface_adapter.signupselector;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;

public class SignupSelectorViewModel extends ViewModel {
    public final String TITLE_LABEL = "Select Account Type";
    public final String AUDIENCE_BUTTON_LABEL = "Sign Up as Audience";
    public final String ARTIST_BUTTON_LABEL = "Sign Up as Artist";
    public final String VENUE_BUTTON_LABEL = "Sign Up as Venue";
    public final String CANCEL_BUTTON_LABEL = "Cancel";

    public SignupSelectorViewModel() {
        super("signup selection");
    }

    @Override
    public void firePropertyChanged() {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }
}
