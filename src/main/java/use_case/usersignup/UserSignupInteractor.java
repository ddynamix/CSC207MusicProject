package use_case.usersignup;

import data_access.UserAlreadyExistsException;
import data_access.UserDataAccessInterface;
import entity.user.ArtistUser;
import entity.user.AudienceUser;
import entity.user.User;
import entity.user.VenueUser;

import java.time.LocalDateTime;

/**
 * Interactor to create a user
 */
public class UserSignupInteractor implements UserSignupInputBoundary{
    final UserDataAccessInterface userDataAccessInterface;
    final SignupOutputBoundary userPresenter;

    /**
     * Instance of interactor
     * @param userSignupDataAccessInterface     access to input of data
     * @param signupOutputBoundary  output of data
     */
    public UserSignupInteractor(UserDataAccessInterface userSignupDataAccessInterface,
                                SignupOutputBoundary signupOutputBoundary) {
        this.userDataAccessInterface = userSignupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
    }

    /**
     * Try-Catch to create a user
     * @param signupInputData data entered by user to instantiate object
     */
    @Override
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
            } catch (UserAlreadyExistsException e) {
                userPresenter.prepareFailView("Username already exists.");
            }
        }
    }

    /**
     * Return user
     * @param input UserSignupData
     * @return created user
     */
    public User getUser(UserSignupData input){
        User user;
        if (input.getType().equals("Artist")){
            user = new ArtistUser(input.getName(), input.getUsername(), input.getPassword(), input.getEmail());
        } else if (input.getType().equals("Venue")){
            user = new VenueUser(input.getName(), input.getUsername(), input.getPassword(), input.getEmail());
        } else {
            user = new AudienceUser(input.getName(), input.getUsername(), input.getPassword(), input.getEmail());
        }
        return user;
    }
}
