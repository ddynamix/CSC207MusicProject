package use_case.eventcrafter;

import data_access.EventAlreadyExistsException;
import data_access.EventDataAccessInterface;
import entity.event.Event;

import java.util.ArrayList;

public class EventCrafterInteractor implements EventCrafterInputBoundary {
    final EventDataAccessInterface eventDataAccessInterface;
    final EventCrafterOutputBoundary eventCrafterPresenter;

    public EventCrafterInteractor(EventDataAccessInterface eventDataAccessInterface, EventCrafterOutputBoundary eventCrafterPresenter) {
        this.eventDataAccessInterface = eventDataAccessInterface;
        this.eventCrafterPresenter = eventCrafterPresenter;
    }

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

            ArrayList<Event> events = eventDataAccessInterface.getEvents();
            EventCrafterOutputData eventCrafterOutputData = new EventCrafterOutputData(events);

            eventCrafterPresenter.prepareSuccessView(eventCrafterOutputData);
            System.out.println("Event posted successfully!");
        } catch (EventAlreadyExistsException e) {
            eventCrafterPresenter.prepareFailView("Event already exists.");
        }
    }
}
