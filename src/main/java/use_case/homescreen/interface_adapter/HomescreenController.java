package use_case.homescreen.interface_adapter;

import use_case.homescreen.HomescreenInputBoundary;

public class HomescreenController {
    HomescreenInputBoundary homescreenInteractor;

    public HomescreenController(HomescreenInputBoundary homescreenInteractor) {
        this.homescreenInteractor = homescreenInteractor;
    }

    public void executeEventPageClicked() {
        homescreenInteractor.eventPageClicked();
    }

    public void executeSignOut() {
        homescreenInteractor.signOut();
    }
}
