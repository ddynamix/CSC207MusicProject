package interface_adapter.splash;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;

/**
 * Model for splash view
 */
public class SplashViewModel extends ViewModel {

    public final String TITLE_LABEL = "Login or Sign Up";
    public final String SIGN_UP_BUTTON_LABEL = "Sign Up";
    public final String LOGIN_BUTTON_LABEL = "Log in";

    /**
     * create instance
     */
    public SplashViewModel() {
        super("splash");
    }

    /**
     * update listeners
     * Note: Observable Design Pattern
     */
    @Override
    public void firePropertyChanged() {

    }

    /**
     * Add listener
     * Note: Observable Design Pattern
     * @param listener
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }
}
