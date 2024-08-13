package use_case.add_event;

/**
 * interface for interactor
 */
public interface AddEventInputBoundary {
    void addEvent(AddEventInputData inputData);
    void removeEvent(AddEventInputData inputData);
}
