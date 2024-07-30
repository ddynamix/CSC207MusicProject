package app;

import app.interface_adapter_tools.ViewManagerModel;
import app.interface_adapter_tools.ViewModel;

import javax.swing.*;
import java.util.HashMap;

public interface ViewCreatorInterface {
    void createAllViews(JPanel views, ViewManagerModel viewManagerModel, HashMap<String, ViewModel> viewModels, HashMap<String, Object> controllers, HashMap<String, Object> dataAccessObjects);
}
