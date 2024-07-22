package usecase.usersignup;

import dataaccess.UserDataAccessInterface;
import dataaccess.UserDataAccessObject;
import entity.user.ArtistUser;
import entity.user.AudienceUser;
import entity.user.User;
import entity.user.VenueUser;

import java.time.LocalDateTime;

public class UserSignupInteractor implements UserSignupInputBoundary{
    final UserDataAccessInterface userDataAccessInterface;
    final SignupOutputBoundary userPresenter;

    public UserSignupInteractor(UserDataAccessInterface userSignupDataAccessInterface,
                                SignupOutputBoundary signupOutputBoundary) {
        this.userDataAccessInterface = userSignupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
    }

    @Override
    public void execute(UserSignupData userSignupData) {

    }

    public void attemptSignUp(UserSignupData signupInputData) {
        if (!signupInputData.getPassword().equals(signupInputData.getRepeatPass())) {
            userPresenter.prepareFailView("Passwords don't match.");
        } else {
            try {
                User user = getUser(signupInputData);
                userDataAccessInterface.create(user);

                LocalDateTime now = LocalDateTime.now();
                SignupOutputData signupOutputData = new SignupOutputData(user.getUsername(), now.toString());
                userPresenter.prepareSuccessView(signupOutputData);
            } catch (UserDataAccessObject.DuplicateUsernameException e) {
                userPresenter.prepareFailView("Username already exists.");
            }
        }
    }

    public User getUser(UserSignupData input){
        User user;
        if (input.getType().equals("artist")){
            user = new ArtistUser(input.getUsername(), input.getPassword(), input.getEmail(), input.getName());
        } else if (input.getType().equals("venue")){
            user = new VenueUser(input.getUsername(), input.getPassword(), input.getEmail(), input.getName());
        } else {
            user = new AudienceUser(input.getUsername(), input.getPassword(), input.getEmail(), input.getName());
        }
        return user;
    }

}
