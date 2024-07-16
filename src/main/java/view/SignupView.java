package view;

import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SignupView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "sign up";
    private final SignupViewModel signupViewModel;

    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JPasswordField repeatPasswordInputField = new JPasswordField(15);
    private final JTextField emailInputField = new JTextField(15);
    private final JTextField firstNameInputField = new JTextField(15);
    private final JTextField lastNameInputField = new JTextField(15);

    private final SignupController signupController;
    private final SignupPresenter signupPresenter;

    private final JButton signUp;
    private final JButton cancel;

    public SignupView(SignupController controller, SignupPresenter presenter, SignupViewModel signupViewModel) {
        this.signupController = controller;
        this.signupPresenter = presenter;
        this.signupViewModel = signupViewModel;
        this.signupViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(signupViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel(signupViewModel.USERNAME_LABEL), usernameInputField);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel(signupViewModel.PASSWORD_LABEL), passwordInputField);
        LabelTextPanel repeatPasswordInfo = new LabelTextPanel(
                new JLabel(signupViewModel.REPEAT_PASSWORD_LABEL), repeatPasswordInputField);
        LabelTextPanel emailInfo = new LabelTextPanel(
                new JLabel(signupViewModel.EMAIL_LABEL), emailInputField);
        LabelTextPanel firstNameInfo = new LabelTextPanel(
                new JLabel(signupViewModel.FIRST_NAME_LABEL), firstNameInputField);
        LabelTextPanel lastNameInfo = new LabelTextPanel(
                new JLabel(signupViewModel.LAST_NAME_LABEL), lastNameInputField);

        JPanel buttons = new JPanel();
        signUp = new JButton(signupViewModel.SIGNUP_BUTTON_LABEL);
        buttons.add(signUp);
        cancel = new JButton(signupViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        signUp.addActionListener(this);
        cancel.addActionListener(this);

        usernameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getState();
                        currentState.setUsername(usernameInputField.getText() + e.getKeyChar());
                        signupViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(repeatPasswordInfo);
        this.add(emailInfo);
        this.add(firstNameInfo);
        this.add(lastNameInfo);
        this.add(buttons);
    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(signUp)) {
            signupController.execute(usernameInputField.getText(),
                    String.valueOf(passwordInputField.getPassword()),
                    String.valueOf(repeatPasswordInputField.getPassword()),
                    String.valueOf(emailInputField.getText()),
                    String.valueOf(firstNameInputField.getText()),
                    String.valueOf(lastNameInputField.getText()));
        } else if (evt.getSource().equals(cancel)) {
            signupPresenter.prepareSplashView();
        }
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SignupState state = (SignupState) evt.getNewValue();
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