package view;

import interface_adapter.splash.SplashPresenter;
import interface_adapter.splash.SplashViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SplashView extends JPanel implements ActionListener {

    public final String viewName = "splash";
    private final SplashViewModel splashViewModel;

    private final SplashPresenter splashPresenter;

    private final JButton signupB;
    private final JButton loginB;

    public SplashView(SplashPresenter splashPresenter, SplashViewModel splashViewModel) {
        this.splashViewModel = splashViewModel;
        this.splashPresenter = splashPresenter;

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

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(signupB)) {
            // System.out.println("signupB button pressed");
            splashPresenter.prepareSignupView();
        } else if (evt.getSource().equals(loginB)) {
            // System.out.println("loginB button pressed");
            splashPresenter.prepareLoginView();
        }
    }
}
