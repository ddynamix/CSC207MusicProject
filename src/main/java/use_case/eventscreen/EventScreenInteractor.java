package use_case.eventscreen;

import app.interface_adapter_tools.UserSession;
import data_access.UserDataAccessInterface;
import entity.event.Event;
import entity.user.ArtistUser;
import entity.user.User;
import entity.user.VenueUser;

import java.util.ArrayList;

public class EventScreenInteractor implements EventScreenInputBoundary {
    private final EventScreenOutputBoundary eventScreenPresenter;
    private final UserDataAccessInterface userDataAccessInterface;

    public EventScreenInteractor(EventScreenOutputBoundary eventScreenPresenter, UserDataAccessInterface userDataAccessInterface) {
        this.eventScreenPresenter = eventScreenPresenter;
        this.userDataAccessInterface = userDataAccessInterface;
    }

    @Override
    public void createEventClicked() {
        User loggedInUser = UserSession.getInstance().getLoggedInUser();
        ArrayList<ArtistUser> artistUsers = userDataAccessInterface.getArtistUsers();
        ArrayList<VenueUser> venueUsers = userDataAccessInterface.getVenueUsers();
        eventScreenPresenter.prepareCreateEventView(new EventScreenOutputData(loggedInUser, artistUsers, venueUsers));
    }

    @Override
    public void cancel() {
        eventScreenPresenter.prepareHomescreenView();
    }

    // unused method unless i find a way to call this when the view is switched to the event screen
    @Override
    public void updateEvents() {
        ArrayList<Event> myEvents = UserSession.getInstance().getLoggedInUser().getMyEvents();
        eventScreenPresenter.updateEvents(new EventScreenGetEventOutputData(myEvents));
    }
}
