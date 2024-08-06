package view.jswing_views;

import entity.song.Song;
import entity.user.User;
import use_case.play_music.interface_adapter.PlayMusicController;
import use_case.screen_switcher.interface_adapter.ScreenSwitcherController;
import view_model.ProfileViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;

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

    public ProfileView(ProfileViewModel profileViewModel, ScreenSwitcherController screenSwitcherController, PlayMusicController playMusicController, Header headerOriginal) {
        this.profileViewModel = profileViewModel;
        this.profileViewModel.addPropertyChangeListener(this);
        this.screenSwitcherController = screenSwitcherController;
        this.playMusicController = playMusicController;
        this.header = headerOriginal;

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel title = new JLabel(profileViewModel.TITLE_LABEL + usersProfile.getUsername());
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
        c.weighty = 0.1;
        c.insets = new Insets(5, 0, 0, 5);
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(header, c);

        JPanel names = new JPanel();
        names.add(new JLabel(profileViewModel.USERNAME_LABEL + usersProfile.getUsername()));
        names.add(new JLabel(profileViewModel.NAME_LABEL + usersProfile.getName()));
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 1;
        c.insets = new Insets(5, 5, 0, 0);
        c.anchor = GridBagConstraints.LINE_START;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(names, c);

        JPanel followInfo = new JPanel();
        followInfo.add(new JLabel("Followers: " + numFollowers));
        followInfo.add(new JLabel("Following: " + numFollowing));
        c.gridx = 2;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 1;
        c.insets = new Insets(5, 5, 0, 0);
        c.anchor = GridBagConstraints.LINE_END;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(followInfo, c);

        JPanel song = new JPanel();
        song.add(new JLabel(profileViewModel.FAVOURITE_SONG_LABEL + favouriteSong.getName()));
        song.add(playMusic = new JButton(profileViewModel.PLAY_MUSIC_LABEL));
        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 1;
        c.insets = new Insets(5, 5, 0, 0);
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(song, c);


        // panel.add(new JLabel("Song" + signedInAs.getSong()));

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
            playMusicController.playMusic(new Song("Passionfruit", "Drake", "More Life", null, null, null));
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
        }
    }
}
