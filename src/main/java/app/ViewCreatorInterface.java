package app;

import app.interface_adapter_tools.ViewManagerModel;
import app.interface_adapter_tools.ViewModel;

import javax.swing.*;
import java.util.HashMap;

/**
 * Interface for all views
 * Parent class of views is SwingViewCreator
 */
public interface ViewCreatorInterface {
    void createAllViews(JPanel views, ViewManagerModel viewManagerModel, HashMap<String, ViewModel> viewModels,
                        HashMap<String, Object> controllers, HashMap<String, Object> dataAccessObjects);
}
