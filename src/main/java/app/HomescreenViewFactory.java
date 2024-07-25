package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.homescreen.HomescreenController;
import interface_adapter.homescreen.HomescreenPresenter;
import interface_adapter.homescreen.HomescreenViewModel;
import view.HomescreenView;

public class HomescreenViewFactory {
    private HomescreenViewFactory() {
    }

    public static HomescreenView createHomescreenView(ViewManagerModel viewManagerModel, HomescreenViewModel homescreenViewModel) {
        HomescreenController homescreenController = new HomescreenController();

        HomescreenPresenter homescreenPresenter = new HomescreenPresenter(viewManagerModel, homescreenViewModel);
        return new HomescreenView(homescreenViewModel, homescreenController, homescreenPresenter);
    }
}
