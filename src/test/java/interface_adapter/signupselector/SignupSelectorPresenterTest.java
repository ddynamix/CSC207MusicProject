package interface_adapter.signupselector;

import interface_adapter.ViewManagerModel;
import interface_adapter.artistsignup.ArtistSignupViewModel;
import interface_adapter.audiencesignup.AudienceSignupViewModel;
import interface_adapter.venuesignup.VenueSignupViewModel;
import interface_adapter.splash.SplashViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class SignupSelectorPresenterTest {

    private ViewManagerModel viewManagerModel;
    private AudienceSignupViewModel audienceSignupViewModel;
    private ArtistSignupViewModel artistSignupViewModel;
    private VenueSignupViewModel venueSignupViewModel;
    private SplashViewModel splashViewModel;
    private SignupSelectorPresenter signupSelectorPresenter;

    @BeforeEach
    void setUp() {
        viewManagerModel = mock(ViewManagerModel.class);
        audienceSignupViewModel = mock(AudienceSignupViewModel.class);
        artistSignupViewModel = mock(ArtistSignupViewModel.class);
        venueSignupViewModel = mock(VenueSignupViewModel.class);
        splashViewModel = mock(SplashViewModel.class);

        signupSelectorPresenter = new SignupSelectorPresenter(
                viewManagerModel,
                audienceSignupViewModel,
                artistSignupViewModel,
                venueSignupViewModel,
                splashViewModel
        );
    }

    @Test
    void testPrepareAudienceSignupView() {
        String expectedViewName = "audienceSignupView";
        when(audienceSignupViewModel.getViewName()).thenReturn(expectedViewName);

        signupSelectorPresenter.prepareAudienceSignupView();

        verify(viewManagerModel).setActiveView(expectedViewName);
        verify(viewManagerModel).firePropertyChanged();
    }

    @Test
    void testPrepareArtistSignupView() {
        String expectedViewName = "artistSignupView";
        when(artistSignupViewModel.getViewName()).thenReturn(expectedViewName);

        signupSelectorPresenter.prepareArtistSignupView();

        verify(viewManagerModel).setActiveView(expectedViewName);
        verify(viewManagerModel).firePropertyChanged();
    }

    @Test
    void testPrepareVenueSignupView() {
        String expectedViewName = "venueSignupView";
        when(venueSignupViewModel.getViewName()).thenReturn(expectedViewName);

        signupSelectorPresenter.prepareVenueSignupView();

        verify(viewManagerModel).setActiveView(expectedViewName);
        verify(viewManagerModel).firePropertyChanged();
    }

    @Test
    void testPrepareSplashView() {
        String expectedViewName = "splashView";
        when(splashViewModel.getViewName()).thenReturn(expectedViewName);

        signupSelectorPresenter.prepareSplashView();

        verify(viewManagerModel).setActiveView(expectedViewName);
        verify(viewManagerModel).firePropertyChanged();
    }
}
