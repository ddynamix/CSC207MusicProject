package view;

import app.interface_adapter_tools.ViewManagerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import view.jswing_views.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;

import static org.mockito.Mockito.*;

public class ViewManagerTest {

    private ViewManager viewManager;

    @Mock
    private ViewManagerModel viewManagerModel;

    @Mock
    private CardLayout cardLayout;

    @Mock
    private JPanel views;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        viewManager = new ViewManager(views, cardLayout, viewManagerModel);
    }

    @Test
    public void testPropertyChange_viewChange() {
        String newViewName = "newView";
        PropertyChangeEvent event = new PropertyChangeEvent(this, "view", null, newViewName);
        viewManager.propertyChange(event);

        verify(cardLayout).show(views, newViewName);
    }
}
