package use_case.edit_event.interface_adapter;

import app.interface_adapter_tools.ViewManagerModel;
import use_case.edit_event.EditEventOutputBoundary;
import use_case.edit_event.EditEventOutputData;

public class EditEventPresenter implements EditEventOutputBoundary {
    private final ViewManagerModel viewManagerModel;



    @Override
    public void goToEventEditor(EditEventOutputData outputData) {

    }
}
