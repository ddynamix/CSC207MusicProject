package interface_adapter.homescreen;

import interface_adapter.ViewManagerModel;
import interface_adapter.eventcrafter.EventCrafterViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class HomescreenPresenterTest {

    private ViewManagerModel viewManagerModel;
    private HomescreenViewModel homescreenViewModel;
    private HomescreenPresenter homescreenPresenter;
    private EventCrafterViewModel eventCrafterViewModel;

    @BeforeEach
    void setUp() {
        viewManagerModel = mock(ViewManagerModel.class);
        homescreenViewModel = mock(HomescreenViewModel.class);
        eventCrafterViewModel = mock(EventCrafterViewModel.class);

        homescreenPresenter = new HomescreenPresenter(viewManagerModel, eventCrafterViewModel, homescreenViewModel);
    }
}
