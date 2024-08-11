package view.jswing_views;

import entity.post.Post;
import entity.user.User;
import use_case.add_post.interface_adapter.AddPostController;
import use_case.edit_post.interface_adapter.EditPostController;
import view.jswing_views.utils.CustomListCellRenderer;
import view.jswing_views.utils.PostListJPanel;
import view_model.HomescreenState;
import view_model.HomescreenViewModel;
import use_case.screen_switcher.interface_adapter.ScreenSwitcherController;
import use_case.sign_out.interface_adapter.SignOutController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class HomescreenView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "home";
    private final HomescreenViewModel homescreenViewModel;
    private final ScreenSwitcherController screenSwitcherController;
    private final SignOutController signOutController;
    private final EditPostController editPostController;
    private final AddPostController addPostController;
    private final JPanel header;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private JScrollPane scrollPane;

    private JList<PostListJPanel> postList;
    private DefaultListModel<PostListJPanel> postListModel;
    private JPopupMenu popupMenu;

    private User signedInAs = null;
    JLabel welcome_message;

    JButton eventPageButton;
    JButton signOutButton;
    JButton postButton;

    public HomescreenView(HomescreenViewModel homescreenViewModel, ScreenSwitcherController screenSwitcherController,
                          SignOutController signOutController, EditPostController editPostController,
                          AddPostController addPostController, JPanel headerOriginal) {
        this.homescreenViewModel = homescreenViewModel;
        this.homescreenViewModel.addPropertyChangeListener(this);

        this.editPostController = editPostController;
        this.addPostController = addPostController;
        this.screenSwitcherController = screenSwitcherController;
        this.signOutController = signOutController;

        this.header = headerOriginal;

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel title = new JLabel(homescreenViewModel.TITLE_LABEL);
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

        welcome_message = new JLabel("Signed in as: \n" + signedInAs);
        welcome_message.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 0.2;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(welcome_message, c);

        postListModel = new DefaultListModel<>();
        postList = new JList<>(postListModel);
        popupMenu = this.createPopupMenu();
        postList.setComponentPopupMenu(popupMenu);
        postList.setCellRenderer(new CustomListCellRenderer());
        postList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        postList.setOpaque(false);

        scrollPane = new JScrollPane(postList);
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
        eventPageButton = new JButton(homescreenViewModel.EVENT_PAGE_BUTTON_LABEL);
        eventPageButton.addActionListener(this);
        signOutButton = new JButton(homescreenViewModel.SIGN_OUT_BUTTON_LABEL);
        signOutButton.addActionListener(this);
        postButton = new JButton(homescreenViewModel.POST_BUTTON_LABEL);
        postButton.addActionListener(this);
        buttons.add(eventPageButton);
        buttons.add(postButton);
        buttons.add(signOutButton);

        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 2;
        c.weighty = 1;
        c.insets = new Insets(10, 0, 0, 0);
        c.anchor = GridBagConstraints.PAGE_END;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(buttons, c);

    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(postButton)) {
            screenSwitcherController.switchToPost();
        } else if (evt.getSource().equals(eventPageButton)) {
            screenSwitcherController.switchToMyEvents();
        } else if (evt.getSource().equals(signOutButton)) {
            signOutController.executeSignOut();
            screenSwitcherController.switchToSplash();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        HomescreenState state = (HomescreenState) evt.getNewValue();
        signedInAs = state.getSignedInAs();
        System.out.println("User has " + signedInAs.getMyPosts().size() + " posts");
        System.out.println(postList.getModel().getSize() + " post in pane");
        if (evt.getPropertyName().equals("state")) {
            postListModel.clear();
            scrollPane = new JScrollPane();
            for (Post p : signedInAs.getMyPosts()) {
                PostListJPanel postPanel = new PostListJPanel(p);
                postListModel.addElement(postPanel);
            }
            this.repaint();

            postList.setComponentPopupMenu(popupMenu);
        }
        try {
            if (signedInAs == null) {
                welcome_message.setText("Not signed in");
            } else {
                welcome_message.setText("Signed in as: " + signedInAs.getUsername());
                System.out.println("HomescreenView received new state: " + state);
            }
        } catch (ClassCastException e) {
            System.out.println("Error: HomescreenView received an unexpected event.");
        }
    }

    private JPopupMenu createPopupMenu() {
        JPopupMenu popupMenu = new JPopupMenu();

        postList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                popupMenu.removeAll();

                PostListJPanel postPanel = postList.getSelectedValue();
                if (postPanel != null) {
                    Post post = postPanel.getPost();

                    JMenuItem editPost = new JMenuItem("Edit Post");
                    editPost.addActionListener(ev -> {
                        editPostController.editPost(post);
                    });
                    popupMenu.add(editPost);

                    JMenuItem deletePost = new JMenuItem("Delete Post");
                    deletePost.addActionListener(ev -> {
                        editPostController.deletePost(post);
                        homescreenViewModel.firePropertyChanged();
                        this.repaint();
                    });
                    popupMenu.add(deletePost);
                }
            }
        });

        return popupMenu;
    }
}
