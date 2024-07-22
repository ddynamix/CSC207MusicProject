package view;

import interface_adapter.UserSignupState;
import interface_adapter.audiencesignup.AudienceSignupViewModel;
import interface_adapter.audiencesignup.AudienceSignupController;
import interface_adapter.audiencesignup.AudienceSignupPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AudienceSignupView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "audience sign up";
    private final AudienceSignupViewModel audienceSignupViewModel;

    final JTextField usernameInputField = new JTextField(15);
    final JPasswordField passwordInputField = new JPasswordField(15);
    final JPasswordField repeatPasswordInputField = new JPasswordField(15);
    final JTextField emailInputField = new JTextField(15);
    final JTextField firstNameInputField = new JTextField(15);
    final JTextField lastNameInputField = new JTextField(15);

    private final AudienceSignupController audienceSignupController;
    private final AudienceSignupPresenter audienceSignupPresenter;
    private final DialogHelper dialogHelper;

    final JButton signUp;
    final JButton cancel;

    public AudienceSignupView(AudienceSignupController controller, AudienceSignupPresenter presenter, AudienceSignupViewModel audienceSignupViewModel) {
        this(controller, presenter, audienceSignupViewModel, new DialogHelper());
    }

    // Constructor to allow injecting DialogHelper for testing
    AudienceSignupView(AudienceSignupController controller, AudienceSignupPresenter presenter, AudienceSignupViewModel audienceSignupViewModel, DialogHelper dialogHelper) {
        this.audienceSignupController = controller;
        this.audienceSignupPresenter = presenter;
        this.audienceSignupViewModel = audienceSignupViewModel;
        this.dialogHelper = dialogHelper;
        this.audienceSignupViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(audienceSignupViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel(audienceSignupViewModel.USERNAME_LABEL), usernameInputField);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel(audienceSignupViewModel.PASSWORD_LABEL), passwordInputField);
        LabelTextPanel repeatPasswordInfo = new LabelTextPanel(
                new JLabel(audienceSignupViewModel.REPEAT_PASSWORD_LABEL), repeatPasswordInputField);
        LabelTextPanel emailInfo = new LabelTextPanel(
                new JLabel(audienceSignupViewModel.EMAIL_LABEL), emailInputField);
        LabelTextPanel firstNameInfo = new LabelTextPanel(
                new JLabel(audienceSignupViewModel.FIRST_NAME_LABEL), firstNameInputField);
        LabelTextPanel lastNameInfo = new LabelTextPanel(
                new JLabel(audienceSignupViewModel.LAST_NAME_LABEL), lastNameInputField);

        JPanel buttons = new JPanel();
        signUp = new JButton(audienceSignupViewModel.SIGNUP_BUTTON_LABEL);
        buttons.add(signUp);
        cancel = new JButton(audienceSignupViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        signUp.addActionListener(this);
        cancel.addActionListener(this);

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
            audienceSignupController.execute(usernameInputField.getText(),
                    String.valueOf(passwordInputField.getPassword()),
                    String.valueOf(repeatPasswordInputField.getPassword()),
                    String.valueOf(emailInputField.getText()),
                    String.valueOf(firstNameInputField.getText()),
                    String.valueOf(lastNameInputField.getText()));
        } else if (evt.getSource().equals(cancel)) {
            audienceSignupPresenter.prepareSignupSelectorView();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        UserSignupState state = (UserSignupState) evt.getNewValue();
        if (state.getUsernameError() != null) {
            dialogHelper.showMessageDialog(this, state.getUsernameError());
        } else if (state.getPasswordError() != null) {
            dialogHelper.showMessageDialog(this, state.getPasswordError());
        } else if (state.getRepeatPasswordError() != null) {
            dialogHelper.showMessageDialog(this, state.getRepeatPasswordError());
        } else if (state.getEmailError() != null) {
            dialogHelper.showMessageDialog(this, state.getEmailError());
        }
    }
}
