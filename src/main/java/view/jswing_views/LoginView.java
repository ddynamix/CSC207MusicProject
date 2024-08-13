package view.jswing_views;

import use_case.login.interface_adapter.LoginController;
import view_model.LoginState;
import view_model.LoginViewModel;
import use_case.screen_switcher.interface_adapter.ScreenSwitcherController;
import view.jswing_views.utils.LabelTextPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "log in";
    private final LoginViewModel loginViewModel;

    private final LoginController loginController;
    private final ScreenSwitcherController screenSwitcherController;

    final JTextField usernameInputField = new JTextField(15);
    private final JLabel usernameErrorField = new JLabel();

    final JPasswordField passwordInputField = new JPasswordField(15);
    private final JLabel passwordErrorField = new JLabel();

    final JButton logIn;
    final JButton cancel;

    public LoginView(LoginViewModel loginViewModel, LoginController loginController, ScreenSwitcherController screenSwitcherController) {
        this.loginViewModel = loginViewModel;
        this.loginViewModel.addPropertyChangeListener(this);
        this.loginController = loginController;
        this.screenSwitcherController = screenSwitcherController;

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 0, 10, 0);
        c.anchor = GridBagConstraints.CENTER;

        JLabel title = new JLabel(loginViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel usernameInfo = new LabelTextPanel(new JLabel("Username"), usernameInputField);
        LabelTextPanel passwordInfo = new LabelTextPanel(new JLabel("Password"), passwordInputField);

        JPanel buttons = new JPanel();
        logIn = new JButton(loginViewModel.LOGIN_BUTTON_LABEL);
        logIn.setToolTipText("Click to log into the application");
        buttons.add(logIn);
        cancel = new JButton(loginViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);
        cancel.setToolTipText("Click to return to the opening page");

        logIn.addActionListener(this);
        cancel.addActionListener(this);

        this.add(title, c);
        this.add(usernameInfo, c);
        this.add(usernameErrorField, c);
        this.add(passwordInfo, c);
        this.add(passwordErrorField, c);
        this.add(buttons, c);
    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(logIn)) {
            System.out.println("log in button pressed");
            loginController.execute(usernameInputField.getText(), String.valueOf(passwordInputField.getPassword()));
        } else if (evt.getSource().equals(cancel)) {
            screenSwitcherController.switchToSplash();
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
