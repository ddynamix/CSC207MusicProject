package view.jswing_views;

import entity.event.Event;
import entity.user.AudienceUser;
import entity.user.User;
import use_case.homescreen.interface_adapter.HomescreenController;
import use_case.homescreen.interface_adapter.HomescreenState;
import use_case.homescreen.interface_adapter.HomescreenViewModel;
import view.jswing_views.utils.EventListCellRenderer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class HomescreenView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "home";
    private final HomescreenViewModel homescreenViewModel;

    private final HomescreenController homescreenController;

    private User signedInAs = null;
    JLabel welcome_message;

    private JList<Event> eventList;
    private DefaultListModel<Event> eventListModel;

    JButton createEventButton;

    public HomescreenView(HomescreenViewModel homescreenViewModel, HomescreenController homescreenController) {
        this.homescreenViewModel = homescreenViewModel;
        this.homescreenController = homescreenController;
        this.homescreenViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(homescreenViewModel.TITLE_LABEL);
        title.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        eventListModel = new DefaultListModel<>();
        eventList = new JList<>(eventListModel);
        eventList.setCellRenderer(new EventListCellRenderer());

        welcome_message = new JLabel("Signed in as: " + signedInAs);
        welcome_message.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        createEventButton = new JButton(homescreenViewModel.CREATE_EVENT_BUTTON_LABEL);
        createEventButton.setAlignmentX(JComponent.RIGHT_ALIGNMENT);
        createEventButton.setAlignmentY(JComponent.BOTTOM_ALIGNMENT);
        createEventButton.addActionListener(this);

        createEventButton.setVisible(false);

        JScrollPane scrollPane = new JScrollPane(eventList);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(welcome_message);
        this.add(scrollPane);
        this.add(createEventButton);
    }

    private void setEvents(ArrayList<Event> events) {
        eventListModel.clear();
        for (Event event : events) {
            eventListModel.addElement(event);
        }
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(createEventButton)) {
            homescreenController.executeCreateEvent(homescreenViewModel.getState().getSignedInAs());
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        try {
            HomescreenState state = (HomescreenState) evt.getNewValue();
            this.signedInAs = state.getSignedInAs();
            welcome_message.setText("Signed in as: " + signedInAs.getUsername());

            createEventButton.setVisible(!(signedInAs instanceof AudienceUser));

            System.out.println("HomescreenView received new state: " + state);
            this.setEvents(state.getEvents());
        } catch (ClassCastException e) {
            System.out.println("Error: HomescreenView received an unexpected event.");
        }
    }
}
