package app;

import interface_adapter.ViewModel;

import java.util.HashMap;

public interface ViewCreatorInterface {
    void createAllViews(HashMap<String, ViewModel> viewModels, HashMap<String, Object> dataAccessObjects);
    void run();
}
