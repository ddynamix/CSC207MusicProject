package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.eventcrafter.EventCrafterViewModel;
import interface_adapter.homescreen.HomescreenController;
import interface_adapter.homescreen.HomescreenPresenter;
import interface_adapter.homescreen.HomescreenViewModel;
import view.HomescreenView;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class HomescreenViewFactoryTest {

//    @Test
//    void testCreateHomescreenView() {
//        // Mocking the dependencies
//        ViewManagerModel viewManagerModel = mock(ViewManagerModel.class);
//        HomescreenViewModel homescreenViewModel = mock(HomescreenViewModel.class);
//        EventCrafterViewModel eventCrafterViewModel = mock(EventCrafterViewModel.class);
//
//        HomescreenView homescreenView = HomescreenViewFactory.createHomescreenView(viewManagerModel, eventCrafterViewModel, homescreenViewModel);
//
//        assertNotNull(homescreenView, "HomescreenView should not be null");
//    }
}
