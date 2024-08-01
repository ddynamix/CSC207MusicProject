package use_case.add_event.interface_adapter;

import entity.event.Event;
import use_case.add_event.AddEventInputBoundary;
import use_case.add_event.AddEventInputData;

public class AddEventController {
    private final AddEventInputBoundary addEventInteractor;

    public AddEventController(AddEventInputBoundary addEventInteractor) {
        this.addEventInteractor = addEventInteractor;
    }

    public void addEvent(Event event) {
        AddEventInputData inputData = new AddEventInputData(event);
        addEventInteractor.addEvent(inputData);
    }

    public void removeEvent(Event event) {
        AddEventInputData inputData = new AddEventInputData(event);
        addEventInteractor.removeEvent(inputData);
    }
}
