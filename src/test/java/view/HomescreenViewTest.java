package view;

import interface_adapter.homescreen.HomescreenController;
import interface_adapter.homescreen.HomescreenPresenter;
import interface_adapter.homescreen.HomescreenState;
import interface_adapter.homescreen.HomescreenViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.*;
import java.beans.PropertyChangeEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class HomescreenViewTest {

    private HomescreenView homescreenView;

    @Mock
    private HomescreenViewModel homescreenViewModel;

    @Mock
    private HomescreenController homescreenController;

    @Mock
    private HomescreenPresenter homescreenPresenter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        homescreenView = new HomescreenView(homescreenViewModel, homescreenController, homescreenPresenter);
    }

    @Test
    public void testPropertyChange_updatesWelcomeMessage() {
        HomescreenState state = mock(HomescreenState.class);
        when(state.getUsername()).thenReturn("testUser");

        PropertyChangeEvent event = new PropertyChangeEvent(homescreenViewModel, "state", null, state);

        homescreenView.propertyChange(event);

        JLabel welcomeMessageLabel = homescreenView.welcome_message;
        assertEquals("Signed in as: testUser", welcomeMessageLabel.getText());
    }
}
