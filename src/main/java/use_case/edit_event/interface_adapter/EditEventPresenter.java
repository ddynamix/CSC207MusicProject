package use_case.edit_event.interface_adapter;

import app.interface_adapter_tools.ViewManagerModel;
import use_case.edit_event.EditEventOutputBoundary;
import use_case.edit_event.EditEventOutputData;
import use_case.edit_event.EditEventsSuccessOutputData;
import view_model.EventEditorState;
import view_model.EventEditorViewModel;
import view_model.EventScreenState;
import view_model.EventScreenViewModel;

public class EditEventPresenter implements EditEventOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final EventEditorViewModel eventEditorViewModel;
    private final EventScreenViewModel eventScreenViewModel;

    public EditEventPresenter(ViewManagerModel viewManagerModel, EventEditorViewModel eventEditorViewModel, EventScreenViewModel eventScreenViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.eventEditorViewModel = eventEditorViewModel;
        this.eventScreenViewModel = eventScreenViewModel;
    }

    @Override
    public void goToEventEditor(EditEventOutputData outputData) {
        EventEditorState eventEditorState = eventEditorViewModel.getState();
        eventEditorState.setEventToEdit(outputData.getGetEventToEdit());
        eventEditorViewModel.setState(eventEditorState);
        eventEditorViewModel.firePropertyChanged();

        viewManagerModel.setActiveView("event editor");
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareSuccessView(EditEventsSuccessOutputData outputData) {
        EventScreenState eventScreenState = eventScreenViewModel.getState();
        eventScreenState.setEvents(outputData.getEvents());
        eventScreenViewModel.setState(eventScreenState);
        eventScreenViewModel.firePropertyChanged();

        viewManagerModel.setActiveView("event screen");
        viewManagerModel.firePropertyChanged();
    }
}
