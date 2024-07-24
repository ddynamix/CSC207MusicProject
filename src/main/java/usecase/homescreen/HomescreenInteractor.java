package usecase.homescreen;

import dataaccess.EventDataAccessInterface;
import dataaccess.UserDataAccessInterface;
import entity.event.Event;
import usecase.eventcrafter.EventCrafterOutputData;

import java.util.ArrayList;
import java.util.Collection;
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

    private ArrayList<Event> getEventsAsList() {
        Map<String, Event> eventsMap = eventDataAccessInterface.getEvents();
        ArrayList<Event> eventsList = new ArrayList<>(eventsMap.values());
        return eventsList;
    }
}
