package use_case.homescreen.interface_adapter;

import entity.user.User;
import use_case.homescreen.HomescreenInputBoundary;
import use_case.homescreen.HomescreenInputData;

public class HomescreenController {
    HomescreenInputBoundary homescreenInteractor;

    public HomescreenController(HomescreenInputBoundary homescreenInteractor) {
        this.homescreenInteractor = homescreenInteractor;
    }

    public void executeCreateEvent(User signedInAs) {
        HomescreenInputData homescreenInputData = new HomescreenInputData(signedInAs);
        homescreenInteractor.createEventClicked(homescreenInputData);
    }

    public void executeSignOut() {
        homescreenInteractor.signOut();
    }
}
