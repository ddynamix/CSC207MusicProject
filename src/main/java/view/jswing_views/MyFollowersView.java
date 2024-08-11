package view.jswing_views;

import app.interface_adapter_tools.UserSession;
import entity.user.User;
import use_case.follow_user.interface_adapter.FollowUserController;
import use_case.screen_switcher.interface_adapter.ScreenSwitcherController;
import view.jswing_views.utils.UserCellListRenderer;
import view.jswing_views.utils.UserListJPanel;
import view_model.MyFollowersViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MyFollowersView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "my followers";
    private final MyFollowersViewModel myFollowersViewModel;
    private final ScreenSwitcherController screenSwitcherController;
    private final FollowUserController followUserController;
    private final JPanel header;

    private final JButton homeButton;

    private JList<UserListJPanel> userList;
    private DefaultListModel<UserListJPanel> listModel;

    public MyFollowersView(MyFollowersViewModel myFollowersViewModel, ScreenSwitcherController screenSwitcherController, FollowUserController followUserController, Header headerOriginal) {
        this.myFollowersViewModel = myFollowersViewModel;
        myFollowersViewModel.addPropertyChangeListener(this);
        this.screenSwitcherController = screenSwitcherController;
        this.followUserController = followUserController;
        this.header = headerOriginal;

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel title = new JLabel(myFollowersViewModel.TITLE_LABEL);
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
        userList.setCellRenderer(new UserCellListRenderer());
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
        homeButton = new JButton(myFollowersViewModel.HOME_BUTTON_LABEL);
        homeButton.addActionListener(this);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(homeButton)) {
            screenSwitcherController.switchToHome();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            listModel.clear();
            for (User user : myFollowersViewModel.getState().getUsersToDisplay()) {
                if (user != UserSession.getInstance().getLoggedInUser()) {
                    UserListJPanel userPanel = new UserListJPanel(user);
                    listModel.addElement(userPanel);
                }
            }
            this.repaint();
        }
    }

    private JPopupMenu createPopupMenu() {
        JPopupMenu popupMenu = new JPopupMenu();

        JMenuItem viewProfile = new JMenuItem("View Profile");
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
