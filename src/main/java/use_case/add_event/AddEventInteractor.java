package use_case.add_event;

import app.interface_adapter_tools.UserSession;
import data_access.UsersEventsRelationalAccessInterface;
import entity.user.User;

public class AddEventInteractor implements AddEventInputBoundary {
    private final UsersEventsRelationalAccessInterface usersEventsRelationalAccessInterface;

    public AddEventInteractor(UsersEventsRelationalAccessInterface usersEventsRelationalAccessInterface) {
        this.usersEventsRelationalAccessInterface = usersEventsRelationalAccessInterface;
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
    }
}
