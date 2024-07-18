package view;

import interface_adapter.UserSignupState;
import interface_adapter.venuesignup.VenueSignupController;
import interface_adapter.venuesignup.VenueSignupPresenter;
import interface_adapter.venuesignup.VenueSignupViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class VenueSignupView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "venue sign up";
    private final VenueSignupViewModel venueSignupViewModel;

    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JPasswordField repeatPasswordInputField = new JPasswordField(15);
    private final JTextField emailInputField = new JTextField(15);
    private final JTextField venueNameInputField = new JTextField(15);
    private final JTextField venueLocationInputField = new JTextField(15);

    private final VenueSignupController venueSignupController;
    private final VenueSignupPresenter venueSignupPresenter;

    private final JButton signUp;
    private final JButton cancel;

    public VenueSignupView(VenueSignupController controller, VenueSignupPresenter presenter, VenueSignupViewModel venueSignupViewModel) {
        this.venueSignupController = controller;
        this.venueSignupPresenter = presenter;
        this.venueSignupViewModel = venueSignupViewModel;
        this.venueSignupViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(venueSignupViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel(venueSignupViewModel.USERNAME_LABEL), usernameInputField);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel(venueSignupViewModel.PASSWORD_LABEL), passwordInputField);
        LabelTextPanel repeatPasswordInfo = new LabelTextPanel(
                new JLabel(venueSignupViewModel.REPEAT_PASSWORD_LABEL), repeatPasswordInputField);
        LabelTextPanel emailInfo = new LabelTextPanel(
                new JLabel(venueSignupViewModel.EMAIL_LABEL), emailInputField);
        LabelTextPanel venueNameInfo = new LabelTextPanel(
                new JLabel(venueSignupViewModel.VENUE_NAME_LABEL), venueNameInputField);
        LabelTextPanel venueLocationInfo = new LabelTextPanel(
                new JLabel(venueSignupViewModel.VENUE_LOCATION_LABEL), venueLocationInputField);

        JPanel buttons = new JPanel();
        signUp = new JButton(venueSignupViewModel.SIGNUP_BUTTON_LABEL);
        buttons.add(signUp);
        cancel = new JButton(venueSignupViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        signUp.addActionListener(this);
        cancel.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(repeatPasswordInfo);
        this.add(emailInfo);
        this.add(venueNameInfo);
        this.add(venueLocationInfo);
        this.add(buttons);
    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(signUp)) {
            venueSignupController.execute(usernameInputField.getText(),
                    String.valueOf(passwordInputField.getPassword()),
                    String.valueOf(repeatPasswordInputField.getPassword()),
                    String.valueOf(emailInputField.getText()),
                    String.valueOf(venueNameInputField.getText()),
                    String.valueOf(venueLocationInputField.getText()));
        } else if (evt.getSource().equals(cancel)) {
            venueSignupPresenter.prepareSignupSelectorView();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        UserSignupState state = (UserSignupState) evt.getNewValue();
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        } else if (state.getPasswordError() != null) {
            JOptionPane.showMessageDialog(this, state.getPasswordError());
        } else if (state.getRepeatPasswordError() != null) {
            JOptionPane.showMessageDialog(this, state.getRepeatPasswordError());
        } else if (state.getEmailError() != null) {
            JOptionPane.showMessageDialog(this, state.getEmailError());
        }
    }
}
