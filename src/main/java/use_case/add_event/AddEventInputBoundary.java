package use_case.add_event;

public interface AddEventInputBoundary {
    void addEvent(AddEventInputData inputData);
    void removeEvent(AddEventInputData inputData);
}
