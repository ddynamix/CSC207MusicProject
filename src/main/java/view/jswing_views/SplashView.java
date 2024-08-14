package view.jswing_views;

import app.interface_adapter_tools.Theme;
import use_case.screen_switcher.interface_adapter.ScreenSwitcherController;
import view.jswing_views.utils.SplashBackgroundPanel;
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
     *
     * @param splashViewModel          model for splash screen
     * @param screenSwitcherController switches screens
     */
    public SplashView(SplashViewModel splashViewModel, ScreenSwitcherController screenSwitcherController) {
        this.splashViewModel = splashViewModel;
        this.screenSwitcherController = screenSwitcherController;

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);  // Using null layout for absolute positioning

        // Background panel with image
        ImageIcon icon = new ImageIcon("src/main/resources/dancing crowd festival footage.gif");
        Image image = icon.getImage();
        JPanel backgroundPanel = new SplashBackgroundPanel(image);
        backgroundPanel.setBounds(0, 0, 400, 800);
        layeredPane.add(backgroundPanel, Integer.valueOf(0));  // Add at the lowest layer

        JPanel credPanel = new JPanel();
        credPanel.setLayout(new BoxLayout(credPanel, BoxLayout.Y_AXIS));
        credPanel.setOpaque(false);

        JLabel title = new JLabel(splashViewModel.TITLE_LABEL);
        Theme.ThemeManager.addOverridenComponent(title);
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        credPanel.add(title);

        JPanel buttons = new JPanel();
        buttons.setOpaque(false);
        buttons.setLayout(new FlowLayout());

        signupB = new JButton(splashViewModel.SIGN_UP_BUTTON_LABEL);
        signupB.setToolTipText("Click to Create a New Account");
        buttons.add(signupB);

        loginB = new JButton(splashViewModel.LOGIN_BUTTON_LABEL);
        loginB.setToolTipText("Login into your account");
        buttons.add(loginB);

        signupB.addActionListener(this);
        loginB.addActionListener(this);
        buttons.add(loginB);

        credPanel.add(buttons);

        // Position credPanel within the layeredPane
        credPanel.setBounds(0, 400, 400, 800);
        layeredPane.add(credPanel, Integer.valueOf(1));

        this.setLayout(new BorderLayout());
        this.add(layeredPane, BorderLayout.CENTER);
    }

    /**
     * Check if click performed and respond
     *
     * @param evt ActionEvent   Instance of click
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
