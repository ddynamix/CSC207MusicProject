package interface_adapter.signupselector;

import interface_adapter.ViewManagerModel;
import interface_adapter.artistsignup.ArtistSignupViewModel;
import interface_adapter.splash.SplashViewModel;
import interface_adapter.audiencesignup.AudienceSignupViewModel;
import interface_adapter.venuesignup.VenueSignupViewModel;

public class SignupSelectorPresenter {
    private final AudienceSignupViewModel audienceSignupViewModel;
    private final ArtistSignupViewModel artistSignupViewModel;
    private final VenueSignupViewModel venueSignupViewModel;
    private final SplashViewModel splashViewModel;
    private final ViewManagerModel viewManagerModel;

    public SignupSelectorPresenter(ViewManagerModel viewManagerModel,
                                   AudienceSignupViewModel audienceSignupViewModel,
                                   ArtistSignupViewModel artistSignupViewModel,
                                   VenueSignupViewModel venueSignupViewModel,
                                   SplashViewModel splashViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.splashViewModel = splashViewModel;
        this.audienceSignupViewModel = audienceSignupViewModel;
        this.artistSignupViewModel = artistSignupViewModel;
        this.venueSignupViewModel = venueSignupViewModel;
    }

    public void prepareAudienceSignupView() {
        viewManagerModel.setActiveView(audienceSignupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void prepareArtistSignupView() {
        viewManagerModel.setActiveView(artistSignupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void prepareVenueSignupView() {
        viewManagerModel.setActiveView(venueSignupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void prepareSplashView() {
        viewManagerModel.setActiveView(splashViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
