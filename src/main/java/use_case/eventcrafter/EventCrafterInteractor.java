package use_case.eventcrafter;

import app.interface_adapter_tools.UserSession;
import data_access.EventAlreadyExistsException;
import data_access.EventDataAccessInterface;
import entity.event.Event;

import java.util.ArrayList;

/**
 * interactor for event use case
 */
public class EventCrafterInteractor implements EventCrafterInputBoundary {
    final EventDataAccessInterface eventDataAccessInterface;
    final EventCrafterOutputBoundary eventCrafterPresenter;

    /**
     * create instance of interactor for event use case
     * @param eventDataAccessInterface event DAO
     * @param eventCrafterPresenter present for event use case
     */
    public EventCrafterInteractor(EventDataAccessInterface eventDataAccessInterface,
                                  EventCrafterOutputBoundary eventCrafterPresenter) {
        this.eventDataAccessInterface = eventDataAccessInterface;
        this.eventCrafterPresenter = eventCrafterPresenter;
    }

    /**
     * attempt to save post and continue actions
     * @param eventCrafterInputData data to be processed
     */
    @Override
    public void attemptPostEvent(EventCrafterInputData eventCrafterInputData) {
        try {
            Event event = new Event(eventCrafterInputData.getTitle(),
                    eventCrafterInputData.getArtist(),
                    eventCrafterInputData.getVenue(),
                    eventCrafterInputData.getDateAndTime(),
                    eventCrafterInputData.getDescription(),
                    eventCrafterInputData.getTags(),
                    eventCrafterInputData.getPostDate(),
                    eventCrafterInputData.getAttachedMedia());
            eventDataAccessInterface.createEvent(event);

            ArrayList<Event> events = UserSession.getInstance().getLoggedInUser().getMyEvents();
            EventCrafterOutputData eventCrafterOutputData = new EventCrafterOutputData(events);

            eventCrafterPresenter.prepareSuccessView(eventCrafterOutputData);
            System.out.println("Event posted successfully!");
        } catch (EventAlreadyExistsException e) {
            eventCrafterPresenter.prepareFailView("Event already exists.");
        }
    }
}
