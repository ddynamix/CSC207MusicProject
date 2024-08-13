package view.jswing_views.utils;

import entity.user.ArtistUser;
import entity.user.User;
import entity.user.VenueUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * JPanel for user
 */
public class UserListJPanel extends JPanel {
    private final User user;

    /**
     * create instance of panel
     * @param user to be converted to panel
     */
    public UserListJPanel(User user) {
        this.user = user;

        String name = user.getName();
        String username = user.getUsername();
        String type = "";
        if (user instanceof VenueUser) {
            type = "Venue";
        } else if (user instanceof ArtistUser){
            type = "Artist";
        } else {
            type = "Audience";
        }

        this.setPreferredSize(new Dimension(200, 100));
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.GRAY);
        GridBagConstraints c = new GridBagConstraints();

        JLabel nameLabel = new JLabel(name);
        nameLabel.setBackground(Color.GRAY);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.insets = new Insets(5, 5, 0, 0);
        this.add(nameLabel, c);

        JLabel usernameLabel = new JLabel(username);
        usernameLabel.setBackground(Color.GRAY);
        usernameLabel.setForeground(Color.DARK_GRAY);
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.insets = new Insets(5, 5, 0, 0);
        this.add(usernameLabel, c);

        JLabel typeLabel = new JLabel(type);
        typeLabel.setBackground(Color.GRAY);
        c.gridx = 2;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        c.insets = new Insets(5, 0, 0, 5);
        this.add(typeLabel, c);

        JLabel infoLabel = new JLabel("Select then right click to view options.");
        infoLabel.setBackground(Color.GRAY);
        c.gridx = 2;
        c.gridy = 2;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.anchor = GridBagConstraints.LAST_LINE_END;
        c.insets = new Insets(5, 0, 5, 5);
        this.add(infoLabel, c);
    }

    /** access user
     * @return user
     */
    public User getUser() {
        return user;
    }
}
