package use_case.edit_event;

import app.interface_adapter_tools.UserSession;
import data_access.EventDataAccessInterface;
import data_access.EventDoesntExistException;
import data_access.UsersEventsRelationalAccessInterface;
import entity.event.Event;

import java.util.ArrayList;

/**
 * interactor for edit event use case
 */
public class EditEventInteractor implements EditEventInputBoundary {
    private final EditEventOutputBoundary editEventPresenter;
    private final EventDataAccessInterface eventDataAccessInterface;
    private final UsersEventsRelationalAccessInterface usersEventsRelationalAccessInterface;

    /**
     * create interactor for edit event use case
     * @param editEventPresenter presenter for edit event use case
     * @param eventDataAccessInterface event DAO
     * @param usersEventsRelationalAccessInterface user -> event DAO
     */
    public EditEventInteractor(EditEventOutputBoundary editEventPresenter, EventDataAccessInterface eventDataAccessInterface, UsersEventsRelationalAccessInterface usersEventsRelationalAccessInterface) {
        this.editEventPresenter = editEventPresenter;
        this.eventDataAccessInterface = eventDataAccessInterface;
        this.usersEventsRelationalAccessInterface = usersEventsRelationalAccessInterface;
    }

    /**
     * update event
     * @param inputData new data
     */
    @Override
    public void editEvent(EditEventInputData inputData) {
        EditEventOutputData outputData = new EditEventOutputData(inputData.getEventToAlter());
        editEventPresenter.goToEventEditor(outputData);
    }

    /**
     * remove event
     * @param inputData new data
     */
    @Override
    public void deleteEvent(EditEventInputData inputData) {
        try {
            eventDataAccessInterface.deleteEvent(inputData.getEventToAlter());
            usersEventsRelationalAccessInterface.removeEvent(UserSession.getInstance().getLoggedInUser(), inputData.getEventToAlter());
            ArrayList<Event> updatedEvents = UserSession.getInstance().getLoggedInUser().getMyEvents();
            editEventPresenter.prepareSuccessView(new EditEventsSuccessOutputData(updatedEvents));
        } catch (EventDoesntExistException e) {
            e.printStackTrace();
        }
    }

    /**
     * update data
     * @param inputData new data
     */
    @Override
    public void updateEvent(EditEventInputData inputData) {
        try {
            eventDataAccessInterface.updateEvent(inputData.getEventToAlter(), inputData.getTitle(),
                    inputData.getDescription(), inputData.getDate(), inputData.getTags(), inputData.getMedia());
            ArrayList<Event> updatedEvents = UserSession.getInstance().getLoggedInUser().getMyEvents();
            editEventPresenter.prepareSuccessView(new EditEventsSuccessOutputData(updatedEvents));
        } catch (EventDoesntExistException e) {
            e.printStackTrace();
        }
    }
}
