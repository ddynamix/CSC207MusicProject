package use_case.add_event;

import app.interface_adapter_tools.UserSession;
import data_access.UsersEventsRelationalAccessInterface;
import entity.user.User;

public class AddEventInteractor implements AddEventInputBoundary {
    private final AddEventOutputBoundary addEventPresenter;
    private final UsersEventsRelationalAccessInterface usersEventsRelationalAccessInterface;

    public AddEventInteractor(AddEventOutputBoundary addEventPresenter, UsersEventsRelationalAccessInterface usersEventsRelationalAccessInterface) {
        this.usersEventsRelationalAccessInterface = usersEventsRelationalAccessInterface;
        this.addEventPresenter = addEventPresenter;
    }

    @Override
    public void addEvent(AddEventInputData inputData) {
        User loggedIn = UserSession.getInstance().getLoggedInUser();
        usersEventsRelationalAccessInterface.addEvent(loggedIn, inputData.getEventToAddOrRemove());
    }

    @Override
    public void removeEvent(AddEventInputData inputData) {
        User loggedIn = UserSession.getInstance().getLoggedInUser();
        usersEventsRelationalAccessInterface.removeEvent(loggedIn, inputData.getEventToAddOrRemove());
        addEventPresenter.updateEventsView(new AddEventOutputData(UserSession.getInstance().getLoggedInUser().getMyEvents()));
    }
}
