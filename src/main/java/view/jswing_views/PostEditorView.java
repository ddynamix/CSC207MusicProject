package view.jswing_views;

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



    public PostEditorView(PostEditorViewModel postEditorViewModel, EditPostController editPostController, 
                          ScreenSwitcherController screenSwitcherController) {
        this.postEditorViewModel = postEditorViewModel;
        this.postEditorViewModel.addPropertyChangeListener(this);
        this.editPostController = editPostController;
        this.screenSwitcherController = screenSwitcherController;

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
        buttons.add(publishChanges);
        cancel = new JButton(postEditorViewModel.CANCEL_BUTTON_LABEL);
        cancel.addActionListener(this);
        buttons.add(cancel);

        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 0.5;
        c.weighty = 0.1;
        c.gridwidth = 3;
        c.anchor = GridBagConstraints.PAGE_END;
        c.insets = new Insets(5, 5, 5, 5);
        this.add(buttons, c);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(publishChanges)) {
            try {
                editPostController.updatePost(postEditorViewModel.getState().getPostToEdit(),
                        postTitleInputField.getText(), postTextInputField.getText(),
                        postAttachedMediaField.getText());
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(this, "Invalid date format. Please use the format yyyy-MM-dd HH:mm", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (evt.getSource().equals(cancel)) {
            screenSwitcherController.switchToHome();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        PostEditorState state = (PostEditorState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(PostEditorState state) {
        postTitleInputField.setText(state.getPostToEdit().getTitle());
        postTextInputField.setText(state.getPostToEdit().getText());
        postDateInputField.setText(state.getPostToEdit().getDateAndTimeString());
        postAttachedMediaField.setText(state.getPostToEdit().getAttachedMedia());
    }
}
