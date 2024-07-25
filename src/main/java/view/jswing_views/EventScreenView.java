package view.jswing_views;

import entity.event.Event;
import entity.user.User;
import use_case.eventscreen.interface_adapter.EventScreenController;
import use_case.eventscreen.interface_adapter.EventScreenViewModel;
import use_case.homescreen.interface_adapter.HomescreenController;
import view.jswing_views.utils.EventListCellRenderer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

public class EventScreenView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "event screen";
    private final EventScreenViewModel eventScreenViewModel;
    private final EventScreenController eventScreenController;

    private JList<Event> eventList;
    private DefaultListModel<Event> eventListModel;

    JButton createEventButton;
    JButton backButton;

    public EventScreenView(EventScreenViewModel eventScreenViewModel, EventScreenController eventScreenController) {
        this.eventScreenViewModel = eventScreenViewModel;
        this.eventScreenController = eventScreenController;
        this.eventScreenViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(eventScreenViewModel.TITLE_LABEL);
        title.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        eventListModel = new DefaultListModel<>();
        eventList = new JList<>(eventListModel);
        eventList.setCellRenderer(new EventListCellRenderer());

        JPanel buttons = new JPanel();

        createEventButton = new JButton(eventScreenViewModel.CREATE_EVENT_BUTTON_LABEL);
        createEventButton.setAlignmentX(JComponent.RIGHT_ALIGNMENT);
        createEventButton.setAlignmentY(JComponent.BOTTOM_ALIGNMENT);
        createEventButton.addActionListener(this);
        createEventButton.setVisible(false);

        backButton = new JButton(eventScreenViewModel.BACK_BUTTON_LABEL);
        backButton.setAlignmentX(JComponent.RIGHT_ALIGNMENT);
        backButton.setAlignmentY(JComponent.BOTTOM_ALIGNMENT);
        backButton.addActionListener(this);

        buttons.add(createEventButton);
        buttons.add(backButton);
        buttons.setAlignmentY(JPanel.BOTTOM_ALIGNMENT);
        buttons.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        JScrollPane scrollPane = new JScrollPane(eventList);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(scrollPane);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createEventButton) {
            eventScreenController.executeCreateEvent();
        } else if (e.getSource() == backButton) {
            eventScreenController.executeCancel();
        }
    }

    @Override
    public void propertyChange(java.beans.PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            eventListModel.clear();
            for (Event event : eventScreenViewModel.getState().getEvents()) {
                eventListModel.addElement(event);
            }
        }
    }
}
