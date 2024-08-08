package view.jswing_views;

import entity.song.Song;
import entity.user.User;
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

    public ProfileView(ProfileViewModel profileViewModel, ScreenSwitcherController screenSwitcherController, PlayMusicController playMusicController, Header headerOriginal) {
        this.profileViewModel = profileViewModel;
        this.profileViewModel.addPropertyChangeListener(this);
        this.screenSwitcherController = screenSwitcherController;
        this.playMusicController = playMusicController;
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
        } else {
            JPanel song = new JPanel();
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
        c.insets = new Insets(5, 5, 0, 0);
        c.anchor = GridBagConstraints.LINE_START;
        c.fill = GridBagConstraints.HORIZONTAL;
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
                playMusicController.playMusic(new Song("Baby", "Justin Bieber", "My World 2.0", null, null, null));
            } catch (NoPreviewAvailableException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, profileViewModel.NO_PREVIEW_ERROR);
            }
        } else if (evt.getSource() == stopMusic) {
            playMusicController.stopMusic();
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
            this.paintView();
        }
    }
}
