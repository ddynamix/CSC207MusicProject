package interface_adapter.homescreen;

import entity.user.User;
import usecase.homescreen.HomescreenInputBoundary;
import usecase.homescreen.HomescreenInputData;

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
