package view;

import interface_adapter.signupselector.SignupSelectorViewModel;
import interface_adapter.signupselector.SignupSelectorPresenter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignupSelectorView extends JPanel implements ActionListener {

    public final String viewName = "signup selection";
    private final SignupSelectorViewModel signupSelectorViewModel;

    private final SignupSelectorPresenter signupSelectorPresenter;

    private final JButton signupAudience;
    private final JButton signupArtist;
    private final JButton signupVenue;

    public SignupSelectorView(SignupSelectorPresenter signupSelectorPresenter, SignupSelectorViewModel signupSelectorViewModel) {
        this.signupSelectorViewModel = signupSelectorViewModel;
        this.signupSelectorPresenter = signupSelectorPresenter;

        JLabel title = new JLabel(signupSelectorViewModel.TITLE_LABEL);
        title.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        signupAudience = new JButton(signupSelectorViewModel.AUDIENCE_BUTTON_LABEL);
        buttons.add(signupAudience);
        signupArtist = new JButton(signupSelectorViewModel.ARTIST_BUTTON_LABEL);
        buttons.add(signupArtist);
        signupVenue = new JButton(signupSelectorViewModel.VENUE_BUTTON_LABEL);
        buttons.add(signupVenue);

        signupAudience.addActionListener(this);
        signupArtist.addActionListener(this);
        signupVenue.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(signupAudience)) {
            signupSelectorPresenter.prepareAudienceSignupView();
        } else if (evt.getSource().equals(signupArtist)) {
            signupSelectorPresenter.prepareArtistSignupView();
        } else if (evt.getSource().equals(signupVenue)) {
            signupSelectorPresenter.prepareVenueSignupView();
        }
    }
}
