package view.jswing_views;

import entity.event.Event;
import entity.user.AudienceUser;
import entity.user.User;
import use_case.add_event.interface_adapter.AddEventController;
import use_case.edit_event.interface_adapter.EditEventController;
import view.jswing_views.utils.CustomListCellRenderer;
import view.jswing_views.utils.EventListJPanel;
import view_model.EventScreenViewModel;
import use_case.screen_switcher.interface_adapter.ScreenSwitcherController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

public class EventScreenView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "event screen";
    private final EventScreenViewModel eventScreenViewModel;
    private final ScreenSwitcherController screenSwitcherController;
    private final AddEventController addEventController;
    private final EditEventController editEventController;
    private final JPanel header;

    private JList<EventListJPanel> eventList;
    private DefaultListModel<EventListJPanel> eventListModel;
    private JPopupMenu popupMenu;

    private JButton createEventButton;
    private JButton backButton;

    public EventScreenView(EventScreenViewModel eventScreenViewModel, ScreenSwitcherController screenSwitcherController, AddEventController addEventController, EditEventController editEventController, Header headerOriginal) {
        this.eventScreenViewModel = eventScreenViewModel;
        this.eventScreenViewModel.addPropertyChangeListener(this);
        this.screenSwitcherController = screenSwitcherController;
        this.addEventController = addEventController;
        this.editEventController = editEventController;
        header = headerOriginal;

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel title = new JLabel(eventScreenViewModel.TITLE_LABEL);
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 0.2;
        c.insets = new Insets(5, 5, 0, 0);
        c.anchor = GridBagConstraints.PAGE_START;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(title, c);

        c.gridx = 2;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 0.1;
        c.insets = new Insets(5, 0, 0, 5);
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(header, c);

        eventListModel = new DefaultListModel<>();
        eventList = new JList<>(eventListModel);
        popupMenu = this.createPopupMenu();
        eventList.setComponentPopupMenu(popupMenu);
        eventList.setCellRenderer(new CustomListCellRenderer());
        eventList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        eventList.setLayoutOrientation(JList.VERTICAL);
        eventList.setOpaque(false);

        JScrollPane scrollPane = new JScrollPane(eventList);
        scrollPane.setBackground(Color.LIGHT_GRAY);
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0;
        c.weighty = 1;
        c.gridwidth = 3;
        c.insets = new Insets(10, 5, 10, 5);
        c.fill = GridBagConstraints.BOTH;
        this.add(scrollPane, c);

        JPanel buttons = new JPanel();
        createEventButton = new JButton(eventScreenViewModel.CREATE_EVENT_BUTTON_LABEL);
        createEventButton.addActionListener(this);
        createEventButton.setVisible(false);
        backButton = new JButton(eventScreenViewModel.BACK_BUTTON_LABEL);
        backButton.addActionListener(this);
        buttons.add(createEventButton);
        buttons.add(backButton);

        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 2;
        c.weighty = 0.1;
        c.insets = new Insets(0, 0, 0, 0);
        c.anchor = GridBagConstraints.PAGE_END;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(buttons, c);

        System.out.println(eventList.getModel().getSize() + " evenr in pane\n" + scrollPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createEventButton) {
            screenSwitcherController.switchToEventCrafter();
        } else if (e.getSource() == backButton) {
            screenSwitcherController.switchToHome();
        }
    }

    @Override
    public void propertyChange(java.beans.PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            eventListModel.clear();
            for (Event event : eventScreenViewModel.getState().getEvents()) {
                EventListJPanel eventPanel = new EventListJPanel(event);
                eventListModel.addElement(eventPanel);
            }

            User signedInAs = eventScreenViewModel.getState().getSignedInAs();
            createEventButton.setVisible(!(signedInAs instanceof AudienceUser));
            popupMenu = createPopupMenu(); // Refresh the popup menu
            eventList.setComponentPopupMenu(popupMenu);
            System.out.println("User has " + signedInAs.getMyEvents().size() + " events");
        }
    }

    private JPopupMenu createPopupMenu() {
        JPopupMenu popupMenu = new JPopupMenu();

        eventList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                popupMenu.removeAll();

                EventListJPanel eventPanel = eventList.getSelectedValue();
                if (eventPanel != null) {
                    Event event = eventPanel.getEvent();
                    // Audience can only remove from their list but not the database
                    if (isAudienceUser()) {
                        JMenuItem viewDetails = new JMenuItem("Remove Event");
                        viewDetails.addActionListener(ev -> {
                            addEventController.removeEvent(event);
                        });
                        popupMenu.add(viewDetails);
                    } else {
                        // Venue and Artist can update the database
                        JMenuItem editEvent = new JMenuItem("Edit Event");
                        editEvent.addActionListener(ev -> {
                            editEventController.editEvent(event);
                        });
                        popupMenu.add(editEvent);

                        JMenuItem deleteEvent = new JMenuItem("Delete Event");
                        deleteEvent.addActionListener(ev -> {
                            editEventController.deleteEvent(event);
                        });
                        popupMenu.add(deleteEvent);
                    }
                }
            }
        });

        return popupMenu;
    }

    private boolean isAudienceUser() {
        return eventScreenViewModel.getState().getSignedInAs() instanceof AudienceUser;
    }
}
