package view.jswing_views;

import entity.event.Event;
import entity.user.AudienceUser;
import entity.user.User;
import use_case.homescreen.interface_adapter.HomescreenController;
import use_case.homescreen.interface_adapter.HomescreenState;
import use_case.homescreen.interface_adapter.HomescreenViewModel;
import view.jswing_views.utils.EventListCellRenderer;

import javax.swing.*;
import java.awt.*;
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
    JButton signOutButton;
    static JButton waffleButton;
    JPopupMenu waffleMenu = new JPopupMenu();

    public HomescreenView(HomescreenViewModel homescreenViewModel, HomescreenController homescreenController) {
        this.homescreenViewModel = homescreenViewModel;
        this.homescreenController = homescreenController;
        this.homescreenViewModel.addPropertyChangeListener(this);

        BoxLayout box = new BoxLayout(this, BoxLayout.Y_AXIS);

        JLabel title = new JLabel(homescreenViewModel.TITLE_LABEL);
        title.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        eventListModel = new DefaultListModel<>();
        eventList = new JList<>(eventListModel);
        eventList.setCellRenderer(new EventListCellRenderer());

        welcome_message = new JLabel("Signed in as: " + signedInAs);
        welcome_message.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();

        createEventButton = new JButton(homescreenViewModel.CREATE_EVENT_BUTTON_LABEL);
        createEventButton.setAlignmentX(JComponent.RIGHT_ALIGNMENT);
        createEventButton.setAlignmentY(JComponent.BOTTOM_ALIGNMENT);
        createEventButton.addActionListener(this);
        createEventButton.setVisible(false);

        signOutButton = new JButton(homescreenViewModel.SIGN_OUT_BUTTON_LABEL);
        signOutButton.setAlignmentX(JComponent.RIGHT_ALIGNMENT);
        signOutButton.setAlignmentY(JComponent.BOTTOM_ALIGNMENT);
        signOutButton.addActionListener(this);

        // Create a waffle icon
        waffleButton = getjButton();
        // create arraylist of all JMenuItem options
        var options = new ArrayList<JMenuItem>();
        options.add(new JMenuItem("Home"));
        options.add (new JMenuItem("My Profile"));
        options.add(new JMenuItem("My Events"));
        options.add(new JMenuItem("My Artists"));
        options.add(new JMenuItem("My Venues"));
        options.add(new JMenuItem("My Followers"));
        options.add(new JMenuItem("Sign Out?"));

        // Add options to the menu and add action listeners
        for (JMenuItem item : options){
            item.addActionListener(this);
            waffleMenu.add(item);
        }

        // Add action listener to the button to show the popup menu
        waffleButton.addActionListener(this);

        buttons.add(createEventButton);
        buttons.add(signOutButton);

        // header panel
        JPanel header = new JPanel();
        header.add(title);
        header.add(welcome_message);

        header.add(waffleButton);
        header.setSize(200, 200);
        header.setVisible(true);

        // Scroll Pane
        JScrollPane scrollPane = new JScrollPane(eventList);

        this.setLayout(box);

        // add to frame
        this.add(header);
        this.add(scrollPane);
        this.add(buttons);
    }

    private static JButton getjButton() {
        Icon waffleIcon = new Icon() {
            private final int width = 20;
            private final int height = 20;
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.BLACK);
                // Draw three horizontal lines
                for (int i = 0; i < 3; i++) {
                    int lineY = y + i * (6);
                    g2.fillRect(x, lineY, 20, 2);
                }
            }
            @Override
            public int getIconWidth() {return width;}
            @Override
            public int getIconHeight() {return height;}
        };

        // Create a button with the waffle icon
        waffleButton = new JButton(waffleIcon);
        return waffleButton;
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
        } else if (evt.getSource().equals(signOutButton)) {
            homescreenController.executeSignOut();
        } else if (evt.getSource().equals(waffleButton)){
            waffleMenu.show(waffleButton, waffleButton.getWidth() / 2, waffleButton.getHeight() / 2);
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
                createEventButton.setVisible(!(signedInAs instanceof AudienceUser));
                System.out.println("HomescreenView received new state: " + state);
                this.setEvents(state.getEvents());
            }
        } catch (ClassCastException e) {
            System.out.println("Error: HomescreenView received an unexpected event.");
        }
    }
}
