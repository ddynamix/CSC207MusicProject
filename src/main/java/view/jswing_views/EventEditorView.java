package view.jswing_views;

import use_case.edit_event.interface_adapter.EditEventController;
import use_case.screen_switcher.interface_adapter.ScreenSwitcherController;
import view.jswing_views.utils.LabelTextPanel;
import view_model.EventEditorState;
import view_model.EventEditorViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * view for event editor use case
 */
public class EventEditorView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "event editor";
    private final EventEditorViewModel eventEditorViewModel;
    private final EditEventController editEventController;
    private final ScreenSwitcherController screenSwitcherController;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    final JButton publishChanges;
    final JButton cancel;

    final JTextField eventTitleInputField = new JTextField(15);
    final JTextField eventDescriptionInputField = new JTextField(15);
    final JTextField eventDateInputField = new JTextField(15);
    final JTextField eventTagsInputField = new JTextField(15);
    final JTextField eventAttachedMediaField = new JTextField(15);

    /**
     * create instance of view for event editor use case
     * @param eventEditorViewModel view model for event editor use case
     * @param editEventController controller for event editor use case
     * @param screenSwitcherController controller for switcher
     */
    public EventEditorView(EventEditorViewModel eventEditorViewModel, EditEventController editEventController, ScreenSwitcherController screenSwitcherController) {
        this.eventEditorViewModel = eventEditorViewModel;
        this.eventEditorViewModel.addPropertyChangeListener(this);
        this.editEventController = editEventController;
        this.screenSwitcherController = screenSwitcherController;

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel title = new JLabel(eventEditorViewModel.TITLE_LABEL);
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 0.1;
        c.insets = new Insets(5, 5, 5, 5);
        c.anchor = GridBagConstraints.PAGE_START;
        this.add(title, c);

        JPanel editValuesPanel = new JPanel();
        editValuesPanel.setLayout(new BoxLayout(editValuesPanel, BoxLayout.Y_AXIS));
        editValuesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding around the panel

        LabelTextPanel eventTitleInfo = new LabelTextPanel(new JLabel(eventEditorViewModel.EDIT_EVENT_TITLE_LABEL), eventTitleInputField);
        eventTitleInfo.setAlignmentX(Component.RIGHT_ALIGNMENT);
        eventTitleInfo.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0)); // Add spacing between components
        editValuesPanel.add(eventTitleInfo);

        LabelTextPanel eventDescriptionInfo = new LabelTextPanel(new JLabel(eventEditorViewModel.EDIT_EVENT_DESCRIPTION_LABEL), eventDescriptionInputField);
        eventDescriptionInfo.setAlignmentX(Component.RIGHT_ALIGNMENT);
        eventDescriptionInfo.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0)); // Add spacing between components
        editValuesPanel.add(eventDescriptionInfo);

        LabelTextPanel eventDateInfo = new LabelTextPanel(new JLabel(eventEditorViewModel.EDIT_EVENT_DATE_LABEL), eventDateInputField);
        eventDateInfo.setAlignmentX(Component.RIGHT_ALIGNMENT);
        eventDateInfo.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0)); // Add spacing between components
        editValuesPanel.add(eventDateInfo);

        LabelTextPanel eventTagsInfo = new LabelTextPanel(new JLabel(eventEditorViewModel.EDIT_EVENT_TAGS_LABEL), eventTagsInputField);
        eventTagsInfo.setAlignmentX(Component.RIGHT_ALIGNMENT);
        eventTagsInfo.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0)); // Add spacing between components
        editValuesPanel.add(eventTagsInfo);

        LabelTextPanel eventAttachedMediaInfo = new LabelTextPanel(new JLabel(eventEditorViewModel.EDIT_EVENT_ATTACHED_MEDIA_LABEL), eventAttachedMediaField);
        eventAttachedMediaInfo.setAlignmentX(Component.RIGHT_ALIGNMENT);
        eventAttachedMediaInfo.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0)); // Add spacing between components
        editValuesPanel.add(eventAttachedMediaInfo);

        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 1;
        c.gridwidth = 3;
        c.weighty = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.BOTH; // Ensure the panel expands to fill the space
        this.add(editValuesPanel, c);

        JPanel buttons = new JPanel();
        publishChanges = new JButton(eventEditorViewModel.PUBLISH_BUTTON_LABEL);
        publishChanges.addActionListener(this);
        publishChanges.setToolTipText("Click to update your event");
        buttons.add(publishChanges);
        cancel = new JButton(eventEditorViewModel.CANCEL_BUTTON_LABEL);
        cancel.addActionListener(this);
        cancel.setToolTipText("Click to return to events page");
        buttons.add(cancel);

        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 0.5;
        c.weighty = 0.1;
        c.gridwidth = 3;
        c.anchor = GridBagConstraints.PAGE_END;
        c.insets = new Insets(5, 5, 5, 5);
        this.add(buttons, c);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(publishChanges)) {
            try {
                formatter.parse(eventDateInputField.getText());
                editEventController.updateEvent(eventEditorViewModel.getState().getEventToEdit(),
                        eventTitleInputField.getText(), eventDescriptionInputField.getText(), eventDateInputField.getText(),
                        eventTagsInputField.getText(), eventAttachedMediaField.getText());
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(this, "Invalid date format. Please use the format yyyy-MM-dd HH:mm", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (evt.getSource().equals(cancel)) {
            screenSwitcherController.switchToMyEvents();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        EventEditorState state = (EventEditorState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(EventEditorState state) {
        eventTitleInputField.setText(state.getEventToEdit().getTitle());
        eventDescriptionInputField.setText(state.getEventToEdit().getDescription());
        eventTagsInputField.setText(String.join(";", state.getEventToEdit().getTags()));
        eventAttachedMediaField.setText(state.getEventToEdit().getAttachedMedia());
    }
}
