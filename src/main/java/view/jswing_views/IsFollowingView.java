package view.jswing_views;

import app.interface_adapter_tools.UserSession;
import entity.user.User;
import use_case.follow_user.interface_adapter.FollowUserController;
import use_case.screen_switcher.interface_adapter.ScreenSwitcherController;
import view.jswing_views.utils.CustomListCellRenderer;
import view.jswing_views.utils.UserListJPanel;
import view_model.IsFollowingViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * view for following
 */
public class IsFollowingView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "is following";
    private final IsFollowingViewModel isFollowingViewModel;
    private final ScreenSwitcherController screenSwitcherController;
    private final FollowUserController followUserController;
    private final JPanel header;

    private final JButton homeButton;

    private JList<UserListJPanel> userList;
    private DefaultListModel<UserListJPanel> listModel;
    private JMenuItem viewProfile = new JMenuItem("View Profile");

    /**
     * create view for following
     * @param isFollowingViewModel model for following view
     * @param screenSwitcherController controller for switcher
     * @param followUserController controller for following view
     * @param headerOriginal header
     */
    public IsFollowingView(IsFollowingViewModel isFollowingViewModel, ScreenSwitcherController screenSwitcherController, FollowUserController followUserController, Header headerOriginal) {
        this.isFollowingViewModel = isFollowingViewModel;
        this.isFollowingViewModel.addPropertyChangeListener(this);
        this.screenSwitcherController = screenSwitcherController;
        this.followUserController = followUserController;
        this.header = headerOriginal;

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel title = new JLabel(isFollowingViewModel.TITLE_LABEL);
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 0.2;
        c.insets = new Insets(5, 5, 0, 0);
        c.anchor = GridBagConstraints.FIRST_LINE_START;
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

        listModel = new DefaultListModel<>();
        userList = new JList<>(listModel);
        JPopupMenu popupMenu = this.createPopupMenu();
        userList.setComponentPopupMenu(popupMenu);
        userList.setCellRenderer(new CustomListCellRenderer());
        userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        userList.setOpaque(false);

        JScrollPane scrollPane = new JScrollPane(userList);
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
        homeButton = new JButton(isFollowingViewModel.HOME_BUTTON_LABEL);
        homeButton.addActionListener(this);
        homeButton.setToolTipText("Return to Home Page");
        buttons.add(homeButton);
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 2;
        c.weighty = 0.1;
        c.insets = new Insets(0, 0, 0, 0);
        c.anchor = GridBagConstraints.PAGE_END;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(buttons, c);
    }

    /**
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(homeButton)) {
            screenSwitcherController.switchToHome();
        } else if (e.getSource().equals(viewProfile)){
            screenSwitcherController.switchToViewProfile((userList.getSelectedValue().getUser()));
        }
    }

    /**
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            listModel.clear();
            for (User user : isFollowingViewModel.getState().getUsersToDisplay()) {
                if (user != UserSession.getInstance().getLoggedInUser()) {
                    UserListJPanel userPanel = new UserListJPanel(user);
                    listModel.addElement(userPanel);
                }
            }
            this.repaint();
        }
    }

    /**
     * create popup menu for each following
     * @return menu
     */
    private JPopupMenu createPopupMenu() {
        JPopupMenu popupMenu = new JPopupMenu();


        viewProfile.addActionListener(e -> {
            UserListJPanel userPanel = userList.getSelectedValue();
            if (userPanel != null) {
                User user = userPanel.getUser();
                System.out.println("View Profile pressed for " + user.getUsername());
            }
        });
        popupMenu.add(viewProfile);

        userList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                popupMenu.removeAll();
                popupMenu.add(viewProfile);

                UserListJPanel userPanel = userList.getSelectedValue();
                if (userPanel != null) {
                    User user = userPanel.getUser();
                    if (isFollowing(user)) {
                        JMenuItem unfollow = new JMenuItem("Unfollow");
                        unfollow.addActionListener(ev -> {
                            System.out.println("Unfollow pressed for " + user.getUsername());
                            followUserController.unfollowUser(user);
                        });
                        popupMenu.add(unfollow);
                    } else {
                        JMenuItem follow = new JMenuItem("Follow");
                        follow.addActionListener(ev -> {
                            System.out.println("Follow pressed for " + user.getUsername());
                            followUserController.followUser(user);
                        });
                        popupMenu.add(follow);
                    }
                }
            }
        });

        return popupMenu;
    }

    private boolean isFollowing(User user) {
        User currentUser = UserSession.getInstance().getLoggedInUser();
        return currentUser.getFollowing().contains(user);
    }
}
