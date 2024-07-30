package view;

import use_case.homescreen.interface_adapter.HomescreenController;
import view_model.HomescreenViewModel;
import org.mockito.Mock;
import view.jswing_views.HomescreenView;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomescreenViewTest {

    private HomescreenView homescreenView;

    @Mock
    private HomescreenViewModel homescreenViewModel;

    @Mock
    private HomescreenController homescreenController;

//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//
//        homescreenView = new HomescreenView(homescreenViewModel, homescreenController);
//    }

//    @Test
//    public void testPropertyChange_updatesWelcomeMessage() {
//        HomescreenState state = mock(HomescreenState.class);
//        when(state.getSignedInAs().getUsername()).thenReturn("testUser");
//
//        PropertyChangeEvent event = new PropertyChangeEvent(homescreenViewModel, "state", null, state);
//
//        homescreenView.propertyChange(event);
//
//        JLabel welcomeMessageLabel = homescreenView.welcome_message;
//        assertEquals("Signed in as: testUser", welcomeMessageLabel.getText());
//    }
}
