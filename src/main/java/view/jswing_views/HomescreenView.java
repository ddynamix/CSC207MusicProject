package view.jswing_views;

import app.interface_adapter_tools.Theme;
import data_access.PostDataAccessInterface;
import entity.post.Post;
import entity.user.User;
import use_case.edit_post.interface_adapter.EditPostController;
import view.jswing_views.utils.CustomListCellRenderer;
import view.jswing_views.utils.PostListCellRenderer;
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


/**
 * Homescreen View
 */
public class HomescreenView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "home";
    private final HomescreenViewModel homescreenViewModel;
    private final ScreenSwitcherController screenSwitcherController;
    private final SignOutController signOutController;
    private final Header header;
    private final PostDataAccessInterface postAccess;

    private JScrollPane scrollPane;

    private JList<PostListJPanel> postList;
    private DefaultListModel<PostListJPanel> postListModel;
    private JPopupMenu popupMenu;

    private User signedInAs = null;
    JLabel welcome_message;

    JButton eventPageButton;
    JButton signOutButton;
    JButton postButton;

    /**
     * Create homescreen view
     * @param homescreenViewModel      model for home screen
     * @param screenSwitcherController controller for switcher
     * @param signOutController        controller for sign out use case
     * @param editPostController       controller for edit post use case
     * @param postDataAccessObject
     * @param headerOriginal           header
     */
    public HomescreenView(HomescreenViewModel homescreenViewModel, ScreenSwitcherController screenSwitcherController,
                          SignOutController signOutController, EditPostController editPostController,
                          PostDataAccessInterface postDataAccessObject, Header headerOriginal) {
        this.homescreenViewModel = homescreenViewModel;
        this.postAccess = postDataAccessObject;
        this.homescreenViewModel.addPropertyChangeListener(this);

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
        header.setToolTipText("Click to open navigation options.");
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
        postList.setCellRenderer(new PostListCellRenderer());
        postList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        postList.setOpaque(false);

        scrollPane = createScrollPane();


        JPanel buttons = new JPanel();
        eventPageButton = new JButton(homescreenViewModel.EVENT_PAGE_BUTTON_LABEL);
        eventPageButton.addActionListener(this);
        eventPageButton.setToolTipText("Click to see your events");
        signOutButton = new JButton(homescreenViewModel.SIGN_OUT_BUTTON_LABEL);
        signOutButton.addActionListener(this);
        signOutButton.setToolTipText("Click to sign out of the program");
        postButton = new JButton(homescreenViewModel.POST_BUTTON_LABEL);
        postButton.addActionListener(this);
        postButton.setToolTipText("Click to create a post on your feed");
        buttons.add(eventPageButton);
        buttons.add(postButton);
        buttons.add(signOutButton);

        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 2;
        c.weighty = 0.1;
        c.insets = new Insets(5, 0, 0, 0);
        c.anchor = GridBagConstraints.PAGE_END;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(buttons, c);

        Theme.ThemeManager.applyTheme(this);
    }

    private JScrollPane createScrollPane() {
        JScrollPane scrollPane = new JScrollPane(postList);
        Theme.ThemeManager.applyTheme(this);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0;
        c.weighty = 1;
        c.gridwidth = 3;
        c.insets = new Insets(10, 5, 10, 5);
        c.fill = GridBagConstraints.BOTH;

        for (Post post : postAccess.getPosts()) {
            PostListJPanel postPanel = new PostListJPanel(post);
            postListModel.addElement(postPanel);
        }
        postList.setCellRenderer(new CustomListCellRenderer());
        postList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        postList.setOpaque(false);
        postList.revalidate();
        postList.repaint();
        scrollPane.revalidate();
        scrollPane.repaint();
        Theme.ThemeManager.applyTheme(scrollPane);
        this.add(scrollPane, c);
        return scrollPane;
    }

    /**
     * @param evt the event to be processed
     */
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

    /**
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        HomescreenState state = (HomescreenState) evt.getNewValue();
        signedInAs = state.getSignedInAs();
        System.out.println("User has " + signedInAs.getMyPosts().size() + " posts");
        System.out.println(postList.getModel().getSize() + " post in pane");
        try {
            if (signedInAs == null) {
                welcome_message.setText("Not signed in");
            } else {
                welcome_message.setText("Signed in as: " + signedInAs.getUsername());
                System.out.println("HomescreenView received new state: " + state);
            }
            Theme.ThemeManager.applyTheme(this);
        } catch (ClassCastException e) {
            System.out.println("Error: HomescreenView received an unexpected event.");
        }
    }


}
