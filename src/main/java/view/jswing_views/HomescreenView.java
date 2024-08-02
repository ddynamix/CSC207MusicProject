package view.jswing_views;

import entity.user.User;
import use_case.postMaker.interface_adapter.PostMakerController;
import view_model.HomescreenState;
import view_model.HomescreenViewModel;
import use_case.screen_switcher.interface_adapter.ScreenSwitcherController;
import use_case.sign_out.interface_adapter.SignOutController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class HomescreenView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "home";
    private final HomescreenViewModel homescreenViewModel;
    private final ScreenSwitcherController screenSwitcherController;
    private final SignOutController signOutController;
    private final PostMakerController postMakerController;
    private final JPanel header;

    private User signedInAs = null;
    JLabel welcome_message;

    JButton eventPageButton;
    JButton signOutButton;
    JButton postButton;


    public HomescreenView(HomescreenViewModel homescreenViewModel, ScreenSwitcherController screenSwitcherController, SignOutController signOutController, PostMakerController postMakerController, JPanel headerOriginal) {
        this.homescreenViewModel = homescreenViewModel;
        this.homescreenViewModel.addPropertyChangeListener(this);
        this.screenSwitcherController = screenSwitcherController;
        this.signOutController = signOutController;
        this.postMakerController = postMakerController;
        this.header = headerOriginal;

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel title = new JLabel(homescreenViewModel.TITLE_LABEL);
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 0.2;
        c.insets = new Insets(5, 5, 0, 0);
        c.anchor = GridBagConstraints.PAGE_START;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(title, c);

        c.gridx = 2;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 0.2;
        c.insets = new Insets(5, 0, 0, 5);
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(header, c);

        welcome_message = new JLabel("Signed in as: \n" + signedInAs);
        welcome_message.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 0.2;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(welcome_message, c);

        JPanel buttons = new JPanel();
        eventPageButton = new JButton(homescreenViewModel.EVENT_PAGE_BUTTON_LABEL);
        eventPageButton.addActionListener(this);
        signOutButton = new JButton(homescreenViewModel.SIGN_OUT_BUTTON_LABEL);
        signOutButton.addActionListener(this);
        postButton = new JButton(homescreenViewModel.POST_BUTTON_LABEL);
        postButton.addActionListener(this);
        buttons.add(eventPageButton);
        buttons.add(postButton);
        buttons.add(signOutButton);

        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 2;
        c.weighty = 1;
        c.insets = new Insets(10, 0, 0, 0);
        c.anchor = GridBagConstraints.PAGE_END;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(buttons, c);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(eventPageButton)) {
            screenSwitcherController.switchToMyEvents();
        } else if (evt.getSource().equals(signOutButton)) {
            signOutController.executeSignOut();
            screenSwitcherController.switchToSplash();
        } else if (evt.getSource().equals(postButton)){
            screenSwitcherController.switchToPost();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        try {
            HomescreenState state = (HomescreenState) evt.getNewValue();
            this.signedInAs = state.getSignedInAs();

            if (signedInAs == null) {
                welcome_message.setText("Not signed in");
            } else {
                welcome_message.setText("Signed in as: " + signedInAs.getUsername());
                System.out.println("HomescreenView received new state: " + state);
            }
        } catch (ClassCastException e) {
            System.out.println("Error: HomescreenView received an unexpected event.");
        }
    }
}
