package use_case.homescreen;

import app.interface_adapter_tools.UserSession;
import data_access.EventDataAccessInterface;
import data_access.UserDataAccessInterface;
import entity.event.Event;

import java.util.ArrayList;
import java.util.Map;

public class HomescreenInteractor implements HomescreenInputBoundary {
    private final HomescreenOutputBoundary homescreenPresenter;
    private final UserDataAccessInterface userDataAccessInterface;
    private final EventDataAccessInterface eventDataAccessInterface;

    public HomescreenInteractor(HomescreenOutputBoundary homescreenPresenter, UserDataAccessInterface userDataAccessInterface, EventDataAccessInterface eventDataAccessInterface) {
        this.homescreenPresenter = homescreenPresenter;
        this.userDataAccessInterface = userDataAccessInterface;
        this.eventDataAccessInterface = eventDataAccessInterface;
    }

    @Override
    public void eventPageClicked() {
        ArrayList<Event> myEvents = UserSession.getInstance().getLoggedInUser().getMyEvents();
        homescreenPresenter.prepareEventPageView(new HomescreenOutputData(myEvents));
    }

    @Override
    public void signOut() {
        homescreenPresenter.prepareSplashView();
    }
}
