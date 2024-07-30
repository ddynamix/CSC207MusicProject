package use_case.eventscreen.interface_adapter;

import use_case.eventscreen.EventScreenInputBoundary;

public class EventScreenController {
    private final EventScreenInputBoundary eventScreenInteractor;

    public EventScreenController(EventScreenInputBoundary eventScreenInteractor) {
        this.eventScreenInteractor = eventScreenInteractor;
    }

    public void executeCreateEvent() {
        eventScreenInteractor.createEventClicked();
    }
}
