package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.artistsignup.ArtistSignupViewModel;
import interface_adapter.audiencesignup.AudienceSignupViewModel;
import interface_adapter.signupselector.SignupSelectorPresenter;
import interface_adapter.signupselector.SignupSelectorViewModel;
import interface_adapter.splash.SplashViewModel;
import interface_adapter.venuesignup.VenueSignupViewModel;
import view.SignupSelectorView;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class SignupSelectorViewFactoryTest {

    @Test
    void testCreateSignupSelectorView() {
        // Mocking the dependencies
        ViewManagerModel viewManagerModel = mock(ViewManagerModel.class);
        SignupSelectorViewModel signupSelectorViewModel = mock(SignupSelectorViewModel.class);
        AudienceSignupViewModel audienceSignupViewModel = mock(AudienceSignupViewModel.class);
        ArtistSignupViewModel artistSignupViewModel = mock(ArtistSignupViewModel.class);
        VenueSignupViewModel venueSignupViewModel = mock(VenueSignupViewModel.class);
        SplashViewModel splashViewModel = mock(SplashViewModel.class);

        SignupSelectorView signupSelectorView = SignupSelectorViewFactory.createSignupSelectorView(
                viewManagerModel,
                signupSelectorViewModel,
                audienceSignupViewModel,
                artistSignupViewModel,
                venueSignupViewModel,
                splashViewModel
        );

        assertNotNull(signupSelectorView, "SignupSelectorView should not be null");
    }
}
