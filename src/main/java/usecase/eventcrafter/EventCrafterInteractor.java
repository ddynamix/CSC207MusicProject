package usecase.eventcrafter;

import dataaccess.EventAlreadyExistsException;
import dataaccess.EventDataAccessInterface;
import entity.event.Event;

import java.util.ArrayList;
import java.util.Collection;

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
            System.out.println(eventCrafterInputData.getArtist().getUsername());

            Event event = new Event(eventCrafterInputData.getTitle(),
                    eventCrafterInputData.getArtist(),
                    eventCrafterInputData.getVenue(),
                    eventCrafterInputData.getDateAndTime(),
                    eventCrafterInputData.getDescription(),
                    eventCrafterInputData.getTags(),
                    eventCrafterInputData.getPostDate(),
                    eventCrafterInputData.getAttachedMedia());
            eventDataAccessInterface.createEvent(event);

            Collection<Event> values = eventDataAccessInterface.getEvents().values();
            ArrayList<Event> listOfEvents = new ArrayList<>(values);
            EventCrafterOutputData eventCrafterOutputData = new EventCrafterOutputData(listOfEvents);

            eventCrafterPresenter.prepareSuccessView(eventCrafterOutputData);
            System.out.println("Event posted successfully!");
        } catch (EventAlreadyExistsException e) {
            eventCrafterPresenter.prepareFailView("Event already exists.");
        }
    }

    @Override
    public void switchToHomescreen() {
        eventCrafterPresenter.prepareSuccessView(new EventCrafterOutputData(new ArrayList<>()));
    }
}
