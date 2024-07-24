package use_case.homescreen.interface_adapter;

import entity.user.User;
import use_case.homescreen.HomescreenInputBoundary;
import use_case.homescreen.HomescreenInputData;

public class HomescreenController {
    HomescreenInputBoundary homescreenInputBoundary;

    public HomescreenController(HomescreenInputBoundary homescreenInputBoundary) {
        this.homescreenInputBoundary = homescreenInputBoundary;
    }

    public void executeCreateEvent(User signedInAs) {
        HomescreenInputData homescreenInputData = new HomescreenInputData(signedInAs);
        homescreenInputBoundary.createEventClicked(homescreenInputData);
    }
}
