package app.controller_factories;

import app.interface_adapter_tools.ViewManagerModel;
import data_access.EventDataAccessInterface;
import data_access.UsersEventsRelationalAccessInterface;
import use_case.edit_event.EditEventInputBoundary;
import use_case.edit_event.EditEventInteractor;
import use_case.edit_event.EditEventOutputBoundary;
import use_case.edit_event.interface_adapter.EditEventController;
import use_case.edit_event.interface_adapter.EditEventPresenter;
import view_model.EventEditorViewModel;
import view_model.EventScreenViewModel;

/**
 * create controllers for edit event use case
 */
public class EditEventControllerFactory {

    private EditEventControllerFactory() {}

    /**
     * create an instance of controller for edit event use case
     * @param viewManagerModel manager for changing views
     * @param eventEditorViewModel view model for edit event use case
     * @param eventScreenViewModel view model for event screen view
     * @param eventDataAccessObject data access for events
     * @param usersEventsRelationalAccessObject data access for user -> events
     * @return controller for edit event use case
     */
    public static EditEventController createEditEventController(ViewManagerModel viewManagerModel, EventEditorViewModel eventEditorViewModel, EventScreenViewModel eventScreenViewModel, EventDataAccessInterface eventDataAccessObject, UsersEventsRelationalAccessInterface usersEventsRelationalAccessObject) {
        EditEventOutputBoundary editEventPresenter = new EditEventPresenter(viewManagerModel, eventEditorViewModel, eventScreenViewModel);
        EditEventInputBoundary editEventInteractor = new EditEventInteractor(editEventPresenter, eventDataAccessObject, usersEventsRelationalAccessObject);

        return new EditEventController(editEventInteractor);
    }
}
