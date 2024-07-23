package view;

import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "log in";
    private final LoginViewModel loginViewModel;

    private final LoginPresenter loginPresenter;
    private final LoginController loginController;

    final JTextField usernameInputField = new JTextField(15);
    private final JLabel usernameErrorField = new JLabel();

    final JPasswordField passwordInputField = new JPasswordField(15);
    private final JLabel passwordErrorField = new JLabel();

    final JButton logIn;
    final JButton cancel;

    public LoginView(LoginController loginController, LoginPresenter loginPresenter, LoginViewModel loginViewModel) {
        this.loginController = loginController;
        this.loginPresenter = loginPresenter;
        this.loginViewModel = loginViewModel;
        this.loginViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(loginViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel("Username"), usernameInputField);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Password"), passwordInputField);

        JPanel buttons = new JPanel();
        logIn = new JButton(loginViewModel.LOGIN_BUTTON_LABEL);
        buttons.add(logIn);
        cancel = new JButton(loginViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        logIn.addActionListener(this);
        cancel.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(usernameErrorField);
        this.add(passwordInfo);
        this.add(passwordErrorField);
        this.add(buttons);
    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(logIn)) {
            System.out.println("log in button pressed");
            loginController.execute(usernameInputField.getText(), String.valueOf(passwordInputField.getPassword()));
        } else if (evt.getSource().equals(cancel)) {
            loginPresenter.prepareSplashView();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoginState state = (LoginState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(LoginState state) {
        usernameInputField.setText(state.getUsername());
        passwordInputField.setText(state.getPassword());
    }
}