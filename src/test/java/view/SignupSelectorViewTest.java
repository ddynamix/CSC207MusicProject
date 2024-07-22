package view;

import interface_adapter.signupselector.SignupSelectorPresenter;
import interface_adapter.signupselector.SignupSelectorViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class SignupSelectorViewTest {

    private SignupSelectorView signupSelectorView;

    @Mock
    private SignupSelectorPresenter signupSelectorPresenter;

    @Mock
    private SignupSelectorViewModel signupSelectorViewModel;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        signupSelectorView = new SignupSelectorView(signupSelectorPresenter, signupSelectorViewModel);
    }

    @Test
    public void testActionPerformed_signupAudience() {
        signupSelectorView.signupAudience.doClick();

        verify(signupSelectorPresenter).prepareAudienceSignupView();
    }

    @Test
    public void testActionPerformed_signupArtist() {
        signupSelectorView.signupArtist.doClick();

        verify(signupSelectorPresenter).prepareArtistSignupView();
    }

    @Test
    public void testActionPerformed_signupVenue() {
        signupSelectorView.signupVenue.doClick();

        verify(signupSelectorPresenter).prepareVenueSignupView();
    }

    @Test
    public void testActionPerformed_cancel() {
        signupSelectorView.cancel.doClick();

        verify(signupSelectorPresenter).prepareSplashView();
    }
}
