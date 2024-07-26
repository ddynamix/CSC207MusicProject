package view.jswing_views;

import entity.event.Event;
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
    private final JPanel header;

    private User signedInAs = null;
    JLabel welcome_message;

    private JList<Event> eventList;
    private DefaultListModel<Event> eventListModel;

    JButton eventPageButton;
    JButton signOutButton;


    public HomescreenView(HomescreenViewModel homescreenViewModel, HomescreenController homescreenController, JPanel headerOriginal) {
        this.homescreenViewModel = homescreenViewModel;
        this.homescreenController = homescreenController;
        this.homescreenViewModel.addPropertyChangeListener(this);
        this.header = headerOriginal;

        // Ensure header is not null
        if (this.header == null) {
            throw new IllegalArgumentException("Header cannot be null");
        }

        JLabel title = new JLabel(homescreenViewModel.TITLE_LABEL);
        title.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        eventListModel = new DefaultListModel<>();
        eventList = new JList<>(eventListModel);
        eventList.setCellRenderer(new EventListCellRenderer());

        welcome_message = new JLabel("Signed in as: " + signedInAs);
        welcome_message.setAlignmentX(JComponent.CENTER_ALIGNMENT);


        JPanel buttons = new JPanel();

        eventPageButton = new JButton(homescreenViewModel.EVENT_PAGE_BUTTON_LABEL);
        eventPageButton.setAlignmentX(JComponent.RIGHT_ALIGNMENT);
        eventPageButton.setAlignmentY(JComponent.BOTTOM_ALIGNMENT);
        eventPageButton.addActionListener(this);

        signOutButton = new JButton(homescreenViewModel.SIGN_OUT_BUTTON_LABEL);
        signOutButton.setAlignmentX(JComponent.RIGHT_ALIGNMENT);
        signOutButton.setAlignmentY(JComponent.BOTTOM_ALIGNMENT);
        signOutButton.addActionListener(this);


        buttons.add(eventPageButton);
        buttons.add(signOutButton);

        // Ensure the header is visible
        header.setVisible(true);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(header);
        this.add(title);
        this.add(welcome_message);
        this.add(buttons);
    }

    private void setEvents(ArrayList<Event> events) {
        eventListModel.clear();
        for (Event event : events) {
            eventListModel.addElement(event);
        }
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(eventPageButton)) {
            homescreenController.executeEventPageClicked();
        } else if (evt.getSource().equals(signOutButton)) {
            homescreenController.executeSignOut();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        try {
            HomescreenState state = (HomescreenState) evt.getNewValue();
            this.signedInAs = state.getSignedInAs();

            if (signedInAs == null) {
                welcome_message.setText("Not signed in");
            } else {
                welcome_message.setText("Signed in as: " + signedInAs.getUsername());
                System.out.println("HomescreenView received new state: " + state);
                this.setEvents(state.getEvents());
            }
        } catch (ClassCastException e) {
            System.out.println("Error: HomescreenView received an unexpected event.");
        }
    }
}
