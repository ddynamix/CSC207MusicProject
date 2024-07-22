package interface_adapter.eventcrafter;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;

public class EventCrafterViewModel extends ViewModel {
    /**
     * Create instance
     *
     * @param viewName String title
     */
    public EventCrafterViewModel(String viewName) {
        super(viewName);
    }

    @Override
    public void firePropertyChanged() {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }
}
