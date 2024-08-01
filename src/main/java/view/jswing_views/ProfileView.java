package view.jswing_views;

import app.Header;
import entity.event.Event;
import entity.user.ArtistUser;
import entity.user.AudienceUser;
import entity.user.User;
import entity.user.VenueUser;
import use_case.homescreen.interface_adapter.HomescreenController;
import use_case.homescreen.interface_adapter.HomescreenState;
import use_case.homescreen.interface_adapter.HomescreenViewModel;
import view.jswing_views.utils.EventListCellRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Objects;

public class ProfileView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "profile";
    private User signedInAs = null;
    private User viewUser;

    private JButton update;
    private JButton follow;

    public ProfileView(User profile){
        this.viewUser = profile;
        JPanel panel = new JPanel();

        panel.add(new JLabel("Username" + signedInAs.getUsername()));
        panel.add(new JLabel("Name" + signedInAs.getName()));
        panel.add(new JLabel("Email" + signedInAs.getEmail()));
        // panel.add(new JLabel("Song" + signedInAs.getSong()));

        panel.add(update);
        panel.add(follow);

        if (viewUser.equals(signedInAs)){
            update.setVisible(true);
            follow.setVisible(false);
        } else {
            update.setVisible(false);
            follow.setVisible(true);
        }

        this.add(new Header("Profile Page"));
        this.add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(update)){
            updateProfile();
        } else if (evt.getSource().equals(follow)){
            signedInAs.addFollower(viewUser);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public void updateProfile(){
        JOptionPane updater = new JOptionPane();
        JTextField name = new JTextField();
        JTextField username = new JTextField();
        JTextField email = new JTextField();

        updater.add(name);
        updater.add(username);
        updater.add(email);

        JButton finished = new JButton("Submit");

        updater.add(finished);

        updater.setVisible(true);

        if (!Objects.equals(name.getText(), "")) {signedInAs.setName(name.getText());}
        if (!Objects.equals(name.getText(), "")) {signedInAs.setEmail(email.getText());}
        if (!Objects.equals(name.getText(), "")) {signedInAs.setUsername(username.getText());}

    }
}
