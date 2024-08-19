package view.jswing_views;

import app.interface_adapter_tools.Theme;
import entity.post.Post;
import entity.song.Song;
import entity.user.User;
import use_case.add_favourite_song.interface_adapter.AddFavouriteSongController;
import use_case.edit_post.interface_adapter.EditPostController;
import use_case.edit_user.interface_adapter.EditUserController;
import use_case.play_music.NoPreviewAvailableException;
import use_case.play_music.interface_adapter.PlayMusicController;
import use_case.screen_switcher.interface_adapter.ScreenSwitcherController;
import view.jswing_views.utils.CustomListCellRenderer;
import view.jswing_views.utils.PostListJPanel;
import view_model.ProfileState;
import view_model.ProfileViewModel;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * view for profile screen
 */
public class ProfileView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "profile";
    private final ProfileViewModel profileViewModel;
    private final ScreenSwitcherController screenSwitcherController;
    private final PlayMusicController playMusicController;
    private final AddFavouriteSongController addFavouriteSongController;
    private final EditPostController editPostController;
    private final Header header;
    private final EditUserController editUserController;

    private User usersProfile;
    private Song favouriteSong;
    private User signedInAs = null;
    private JPopupMenu popupMenu;

    private int numFollowers;
    private int numFollowing;
    private String email;

    private JButton update;
    private JButton follow;
    private JButton home;
    private JButton playMusic;
    private JButton stopMusic;
    private JButton addSong;
    private JButton userSearch;
    private DefaultListModel<PostListJPanel> postListModel;
    private JList<PostListJPanel> postList;
    private JScrollPane scrollPane;


    /**
     * create instance for view
     * @param profileViewModel view model for profile screen
     * @param screenSwitcherController controller and switcher
     * @param playMusicController controller for play music use case
     * @param addFavouriteSongController controller for add favourite song use case
     * @param editUserController controller for edit user use case
     * @param editPostController controller for edit post use case
     * @param headerOriginal header panel
     */
    public ProfileView(ProfileViewModel profileViewModel, ScreenSwitcherController screenSwitcherController,
                       PlayMusicController playMusicController, AddFavouriteSongController addFavouriteSongController,
                       EditUserController editUserController, EditPostController editPostController, Header headerOriginal) {
        this.profileViewModel = profileViewModel;
        this.editPostController = editPostController;
        this.editUserController = editUserController;
        this.profileViewModel.addPropertyChangeListener(this);
        this.screenSwitcherController = screenSwitcherController;
        this.playMusicController = playMusicController;
        this.addFavouriteSongController = addFavouriteSongController;
        this.header = headerOriginal;
        this.postList = new JList<>();
        this.postListModel = new DefaultListModel<>();
    }

    /**
     * combine all UI components in the view
     */
    private void paintView() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel title = new JLabel(profileViewModel.TITLE_LABEL + usersProfile.getUsername());
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 0.1;
        c.insets = new Insets(5, 5, 0, 0);
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(title, c);

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.weightx = 1.0;
        c.weighty = 0.0;
        c.insets = new Insets(5, 0, 0, 5);
        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(header, c);

        JPanel content = createContent();

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.weightx = 1.0;
        c.weighty = 0;
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        this.add(content, c);

        popupMenu = this.createPopupMenu();

        scrollPane = createScrollPane();
    }

    /**
     * Create the content for user profile JPanel
     * @return JPanel of content
     */
    private JPanel createContent() {
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.add(new JLabel(profileViewModel.USERNAME_LABEL + usersProfile.getUsername()));
        content.add(Box.createVerticalStrut(10)); // Add vertical space
        content.add(new JLabel(profileViewModel.NAME_LABEL + usersProfile.getName()));
        content.add(Box.createVerticalStrut(10)); // Add vertical space

        content.add(new JLabel(profileViewModel.FOLLOWERS_LABEL + numFollowers));
        content.add(Box.createVerticalStrut(10)); // Add vertical space
        content.add(new JLabel(profileViewModel.FOLLOWING_LABEL + numFollowing));
        content.add(Box.createVerticalStrut(10)); // Add vertical space
        content.add(new JLabel(profileViewModel.EMAIL_LABEL + email));
        content.add((Box.createVerticalStrut(10))); // Add vertical space

        if (favouriteSong == null) {
            content.add(new JLabel(profileViewModel.NO_SONG_LABEL));
            content.add(Box.createVerticalStrut(10)); // Add vertical space
            content.add(addSong = new JButton(profileViewModel.ADD_SONG_LABEL));
            addSong.addActionListener(this);
        } else {
            JPanel song = new JPanel();
            song.setLayout(new BoxLayout(song, BoxLayout.Y_AXIS));
            song.add(new JLabel(profileViewModel.FAVOURITE_SONG_LABEL + favouriteSong.getName()));
            content.add(Box.createVerticalStrut(10)); // Add vertical space
            song.add(playMusic = new JButton(profileViewModel.PLAY_MUSIC_LABEL));
            playMusic.addActionListener(this);
            song.add(stopMusic = new JButton(profileViewModel.STOP_MUSIC_LABEL));
            stopMusic.addActionListener(this);
            content.add(song);
        }
        if (signedInAs.equals(usersProfile)){
            JPanel myAccountPanel = new JPanel();

            myAccountPanel.add(home = new JButton("Home"));
            home.addActionListener(this);
            myAccountPanel.add(Box.createVerticalStrut(10)); // Add vertical space

            myAccountPanel.add(update = new JButton("Update Profile"));
            update.addActionListener(this);
            content.add(myAccountPanel);
        } else {
            JPanel visitingAccount = new JPanel();
            visitingAccount.add(Box.createVerticalStrut(10)); // Add vertical space
            visitingAccount.add(userSearch = new JButton("User Search"));
            userSearch.addActionListener(this);
            content.add(visitingAccount);
        }

        return content;
    }

    /**
     * @param evt the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        update.addActionListener(ev -> editUserController.editUser(usersProfile));
        if (evt.getSource() == update) {
            System.out.println("Update button pressed");
            screenSwitcherController.switchToProfileEdit();
        } else if (evt.getSource() == follow) {
            signedInAs.addFollower(usersProfile);
        } else if (evt.getSource() == home) {
            System.out.println("Return to home");
            screenSwitcherController.switchToHome();
        } else if (evt.getSource() == userSearch){
            screenSwitcherController.switchToSearchUsers();
        } else if (evt.getSource() == playMusic) {
            try {
                playMusicController.playMusic(favouriteSong);
            } catch (NoPreviewAvailableException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, profileViewModel.NO_PREVIEW_ERROR);
            }
        } else if (evt.getSource() == stopMusic) {
            playMusicController.stopMusic();
        } else if (evt.getSource() == addSong) {
            showAddSongDialog();
        }
    }

    /**
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            this.usersProfile = profileViewModel.getState().getViewing();
            this.favouriteSong = usersProfile.getFeaturedSong();
            this.numFollowers = usersProfile.getNumFollowers();
            this.numFollowing = usersProfile.getNumFollowing();
            this.email = usersProfile.getEmail();
            ProfileState state = (ProfileState) evt.getNewValue();
            signedInAs = state.getSignedInAs();
            if (postListModel != null){
                postListModel.clear();
            }
            for (Post post : usersProfile.getMyPosts()) {
                PostListJPanel postPanel = new PostListJPanel(post);
                if(signedInAs.equals(usersProfile)) {
                    JPopupMenu menu = createPopupMenu();
                    postPanel.add(menu);
                }
                postListModel.addElement(postPanel);
            }
            System.out.println("List model has: " + postListModel.size() + " posts");
            popupMenu = createPopupMenu();
            postList.setComponentPopupMenu(popupMenu);

            this.removeAll();
            this.paintView();

            Theme.ThemeManager.applyTheme(scrollPane);

        }
    }

    /**
     * Create JOptionPane for Song Selection
     */
    private void showAddSongDialog() {
        String songName = JOptionPane.showInputDialog(this, "Enter song name:", "Add Song", JOptionPane.PLAIN_MESSAGE);
        if (songName != null && !songName.trim().isEmpty()) {
            addFavouriteSongController.addFavouriteSong(songName, usersProfile);
        }
    }

    /**
     * Create JScrollPane for user posts
     * @return JScrollPane of Posts
     */
    private JScrollPane createScrollPane() {
        GridBagConstraints c = new GridBagConstraints();
        JScrollPane scrollPane = new JScrollPane(postList);
        Theme.ThemeManager.applyTheme(this);
        scrollPane.setBackground(Color.LIGHT_GRAY);
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridwidth = 2;
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.BOTH;
        postList.setModel(postListModel);
        postList.setCellRenderer(new CustomListCellRenderer());
        postList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        postList.setLayoutOrientation(JList.VERTICAL);
        postList.setOpaque(false);
        Theme.ThemeManager.applyTheme(scrollPane);
        this.add(scrollPane, c);
        System.out.println("profile has: " + usersProfile.getMyPosts().size() + " posts");
        return scrollPane;
    }

    /**
     * create popup menu for each post
     * @return menu
     */
    private JPopupMenu createPopupMenu() {
        JPopupMenu popupMenu = new JPopupMenu();
        Theme.ThemeManager.applyTheme(this);
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
                        this.repaint();
                    });
                    popupMenu.add(deletePost);
                }
            }
        });

        return popupMenu;
    }
}
