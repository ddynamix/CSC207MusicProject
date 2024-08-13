package use_case.add_event;

import app.interface_adapter_tools.UserSession;
import data_access.UsersEventsRelationalAccessInterface;
import entity.user.User;

/**
 * Create interactor
 */
public class AddEventInteractor implements AddEventInputBoundary {
    private final AddEventOutputBoundary addEventPresenter;
    private final UsersEventsRelationalAccessInterface usersEventsRelationalAccessInterface;

    /**
     * create instance of interactor for add event use case
     * @param addEventPresenter presenter for add event use case
     * @param usersEventsRelationalAccessInterface DAO user -> event
     */
    public AddEventInteractor(AddEventOutputBoundary addEventPresenter, UsersEventsRelationalAccessInterface usersEventsRelationalAccessInterface) {
        this.usersEventsRelationalAccessInterface = usersEventsRelationalAccessInterface;
        this.addEventPresenter = addEventPresenter;
    }

    /**
     * add event to data
     * @param inputData new data
     */
    @Override
    public void addEvent(AddEventInputData inputData) {
        User loggedIn = UserSession.getInstance().getLoggedInUser();
        usersEventsRelationalAccessInterface.addEvent(loggedIn, inputData.getEventToAddOrRemove());
    }

    /**
     * remove event from data
     * @param inputData new data
     */
    @Override
    public void removeEvent(AddEventInputData inputData) {
        User loggedIn = UserSession.getInstance().getLoggedInUser();
        usersEventsRelationalAccessInterface.removeEvent(loggedIn, inputData.getEventToAddOrRemove());
        addEventPresenter.updateEventsView(new AddEventOutputData(UserSession.getInstance().getLoggedInUser().getMyEvents()));
    }
}
