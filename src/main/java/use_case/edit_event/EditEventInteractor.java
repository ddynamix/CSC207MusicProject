package use_case.edit_event;

import data_access.EventDataAccessInterface;
import data_access.EventDoesntExistException;

public class EditEventInteractor implements EditEventInputBoundary {
    private final EditEventOutputBoundary editEventPresenter;
    private final EventDataAccessInterface eventDataAccessInterface;

    public EditEventInteractor(EditEventOutputBoundary editEventPresenter, EventDataAccessInterface eventDataAccessInterface) {
        this.editEventPresenter = editEventPresenter;
        this.eventDataAccessInterface = eventDataAccessInterface;
    }

    @Override
    public void editEvent(EditEventInputData inputData) {
        EditEventOutputData outputData = new EditEventOutputData(
                inputData.getEventToAlter().getTitle(),
                inputData.getEventToAlter().getDescription(),
                inputData.getEventToAlter().getDateAndTimeString(),
                inputData.getEventToAlter().getTagsString(),
                inputData.getEventToAlter().getAttachedMedia());
        editEventPresenter.goToEventEditor(outputData);
    }

    @Override
    public void deleteEvent(EditEventInputData inputData) {
        try {
            eventDataAccessInterface.deleteEvent(inputData.getEventToAlter());
        } catch (EventDoesntExistException e) {
            e.printStackTrace();
        }
    }
}
