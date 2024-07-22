package interface_adapter.homescreen;

import interface_adapter.ViewManagerModel;

public class HomescreenPresenter {
    private final ViewManagerModel viewManagerModel;
    private final HomescreenViewModel homescreenViewModel;

    public HomescreenPresenter(ViewManagerModel viewManagerModel, HomescreenViewModel homescreenViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.homescreenViewModel = homescreenViewModel;
    }

    public void updateHomescreen() {
        String viewName = homescreenViewModel.getViewName();
        viewManagerModel.setActiveView(viewName);
        viewManagerModel.firePropertyChanged();
    }
}
