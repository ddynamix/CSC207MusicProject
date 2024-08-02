package view.jswing_views;

import entity.user.User;
import use_case.postMaker.interface_adapter.PostMakerController;
import view_model.PostMakerState;
import view_model.PostMakerViewModel;
import view.jswing_views.utils.LabelTextPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class PostMakerView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "postMaker view";
    private final PostMakerViewModel postMakerViewModel;
    private final PostMakerController postMakerController;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    final JButton post;
    final JButton cancel;

    final JTextField postTitleInputField = new JTextField(15);
    final JTextField postTextInputField = new JTextField(15);
    final JTextField postAttachedMediaInputField = new JTextField(15);

    User signedInAs = null;

    public PostMakerView(PostMakerViewModel postMakerViewModel, PostMakerController postMakerController) {
        this.postMakerViewModel = postMakerViewModel;
        this.postMakerController = postMakerController;
        this.postMakerViewModel.addPropertyChangeListener(this);

        post = new JButton(postMakerViewModel.POST_BUTTON_LABEL);
        post.addActionListener(this);
        cancel = new JButton(postMakerViewModel.CANCEL_BUTTON_LABEL);
        cancel.addActionListener(this);

        JPanel buttons = new JPanel();
        buttons.add(post);
        buttons.add(cancel);
        buttons.setAlignmentY(JPanel.BOTTOM_ALIGNMENT);
        buttons.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        LabelTextPanel postTitleInfo = new LabelTextPanel(new JLabel(postMakerViewModel.TITLE_LABEL), postTitleInputField);
        LabelTextPanel postTextInfo = new LabelTextPanel(new JLabel(postMakerViewModel.POST_TEXT_LABEL), postTextInputField);
        LabelTextPanel postMediaInfo = new LabelTextPanel(new JLabel(postMakerViewModel.POST_MEDIA_LABEL), postAttachedMediaInputField);

        JLabel title = new JLabel(postMakerViewModel.TITLE_LABEL);
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(postTitleInfo);
        this.add(postTextInfo);
        this.add(postMediaInfo);

        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(post)) {
            LocalDateTime now = LocalDateTime.now();
            String nowFormatted = now.format(formatter);

            try {
                postMakerController.execute(
                        postTitleInputField.getText(),
                        postTextInputField.getText(),
                        signedInAs,
                        nowFormatted,
                        postAttachedMediaInputField.getText()
                );
            } catch (NullPointerException exception) {
                System.out.println("null pointer exception: ");
                exception.printStackTrace();
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                //System.out.println(venue + artist + eventDateInputField.getText() + eventDescriptionInputField.getText() + eventTagsInputField.getText() + nowFormatted + eventAttachedMediaField.getText());
            } catch (DateTimeParseException exception) {
                System.out.println("date time parse exception: " + exception);
                JOptionPane.showMessageDialog(this, "Please enter a valid date and time.");
            }
        } else if (e.getSource().equals(cancel)) {
            System.out.println("cancel button pressed");
            postMakerController.switchToHomescreen();
        }

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        PostMakerState state = (PostMakerState) evt.getNewValue();
    }
}
