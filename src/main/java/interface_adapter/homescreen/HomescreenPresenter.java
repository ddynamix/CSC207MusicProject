package interface_adapter.homescreen;

import interface_adapter.ViewManagerModel;
import interface_adapter.eventcrafter.EventCrafterState;
import interface_adapter.eventcrafter.EventCrafterViewModel;
import usecase.homescreen.HomescreenOutputBoundary;
import usecase.homescreen.HomescreenOutputData;

public class HomescreenPresenter implements HomescreenOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final HomescreenViewModel eventCrafterViewModel;

    public HomescreenPresenter(ViewManagerModel viewManagerModel, HomescreenViewModel eventCrafterViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.eventCrafterViewModel = eventCrafterViewModel;
    }

    @Override
    public void prepareCreateEventView(HomescreenOutputData homescreenOutputData) {
        HomescreenState eventCrafterState = eventCrafterViewModel.getState();
        eventCrafterState.setSignedInAs(homescreenOutputData.getSignedInAs());

        eventCrafterViewModel.setState(eventCrafterState);
        eventCrafterViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(eventCrafterViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
