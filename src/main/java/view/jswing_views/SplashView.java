package view.jswing_views;

import use_case.screen_switcher.interface_adapter.ScreenSwitcherController;
import view_model.SplashViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * View of opening screen
 */
public class SplashView extends JPanel implements ActionListener {

    public final String viewName = "splash";
    private final SplashViewModel splashViewModel;

    private final ScreenSwitcherController screenSwitcherController;

    final JButton signupB;
    final JButton loginB;

    /**
     * Creation of splash view
     * @param splashViewModel model for splash screen
     * @param screenSwitcherController switches screens
     */
    public SplashView(SplashViewModel splashViewModel, ScreenSwitcherController screenSwitcherController) {
        this.splashViewModel = splashViewModel;
        this.screenSwitcherController = screenSwitcherController;

        JLabel title = new JLabel(splashViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        signupB = new JButton(splashViewModel.SIGN_UP_BUTTON_LABEL);
        signupB.setToolTipText("Click to Create a New Account");
        buttons.add(signupB);
        loginB = new JButton(splashViewModel.LOGIN_BUTTON_LABEL);
        loginB.setToolTipText("Login into your account");
        buttons.add(loginB);

        signupB.addActionListener(this);
        loginB.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(buttons);
    }

    /**
     * Check if click performed and respond
     * @param evt   ActionEvent   Instance of click
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(signupB)) {
            screenSwitcherController.switchToSignup();
        } else if (evt.getSource().equals(loginB)) {
            screenSwitcherController.switchToLogin();
        }
    }
}
