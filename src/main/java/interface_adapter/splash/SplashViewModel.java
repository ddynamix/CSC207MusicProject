package interface_adapter.splash;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;

public class SplashViewModel extends ViewModel {

    public final String TITLE_LABEL = "Login or Sign Up";
    public final String SIGN_UP_BUTTON_LABEL = "Sign Up";
    public final String LOGIN_BUTTON_LABEL = "Log in";

    public SplashViewModel() {
        super("splash");
    }

    @Override
    public void firePropertyChanged() {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }
}
