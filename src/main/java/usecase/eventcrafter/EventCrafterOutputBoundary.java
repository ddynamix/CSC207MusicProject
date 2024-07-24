package usecase.eventcrafter;

import java.util.ArrayList;

public interface EventCrafterOutputBoundary {
    void prepareSuccessView(EventCrafterOutputData eventCrafterOutputData);

    void prepareFailView(String error);

    void switchToHomescreen();
}
