package view.jswing_views;

import entity.song.Song;
import entity.user.User;
import use_case.add_favourite_song.AddFavouriteSongInputBoundary;
import use_case.add_favourite_song.interface_adapter.AddFavouriteSongController;
import use_case.play_music.NoPreviewAvailableException;
import use_case.play_music.interface_adapter.PlayMusicController;
import use_case.screen_switcher.interface_adapter.ScreenSwitcherController;
import view_model.ProfileViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ProfileView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "profile";
    private final ProfileViewModel profileViewModel;
    private final ScreenSwitcherController screenSwitcherController;
    private final PlayMusicController playMusicController;
    private final AddFavouriteSongController addFavouriteSongController;
    private final Header header;

    private User usersProfile;
    private Song favouriteSong;
    private boolean isLoggedIn;

    private int numFollowers;
    private int numFollowing;

    private JButton update;
    private JButton follow;
    private JButton home;
    private JButton playMusic;
    private JButton stopMusic;
    private JButton addSong;

    public ProfileView(ProfileViewModel profileViewModel, ScreenSwitcherController screenSwitcherController, PlayMusicController playMusicController, AddFavouriteSongController addFavouriteSongController, Header headerOriginal) {
        this.profileViewModel = profileViewModel;
        this.profileViewModel.addPropertyChangeListener(this);
        this.screenSwitcherController = screenSwitcherController;
        this.playMusicController = playMusicController;
        this.addFavouriteSongController = addFavouriteSongController;
        this.header = headerOriginal;
    }

    private void paintView() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel title = new JLabel(profileViewModel.TITLE_LABEL + usersProfile.getUsername());
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 0.1;
        c.insets = new Insets(5, 5, 0, 0);
        c.anchor = GridBagConstraints.PAGE_START;
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

        if (favouriteSong == null) {
            content.add(new JLabel(profileViewModel.NO_SONG_LABEL));
            content.add(addSong = new JButton(profileViewModel.ADD_SONG_LABEL));
            addSong.addActionListener(this);
        } else {
            JPanel song = new JPanel();
            song.setLayout(new BoxLayout(song, BoxLayout.Y_AXIS));
            song.add(new JLabel(profileViewModel.FAVOURITE_SONG_LABEL + favouriteSong.getName()));
            song.add(playMusic = new JButton(profileViewModel.PLAY_MUSIC_LABEL));
            playMusic.addActionListener(this);
            song.add(stopMusic = new JButton(profileViewModel.STOP_MUSIC_LABEL));
            stopMusic.addActionListener(this);
            content.add(song);
        }

        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 3;
        c.insets = new Insets(5, 5, 5, 5);
        c.anchor = GridBagConstraints.LINE_START;
        this.add(content, c);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == update) {
            // edit profile
        } else if (evt.getSource() == follow) {
            // follow user
        } else if (evt.getSource() == home) {
            screenSwitcherController.switchToHome();
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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            this.usersProfile = profileViewModel.getState().getViewing();
            this.isLoggedIn = profileViewModel.getState().isLoggedIn();
            this.favouriteSong = usersProfile.getFeaturedSong();
            this.numFollowers = usersProfile.getNumFollowers();
            this.numFollowing = usersProfile.getNumFollowing();
            this.removeAll();
            this.paintView();
        }
    }

    private void showAddSongDialog() {
        String songName = JOptionPane.showInputDialog(this, "Enter song name:", "Add Song", JOptionPane.PLAIN_MESSAGE);
        if (songName != null && !songName.trim().isEmpty()) {
            addFavouriteSongController.addFavouriteSong(songName, usersProfile);
        }
    }
}
