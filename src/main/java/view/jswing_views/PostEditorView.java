package view.jswing_views;

import app.interface_adapter_tools.Theme;
import entity.post.IPost;
import use_case.edit_post.interface_adapter.EditPostController;
import use_case.screen_switcher.interface_adapter.ScreenSwitcherController;
import view.jswing_views.utils.LabelTextPanel;
import view_model.PostEditorState;
import view_model.PostEditorViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * view for post editor
 */
public class PostEditorView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "post editor";
    private final PostEditorViewModel postEditorViewModel;
    private final EditPostController editPostController;
    private final ScreenSwitcherController screenSwitcherController;

    final JButton publishChanges;
    final JButton cancel;

    final JTextField postTitleInputField = new JTextField(15);
    final JTextField postTextInputField = new JTextField(15);
    final JTextField postAttachedMediaField = new JTextField(15);
    final JTextField postDateInputField = new JTextField(15);


    /**
     * create view for post edit use case
     * @param postEditorViewModel model for post edit use case
     * @param editPostController controller for post edit use case
     * @param screenSwitcherController controller for switcher
     */
    public PostEditorView(PostEditorViewModel postEditorViewModel, EditPostController editPostController,
                          ScreenSwitcherController screenSwitcherController) {
        this.postEditorViewModel = postEditorViewModel;
        this.postEditorViewModel.addPropertyChangeListener(this);
        this.editPostController = editPostController;
        this.screenSwitcherController = screenSwitcherController;

        postTitleInputField.setToolTipText("Enter the title of your post");
        postTextInputField.setToolTipText("Enter the content of your post");
        postAttachedMediaField.setToolTipText("Enter your media");

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel title = new JLabel(postEditorViewModel.TITLE_LABEL);
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 0.1;
        c.insets = new Insets(5, 5, 5, 5);
        c.anchor = GridBagConstraints.PAGE_START;
        this.add(title, c);

        JPanel editValuesPanel = new JPanel();
        editValuesPanel.setLayout(new BoxLayout(editValuesPanel, BoxLayout.Y_AXIS));
        editValuesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding around the panel

        LabelTextPanel postTitleInfo = new LabelTextPanel(new JLabel(postEditorViewModel.EDIT_POST_TITLE_LABEL), postTitleInputField);
        postTitleInfo.setAlignmentX(Component.RIGHT_ALIGNMENT);
        postTitleInfo.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0)); // Add spacing between components
        editValuesPanel.add(postTitleInfo);

        LabelTextPanel postDescriptionInfo = new LabelTextPanel(new JLabel(postEditorViewModel.EDIT_POST_TEXT_LABEL), postTextInputField);
        postDescriptionInfo.setAlignmentX(Component.RIGHT_ALIGNMENT);
        postDescriptionInfo.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0)); // Add spacing between components
        editValuesPanel.add(postDescriptionInfo);

        LabelTextPanel postAttachedMediaInfo = new LabelTextPanel(new JLabel(postEditorViewModel.EDIT_POST_ATTACHED_MEDIA_LABEL), postAttachedMediaField);
        postAttachedMediaInfo.setAlignmentX(Component.RIGHT_ALIGNMENT);
        postAttachedMediaInfo.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0)); // Add spacing between components
        editValuesPanel.add(postAttachedMediaInfo);

        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 1;
        c.gridwidth = 3;
        c.weighty = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.BOTH; // Ensure the panel expands to fill the space
        this.add(editValuesPanel, c);

        JPanel buttons = new JPanel();
        publishChanges = new JButton(postEditorViewModel.PUBLISH_BUTTON_LABEL);
        publishChanges.addActionListener(this);
        publishChanges.setToolTipText("Click to update your post");
        buttons.add(publishChanges);
        cancel = new JButton(postEditorViewModel.CANCEL_BUTTON_LABEL);
        cancel.addActionListener(this);
        cancel.setToolTipText("Click to return to the home page");
        buttons.add(cancel);

        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 0.5;
        c.weighty = 0.1;
        c.gridwidth = 3;
        c.anchor = GridBagConstraints.PAGE_END;
        c.insets = new Insets(5, 5, 5, 5);
        this.add(buttons, c);
        Theme.ThemeManager.applyTheme(this);
    }

    /**
     * @param evt the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(publishChanges)) {
            editPostController.updatePost(postEditorViewModel.getState().getPostToEdit(),
                    postTitleInputField.getText(), postTextInputField.getText(),
                    postAttachedMediaField.getText());
            screenSwitcherController.switchToHome();
        } else if (evt.getSource().equals(cancel)) {
            screenSwitcherController.switchToHome();
        }
    }

    /**
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        PostEditorState state = (PostEditorState) evt.getNewValue();
        setFields(state);
    }

    /**
     * set the variables from user input
     * @param state current state
     */
    private void setFields(PostEditorState state) {
        postTitleInputField.setText(state.getPostToEdit().getTitle());
        postTextInputField.setText(state.getPostToEdit().getText());
        postDateInputField.setText(state.getPostToEdit().getDateAndTimeString());
        postAttachedMediaField.setText(state.getPostToEdit().getAttachedMedia());
    }
}
