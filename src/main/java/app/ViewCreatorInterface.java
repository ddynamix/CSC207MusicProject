package app;

import app.interface_adapter_tools.ViewModel;

import java.util.HashMap;

public interface ViewCreatorInterface {
    void createAllViews(HashMap<String, ViewModel> viewModels, HashMap<String, Object> dataAccessObjects);
    void run();
}
