package use_case.add_event.interface_adapter;

import entity.event.Event;
import use_case.add_event.AddEventInputBoundary;
import use_case.add_event.AddEventInputData;

/**
 * controller for add event use case
 */
public class AddEventController {
    private final AddEventInputBoundary addEventInteractor;

    /**
     * create instance of controller
     * @param addEventInteractor interactor for add event use case
     */
    public AddEventController(AddEventInputBoundary addEventInteractor) {
        this.addEventInteractor = addEventInteractor;
    }

    /**
     * add event to input data
     * @param event to be added
     */
    public void addEvent(Event event) {
        AddEventInputData inputData = new AddEventInputData(event);
        addEventInteractor.addEvent(inputData);
    }

    /**
     * remove event from input data
     * @param event to be removed
     */
    public void removeEvent(Event event) {
        AddEventInputData inputData = new AddEventInputData(event);
        addEventInteractor.removeEvent(inputData);
    }
}
