package usecase.usersignup;

import dataaccess.UserDataAccessInterface;
import entity.user.User;
import entity.user.UserFactory;

import java.time.LocalDateTime;

public class UserSignupInteractor implements UserSignupInputBoundary {
    final UserDataAccessInterface userDataAccessObject;
    final UserSignupOutputBoundary userPresenter;
    final UserFactory userFactory;

    public UserSignupInteractor(UserDataAccessInterface userSignupDataAccessInterface,
                            UserSignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory) {
        this.userDataAccessObject = userSignupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(UserSignupInputData signupInputData) {
        if (userDataAccessObject.userExistsInDatabase(signupInputData.getUsername())) {
            userPresenter.prepareFailView("User already exists.");
        } else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPass())) {
            userPresenter.prepareFailView("Passwords don't match.");
        } else {
            User user = userFactory.createUser(signupInputData.getUsername(), signupInputData.getPassword(), signupInputData.getEmail(), signupInputData.getFirstName(), signupInputData.getLastName());
            userDataAccessObject.create(user);

            LocalDateTime now = LocalDateTime.now();
            UserSignupOutputData signupOutputData = new UserSignupOutputData(user.getUsername(), now.toString(), false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }
}