package view;

import entity.event.Event;
import interface_adapter.homescreen.HomescreenController;
import interface_adapter.homescreen.HomescreenPresenter;
import interface_adapter.homescreen.HomescreenState;
import interface_adapter.homescreen.HomescreenViewModel;

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
    private final HomescreenPresenter homescreenPresenter;

    private String signedInAs = "";
    JLabel welcome_message;

    private JList<Event> eventList;
    private DefaultListModel<Event> eventListModel;

    final JButton createEventButton;

    public HomescreenView(HomescreenViewModel homescreenViewModel, HomescreenController homescreenController, HomescreenPresenter homescreenPresenter) {
        this.homescreenViewModel = homescreenViewModel;
        this.homescreenController = homescreenController;
        this.homescreenPresenter = homescreenPresenter;
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
            homescreenController.executeCreateEvent();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        try {
            HomescreenState state = (HomescreenState) evt.getNewValue();
            this.signedInAs = state.getSignedInAs().getName();
            welcome_message.setText("Signed in as: " + signedInAs);

            this.setEvents(state.getEvents());
        } catch (ClassCastException e) {
            System.out.println("Error: HomescreenView received an unexpected event.");
        }
    }
}
