package view.jswing_views;

import app.interface_adapter_tools.UserSession;
import entity.event.Event;
import entity.user.AudienceUser;
import entity.user.User;
import use_case.add_event.interface_adapter.AddEventController;
import use_case.screen_switcher.interface_adapter.ScreenSwitcherController;
import use_case.search_events.interface_adapter.SearchEventsController;
import view.jswing_views.utils.CustomListCellRenderer;
import view.jswing_views.utils.EventListJPanel;
import view_model.SearchEventsViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SearchEventsView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "search events";
    private final SearchEventsViewModel searchEventsViewModel;
    private final SearchEventsController searchEventsController;
    private final AddEventController addEventController;
    private final ScreenSwitcherController screenSwitcherController;
    private final JPanel header;

    private boolean isAudienceuser = false;

    private JList<EventListJPanel> eventList;
    private DefaultListModel<EventListJPanel> listModel;
    private JPopupMenu popupMenu;

    private final JButton backButton;

    public SearchEventsView(SearchEventsViewModel searchEventsViewModel, SearchEventsController searchEventsController, AddEventController addEventController, ScreenSwitcherController screenSwitcherController, Header headerOriginal) {
        this.searchEventsViewModel = searchEventsViewModel;
        this.searchEventsViewModel.addPropertyChangeListener(this);
        this.searchEventsController = searchEventsController;
        this.addEventController = addEventController;
        this.screenSwitcherController = screenSwitcherController;
        header = headerOriginal;

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel title = new JLabel(searchEventsViewModel.TITLE_LABEL);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 0.1;
        c.insets = new Insets(5, 5, 0, 0);
        c.anchor = GridBagConstraints.FIRST_LINE_START;
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

        listModel = new DefaultListModel<>();
        eventList = new JList<>(listModel);
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
        backButton = new JButton(searchEventsViewModel.BACK_BUTTON_LABEL);
        backButton.addActionListener(this);
        backButton.setToolTipText("Return to previous screen");
        buttons.add(backButton);

        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 2;
        c.weighty = 0.1;
        c.insets = new Insets(0, 0, 0, 0);
        c.anchor = GridBagConstraints.PAGE_END;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(buttons, c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            if (button.equals(backButton)) {
                screenSwitcherController.switchToHome();
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            listModel.clear();
            for (Event event : searchEventsViewModel.getState().getEventsToDisplay()) {
                EventListJPanel eventPanel = new EventListJPanel(event);
                listModel.addElement(eventPanel);
            }
            this.repaint();
        }

        if (evt.getPropertyName().equals("isAudienceUser")) {
            isAudienceuser = (boolean) evt.getNewValue();
            popupMenu.setVisible(isAudienceuser);
        }
    }

    private JPopupMenu createPopupMenu() {
        JPopupMenu popupMenu = new JPopupMenu();

        eventList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                popupMenu.removeAll();

                if (isAudienceUser()) {
                    EventListJPanel eventPanel = eventList.getSelectedValue();
                    if (eventPanel != null) {
                        Event event = eventPanel.getEvent();
                        if (inMyUserEvents(event)) {
                            JMenuItem removeEvent = new JMenuItem("Remove Event");
                            removeEvent.setToolTipText("Click to remove event");
                            removeEvent.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    addEventController.removeEvent(event);
                                }
                            });
                            popupMenu.add(removeEvent);
                        } else {
                            JMenuItem addEvent = new JMenuItem("Add Event");
                            addEvent.setToolTipText("Click to add to your events list");
                            addEvent.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    addEventController.addEvent(event);
                                }
                            });
                            popupMenu.add(addEvent);
                        }
                    }
                }
            }
        });
        return popupMenu;
    }

    private boolean inMyUserEvents(Event event) {
        User user = UserSession.getInstance().getLoggedInUser();
        return user.getMyEvents().contains(event);
    }

    private boolean isAudienceUser() {
        return UserSession.getInstance().getLoggedInUser() instanceof AudienceUser;
    }
}
