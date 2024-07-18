package view;

import interface_adapter.UserSignupState;
import interface_adapter.artistsignup.ArtistSignupViewModel;
import interface_adapter.artistsignup.ArtistSignupController;
import interface_adapter.artistsignup.ArtistSignupPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ArtistSignupView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "artist sign up";
    private final ArtistSignupViewModel artistSignupViewModel;

    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JPasswordField repeatPasswordInputField = new JPasswordField(15);
    private final JTextField emailInputField = new JTextField(15);
    private final JTextField stageNameInputField = new JTextField(15);

    private final ArtistSignupController artistSignupController;
    private final ArtistSignupPresenter artistSignupPresenter;

    private final JButton signUp;
    private final JButton cancel;

    public ArtistSignupView(ArtistSignupController controller, ArtistSignupPresenter presenter, ArtistSignupViewModel artistSignupViewModel) {
        this.artistSignupController = controller;
        this.artistSignupPresenter = presenter;
        this.artistSignupViewModel = artistSignupViewModel;
        this.artistSignupViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(artistSignupViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel(artistSignupViewModel.USERNAME_LABEL), usernameInputField);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel(artistSignupViewModel.PASSWORD_LABEL), passwordInputField);
        LabelTextPanel repeatPasswordInfo = new LabelTextPanel(
                new JLabel(artistSignupViewModel.REPEAT_PASSWORD_LABEL), repeatPasswordInputField);
        LabelTextPanel emailInfo = new LabelTextPanel(
                new JLabel(artistSignupViewModel.EMAIL_LABEL), emailInputField);
        LabelTextPanel stageNameInfo = new LabelTextPanel(
                new JLabel(artistSignupViewModel.STAGE_NAME_LABEL), stageNameInputField);

        JPanel buttons = new JPanel();
        signUp = new JButton(artistSignupViewModel.SIGNUP_BUTTON_LABEL);
        buttons.add(signUp);
        cancel = new JButton(artistSignupViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        signUp.addActionListener(this);
        cancel.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(repeatPasswordInfo);
        this.add(emailInfo);
        this.add(stageNameInfo);
        this.add(buttons);
    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(signUp)) {
            artistSignupController.execute(usernameInputField.getText(),
                    String.valueOf(passwordInputField.getPassword()),
                    String.valueOf(repeatPasswordInputField.getPassword()),
                    String.valueOf(emailInputField.getText()),
                    String.valueOf(stageNameInputField.getText()));
        } else if (evt.getSource().equals(cancel)) {
            artistSignupPresenter.prepareSignupSelectorView();
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
