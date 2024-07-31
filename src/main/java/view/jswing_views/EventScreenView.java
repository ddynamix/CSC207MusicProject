package view.jswing_views;

import entity.event.Event;
import entity.user.AudienceUser;
import entity.user.User;
import view_model.EventScreenViewModel;
import use_case.screen_switcher.interface_adapter.ScreenSwitcherController;
import view.jswing_views.utils.EventListCellRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

public class EventScreenView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "event screen";
    private final EventScreenViewModel eventScreenViewModel;
    private final ScreenSwitcherController screenSwitcherController;
    private final JPanel header;

    private JList<Event> eventList;
    private DefaultListModel<Event> eventListModel;

    JButton createEventButton;
    JButton backButton;

    public EventScreenView(EventScreenViewModel eventScreenViewModel, ScreenSwitcherController screenSwitcherController, Header headerOriginal) {
        this.eventScreenViewModel = eventScreenViewModel;
        this.eventScreenViewModel.addPropertyChangeListener(this);
        this.screenSwitcherController = screenSwitcherController;
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
        c.weighty = 0.2;
        c.insets = new Insets(5, 0, 0, 5);
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(header, c);

        eventListModel = new DefaultListModel<>();
        eventList = new JList<>(eventListModel);
        eventList.setCellRenderer(new EventListCellRenderer());
        JScrollPane scrollPane = new JScrollPane(eventList);
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
        c.weighty = 1;
        c.insets = new Insets(0, 0, 0, 0);
        c.anchor = GridBagConstraints.PAGE_END;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(buttons, c);
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
                eventListModel.addElement(event);
            }

            System.out.println(eventScreenViewModel.getState().getSignedInAs().getUsername());
            User signedInAs = eventScreenViewModel.getState().getSignedInAs();
            createEventButton.setVisible(!(signedInAs instanceof AudienceUser));
        }
    }
}
