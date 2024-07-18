package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.artistsignup.ArtistSignupViewModel;
import interface_adapter.audiencesignup.AudienceSignupViewModel;
import interface_adapter.signupselector.SignupSelectorPresenter;
import interface_adapter.signupselector.SignupSelectorViewModel;
import interface_adapter.splash.SplashViewModel;
import interface_adapter.venuesignup.VenueSignupViewModel;
import view.SignupSelectorView;

public class SignupSelectorViewFactory {

    private SignupSelectorViewFactory() {}

    public static SignupSelectorView createSignupSelectorView(ViewManagerModel viewManagerModel,
                                                              SignupSelectorViewModel signupSelectorViewModel,
                                                              AudienceSignupViewModel audienceSignupViewModel,
                                                              ArtistSignupViewModel artistSignupViewModel,
                                                              VenueSignupViewModel venueSignupViewModel,
                                                              SplashViewModel splashViewModel) {
        SignupSelectorPresenter signupSelectorPresenter = new SignupSelectorPresenter(viewManagerModel,
                                                                                      audienceSignupViewModel,
                                                                                      artistSignupViewModel,
                                                                                      venueSignupViewModel,
                                                                                      splashViewModel);

        return new SignupSelectorView(signupSelectorPresenter, signupSelectorViewModel);
    }
}
