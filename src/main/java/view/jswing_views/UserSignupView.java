package view.jswing_views;

import use_case.usersignup.interface_adapter.UserSignupState;
import use_case.usersignup.interface_adapter.UserSignupController;
import use_case.usersignup.interface_adapter.UserSignupViewModel;
import view.jswing_views.utils.LabelTextPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Enumeration;

public class UserSignupView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "user sign up";
    private final UserSignupViewModel signupViewModel;

    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JPasswordField repeatPasswordInputField = new JPasswordField(15);
    private final JTextField emailInputField = new JTextField(15);
    private final JTextField nameInputField = new JTextField(15);

    private final UserSignupController signupController;

    private final JButton signUp;
    private final JButton cancel;

    private final ButtonGroup options;


    public UserSignupView(UserSignupViewModel signupViewModel, UserSignupController controller) {
        this.signupController = controller;
        this.signupViewModel = signupViewModel;
        this.signupViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(signupViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel questionLabel = new JLabel("Which type of user are you:");
        this.add(questionLabel);
        JRadioButton signupAudience = new JRadioButton("Audience");
        JRadioButton signupArtist = new JRadioButton("Artist");
        JRadioButton signupVenue = new JRadioButton("Venue");
        options = new ButtonGroup();
        options.add(signupAudience);
        options.add(signupArtist);
        options.add(signupVenue);
        this.add(signupAudience);
        this.add(signupArtist);
        this.add(signupVenue);


        JPanel buttons = new JPanel();
        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel(signupViewModel.USERNAME_LABEL), usernameInputField);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel(signupViewModel.PASSWORD_LABEL), passwordInputField);
        LabelTextPanel repeatPasswordInfo = new LabelTextPanel(
                new JLabel(signupViewModel.REPEAT_PASSWORD_LABEL), repeatPasswordInputField);
        LabelTextPanel emailInfo = new LabelTextPanel(
                new JLabel(signupViewModel.EMAIL_LABEL), emailInputField);
        LabelTextPanel nameInfo = new LabelTextPanel(
                new JLabel(signupViewModel.NAME_LABEL), nameInputField);

        signUp = new JButton(signupViewModel.SIGNUP_BUTTON_LABEL);
        buttons.add(signUp);
        cancel = new JButton(signupViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        signUp.addActionListener(this);
        cancel.addActionListener(this);

        this.add(title);
        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(repeatPasswordInfo);
        this.add(emailInfo);
        this.add(nameInfo);
        this.add(buttons);
    }

    public void actionPerformed(ActionEvent evt) {
        String type = "";

        if (evt.getSource().equals(signUp)) {
            for (Enumeration<AbstractButton> buttons = options.getElements(); buttons.hasMoreElements(); ) {
                AbstractButton button = buttons.nextElement();

                if (button.isSelected()) {
                    type = button.getText();
                }
            }
            if (!type.equals("")) {
                signupController.execute(type, usernameInputField.getText(),
                        String.valueOf(passwordInputField.getPassword()),
                        String.valueOf(repeatPasswordInputField.getPassword()),
                        String.valueOf(emailInputField.getText()),
                        String.valueOf(nameInputField.getText()));
            }
        } else if (evt.getSource().equals(cancel)) {
            signupController.cancelClicked();
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
