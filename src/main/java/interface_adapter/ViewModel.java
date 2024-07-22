package interface_adapter;

import java.beans.PropertyChangeListener;

/**
 * View Model Super Class
 */
public abstract class ViewModel {

    private String viewName;

    /**
     * Create instance
     * @param viewName  String title
     */
    public ViewModel(String viewName) {
        this.viewName = viewName;
    }

    /**
     * Return name
     * @return String viewName
     */
    public String getViewName() {
        return this.viewName;
    }

    /**
     * Update listeners
     * Note: Observable Design Pattern
     */
    public abstract void firePropertyChanged();

    /**
     * Add listener
     * Note: Observable Design Pattern
     * @param listener  stakeholder
     */
    public abstract void addPropertyChangeListener(PropertyChangeListener listener);
}