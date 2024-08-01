package view.jswing_views;

import view.jswing_views.utils.LabelTextPanel;
import view_model.EventEditorViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

public class EventEditorView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "event editor";
    private final EventEditorViewModel eventEditorViewModel;

    final JButton publishChanges;
    final JButton cancel;

    final JTextField eventTitleInputField = new JTextField(15);
    final JTextField eventDescriptionInputField = new JTextField(15);
    final JTextField eventDateInputField = new JTextField(15);
    final JTextField eventTagsInputField = new JTextField(15);
    final JTextField eventAttachedMediaField = new JTextField(15);

    public EventEditorView(EventEditorViewModel eventEditorViewModel) {
        this.eventEditorViewModel = eventEditorViewModel;
        this.eventEditorViewModel.addPropertyChangeListener(this);

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel title = new JLabel(eventEditorViewModel.TITLE_LABEL);
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 0.2;
        c.anchor = GridBagConstraints.PAGE_START;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(title, c);

        JPanel editValuesPanel = new JPanel();
        editValuesPanel.setLayout(new BoxLayout(editValuesPanel, BoxLayout.Y_AXIS));

        LabelTextPanel eventTitleInfo = new LabelTextPanel(new JLabel(eventEditorViewModel.EDIT_EVENT_TITLE_LABEL), eventTitleInputField);
        eventTitleInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        editValuesPanel.add(eventTitleInfo);


    }
}
