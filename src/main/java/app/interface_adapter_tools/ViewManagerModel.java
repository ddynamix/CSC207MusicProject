package app.interface_adapter_tools;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Controller of views
 */
public class ViewManagerModel {

    private String activeViewName;

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Return view
     * @return String activeViewName
     */
    public String getActiveView() {
        return activeViewName;
    }

    /**
     * Set view
     * @param activeView to be set
     */
    public void setActiveView(String activeView) {
        this.activeViewName = activeView;
    }

    /**
     * Update listeners
     * Note: Observable Design Pattern
     */
    public void firePropertyChanged() {
        support.firePropertyChange("view", null, this.activeViewName);
    }

    /**
     * Add listener
     * Note: Observable Design Pattern
     * @param listener  stakeholder
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
