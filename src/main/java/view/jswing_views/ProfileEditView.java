package view.jswing_views;

import app.interface_adapter_tools.Theme;
import data_access.UserAlreadyExistsException;
import data_access.mongodb.UserDataAccessObject;
import use_case.edit_profile.interface_adapter.EditProfileController;
import use_case.edit_user.interface_adapter.EditUserController;
import use_case.screen_switcher.interface_adapter.ScreenSwitcherController;
import view.jswing_views.utils.LabelTextPanel;
import view_model.ProfileEditViewModel;
import view_model.ProfileEditorState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * create view for profile edit use case
 */
public class ProfileEditView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "profile editor";
    private final ProfileEditViewModel profileEditViewModel;

    private final EditProfileController editProfileController;
    private final ScreenSwitcherController screenSwitcherController;

    final JTextField usernameInputField = new JTextField(15);
    final JTextField nameInputField = new JTextField(15);
    final JTextField emailInputField = new JTextField(15);

    final JButton update;
    final JButton cancel;

    /**
     * create instance of login view
     * @param profileEditViewModel     model for login use case
     * @param editProfileController    controller for login use case
     * @param screenSwitcherController controller for switcher
     */
    public ProfileEditView(ProfileEditViewModel profileEditViewModel, EditProfileController editProfileController,
                           ScreenSwitcherController screenSwitcherController) {
        this.profileEditViewModel = profileEditViewModel;
        this.editProfileController = editProfileController;
        this.screenSwitcherController = screenSwitcherController;

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 0, 10, 0);
        c.anchor = GridBagConstraints.CENTER;

        LabelTextPanel usernameInfo = new LabelTextPanel(new JLabel(profileEditViewModel.EDIT_USER_USERNAME_LABEL), usernameInputField);
        LabelTextPanel nameInfo = new LabelTextPanel(new JLabel(profileEditViewModel.EDIT_USER_NAME_LABEL), nameInputField);
        LabelTextPanel emailInfo = new LabelTextPanel(new JLabel(profileEditViewModel.EDIT_USER_EMAIL_LABEL), emailInputField);

        JPanel buttons = new JPanel();
        update = new JButton(profileEditViewModel.PUBLISH_BUTTON_LABEL);
        update.setToolTipText("Click to update your account");
        buttons.add(update);
        cancel = new JButton(profileEditViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);
        cancel.setToolTipText("Click to return to your profile page");

        update.addActionListener(this);
        cancel.addActionListener(this);

        this.add(usernameInfo, c);
        this.add(nameInfo, c);
        this.add(emailInfo, c);

        this.add(buttons, c);

        Theme.ThemeManager.applyTheme(this);
    }

    /**
     * @param evt the event to be processed
     */
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(update)) {
            System.out.println("update profile button pressed");
            try {
                editProfileController.updateProfile(profileEditViewModel.getState().getProfileToEdit(),
                        usernameInputField.getText(), emailInputField.getText(), nameInputField.getText());
            } catch (UserDataAccessObject.UserNotFoundException | UserAlreadyExistsException e) {
                throw new RuntimeException(e);
            }
            screenSwitcherController.switchToMyProfile();
        } else if (evt.getSource().equals(cancel)) {
            screenSwitcherController.switchToMyProfile();
        }
    }

    /**
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ProfileEditorState state = (ProfileEditorState) evt.getNewValue();
        setFields(state);
    }

    /**
     * set field from user input
     * @param state current state
     */
    private void setFields(ProfileEditorState state) {
        usernameInputField.setText(state.getProfileToEdit().getUsername());
        emailInputField.setText(state.getProfileToEdit().getEmail());
        nameInputField.setText(state.getProfileToEdit().getName());
    }
}
