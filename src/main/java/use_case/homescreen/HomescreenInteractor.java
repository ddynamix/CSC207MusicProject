package use_case.homescreen;

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

        homescreenPresenter.updateEvents(new HomescreenGetEventOutputData(getEventsAsList()));
    }

    @Override
    public void createEventClicked(HomescreenInputData homescreenInputData) {
        HomescreenOutputData homescreenOutputData = new HomescreenOutputData(homescreenInputData.getSignedInAs(), userDataAccessInterface.getArtistUsers(), userDataAccessInterface.getVenueUsers());
        homescreenPresenter.prepareCreateEventView(homescreenOutputData);
    }

    @Override
    public void signOut() {
        homescreenPresenter.prepareSplashView();
    }

    private ArrayList<Event> getEventsAsList() {
        Map<String, Event> eventsMap = eventDataAccessInterface.getEvents();
        ArrayList<Event> eventsList = new ArrayList<>(eventsMap.values());
        return eventsList;
    }
}
