package view.jswing_views;

import entity.user.User;
import use_case.postMaker.interface_adapter.PostMakerController;
import use_case.screen_switcher.interface_adapter.ScreenSwitcherController;
import view_model.PostMakerState;
import view_model.PostMakerViewModel;
import view.jswing_views.utils.LabelTextPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * view for post use case
 */
public class PostMakerView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "postMaker view";
    private final PostMakerViewModel postMakerViewModel;
    private final PostMakerController postMakerController;
    private final ScreenSwitcherController screenSwitcherController;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    JPanel creationPanel;
    final JButton post;
    final JButton cancel;

    final JTextField postTitleInputField = new JTextField(15);
    final JTextField postTextInputField = new JTextField(15);
    final JTextField postAttachedMediaInputField = new JTextField(15);
    private final Header header;

    User signedInAs = null;

    /**
     * create post view
     * @param postMakerViewModel model for post use case
     * @param postMakerController controller for post use case
     * @param screenSwitcherController controller for switcher
     * @param headerOriginal header
     */
    public PostMakerView(PostMakerViewModel postMakerViewModel, PostMakerController postMakerController,
                         ScreenSwitcherController screenSwitcherController, Header headerOriginal) {
        if (postMakerController == null){
            throw new IllegalArgumentException("PostMakerController cannot be null - improper initialization");
        }
        this.postMakerViewModel = postMakerViewModel;
        this.postMakerViewModel.addPropertyChangeListener(this);
        this.postMakerController = postMakerController;
        this.screenSwitcherController = screenSwitcherController;
        this.header = headerOriginal;

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel title = new JLabel(postMakerViewModel.TITLE_LABEL);
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);

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

        creationPanel = new JPanel();
        creationPanel.setLayout(new BoxLayout(creationPanel, BoxLayout.Y_AXIS));

        LabelTextPanel postTitleInfo = new LabelTextPanel(new JLabel(postMakerViewModel.TITLE_LABEL), postTitleInputField);
        postTitleInfo.setAlignmentX(JComponent.RIGHT_ALIGNMENT);
        creationPanel.add(postTitleInfo);

        LabelTextPanel postTextInfo = new LabelTextPanel(new JLabel(postMakerViewModel.POST_TEXT_LABEL), postTextInputField);
        postTextInfo.setAlignmentX(JComponent.RIGHT_ALIGNMENT);
        creationPanel.add(postTextInfo);

        LabelTextPanel postMediaInfo = new LabelTextPanel(new JLabel(postMakerViewModel.POST_MEDIA_LABEL), postAttachedMediaInputField);
        postMediaInfo.setAlignmentX(JComponent.RIGHT_ALIGNMENT);
        creationPanel.add(postMediaInfo);

        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0;
        c.weighty = 1;
        c.gridwidth = 3;
        c.insets = new Insets(10, 5, 10, 5);
        c.fill = GridBagConstraints.BOTH;
        this.add(creationPanel, c);

        JPanel buttons = new JPanel();
        post = new JButton(postMakerViewModel.POST_BUTTON_LABEL);
        post.addActionListener(this);
        cancel = new JButton(postMakerViewModel.CANCEL_BUTTON_LABEL);
        cancel.addActionListener(this);
        buttons.add(post);
        buttons.add(cancel);
        c.gridx = 1;
        c.gridy = 2;
        c.weighty = 0.2;
        c.gridwidth = 3;
        c.insets = new Insets(10, 0, 0, 0);
        c.anchor = GridBagConstraints.PAGE_END;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(buttons, c);

    }

    /**
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(post)) {
            try {
                postMakerController.execute(
                        postTitleInputField.getText(),
                        postTextInputField.getText(),
                        signedInAs,
                        postAttachedMediaInputField.getText()
                );
                screenSwitcherController.switchToMyProfile();
            } catch (NullPointerException exception) {
                System.out.println("null pointer exception: ");
                exception.printStackTrace();
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            }
        } else if (e.getSource().equals(cancel)) {
            System.out.println("cancel button pressed");
            screenSwitcherController.switchToHome();
        }

    }

    /**
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        PostMakerState state = (PostMakerState) evt.getNewValue();
        signedInAs = state.getSignedInAs();
        if (signedInAs == null){
            throw new IllegalArgumentException("signedInAs is null");
        }
        this.revalidate();
        this.repaint();
    }
}
