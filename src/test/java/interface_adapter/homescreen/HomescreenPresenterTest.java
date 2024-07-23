package interface_adapter.homescreen;

import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class HomescreenPresenterTest {

    private ViewManagerModel viewManagerModel;
    private HomescreenViewModel homescreenViewModel;
    private HomescreenPresenter homescreenPresenter;

    @BeforeEach
    void setUp() {
        viewManagerModel = mock(ViewManagerModel.class);
        homescreenViewModel = mock(HomescreenViewModel.class);

        homescreenPresenter = new HomescreenPresenter(viewManagerModel, homescreenViewModel);
    }
}
