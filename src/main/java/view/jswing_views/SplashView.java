package view.jswing_views;

import use_case.splash.interface_adapter.SplashController;
import use_case.splash.interface_adapter.SplashViewModel;

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

    private final SplashController splashController;

    final JButton signupB;
    final JButton loginB;

    /**
     * Creation of splash view
     * @param splashController controller for splash screen
     * @param splashViewModel model for splash screen
     */
    public SplashView(SplashViewModel splashViewModel, SplashController splashController) {
        this.splashViewModel = splashViewModel;
        this.splashController = splashController;

        JLabel title = new JLabel(splashViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        signupB = new JButton(splashViewModel.SIGN_UP_BUTTON_LABEL);
        buttons.add(signupB);
        loginB = new JButton(splashViewModel.LOGIN_BUTTON_LABEL);
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
            splashController.signUpClicked();
        } else if (evt.getSource().equals(loginB)) {
            splashController.logInClicked();
        }
    }
}
