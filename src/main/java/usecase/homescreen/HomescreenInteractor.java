package usecase.homescreen;

import entity.user.User;

public class HomescreenInteractor implements HomescreenInputBoundary {
    private final HomescreenOutputBoundary homescreenPresenter;

    public HomescreenInteractor(HomescreenOutputBoundary homescreenPresenter) {
        this.homescreenPresenter = homescreenPresenter;
    }

    @Override
    public void createEventClicked(HomescreenInputData homescreenInputData) {
        HomescreenOutputData homescreenOutputData = new HomescreenOutputData(homescreenInputData.getSignedInAs());
        homescreenPresenter.prepareCreateEventView(homescreenOutputData);
    }
}
