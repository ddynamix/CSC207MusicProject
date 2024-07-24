package interface_adapter_tools.homescreen;

import app.interface_adapter_tools.ViewManagerModel;
import use_case.eventcrafter.interface_adapter.EventCrafterViewModel;
import org.junit.jupiter.api.BeforeEach;
import use_case.homescreen.interface_adapter.HomescreenPresenter;
import use_case.homescreen.interface_adapter.HomescreenViewModel;

import static org.mockito.Mockito.*;

class HomescreenPresenterTest {

    private ViewManagerModel viewManagerModel;
    private HomescreenViewModel homescreenViewModel;
    private HomescreenPresenter homescreenPresenter;
    private EventCrafterViewModel eventCrafterViewModel;

//    @BeforeEach
//    void setUp() {
//        viewManagerModel = mock(ViewManagerModel.class);
//        homescreenViewModel = mock(HomescreenViewModel.class);
//        eventCrafterViewModel = mock(EventCrafterViewModel.class);
//
//        homescreenPresenter = new HomescreenPresenter(viewManagerModel, eventCrafterViewModel, homescreenViewModel);
//    }
}
