package view.jswing_views;

import entity.user.ArtistUser;
import entity.user.User;
import entity.user.VenueUser;
import use_case.eventcrafter.interface_adapter.EventCrafterController;
import use_case.eventcrafter.interface_adapter.EventCrafterState;
import use_case.eventcrafter.interface_adapter.EventCrafterViewModel;
import view.jswing_views.utils.LabelTextPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventCrafterView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "eventcrafter view";
    private final EventCrafterViewModel eventCrafterViewModel;
    private final EventCrafterController eventCrafterController;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    final JButton postEvent;
    final JButton cancel;

    final JTextField eventTitleInputField = new JTextField(15);
    final JTextField eventDescriptionInputField = new JTextField(15);
    final JTextField eventDateInputField = new JTextField(15);
    final JTextField eventTagsInputField = new JTextField(15);
    final JTextField eventAttachedMediaField = new JTextField(15);
    JComboBox<ArtistUser> artistComboBox;
    JComboBox<VenueUser> venueComboBox;

    User signedInAs = null;

    public EventCrafterView(EventCrafterViewModel eventCrafterViewModel, EventCrafterController eventCrafterController) {
        this.eventCrafterViewModel = eventCrafterViewModel;
        this.eventCrafterController = eventCrafterController;
        this.eventCrafterViewModel.addPropertyChangeListener(this);

        artistComboBox = new JComboBox<>(eventCrafterViewModel.getState().getArtistUsers().toArray(new ArtistUser[0]));
        venueComboBox = new JComboBox<>(eventCrafterViewModel.getState().getVenueUsers().toArray(new VenueUser[0]));

        postEvent = new JButton(eventCrafterViewModel.POST_EVENT_BUTTON_LABEL);
        postEvent.addActionListener(this);
        cancel = new JButton(eventCrafterViewModel.CANCEL_BUTTON_LABEL);
        cancel.addActionListener(this);

        JPanel buttons = new JPanel();
        buttons.add(postEvent);
        buttons.add(cancel);
        buttons.setAlignmentY(JPanel.BOTTOM_ALIGNMENT);
        buttons.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        LabelTextPanel eventTitleInfo = new LabelTextPanel(new JLabel(eventCrafterViewModel.EVENT_NAME_LABEL), eventTitleInputField);
        LabelTextPanel eventDescriptionInfo = new LabelTextPanel(new JLabel(eventCrafterViewModel.EVENT_DESCRIPTION_LABEL), eventDescriptionInputField);
        LabelTextPanel eventDateInfo = new LabelTextPanel(new JLabel(eventCrafterViewModel.EVENT_DATE_LABEL), eventDateInputField);
        LabelTextPanel eventTagsInfo = new LabelTextPanel(new JLabel(eventCrafterViewModel.EVENT_TAGS_LABEL), eventTagsInputField);
        LabelTextPanel eventAttachedMediaInfo = new LabelTextPanel(new JLabel(eventCrafterViewModel.EVENT_ATTACHED_MEDIA_LABEL), eventAttachedMediaField);

        JLabel title = new JLabel(eventCrafterViewModel.TITLE_LABEL);
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(eventTitleInfo);
        this.add(eventDescriptionInfo);
        this.add(eventDateInfo);
        this.add(eventTagsInfo);
        this.add(eventAttachedMediaInfo);
        if (signedInAs instanceof ArtistUser) {
            this.add(venueComboBox);
        } else if (signedInAs instanceof VenueUser) {
            this.add(artistComboBox);
        }
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(postEvent)) {
            LocalDateTime now = LocalDateTime.now();
            String nowFormatted = now.format(formatter);

            String artist = "";
            String venue = "";
            if (signedInAs instanceof ArtistUser) {
                artist = signedInAs.getUsername();
                venue = venueComboBox.getSelectedItem().toString();
            } else {
                venue = signedInAs.getUsername();
                artist = artistComboBox.getSelectedItem().toString();
            }

            try {
                eventCrafterController.excecute(
                        eventTitleInputField.getText(),
                        artist,
                        venue,
                        eventDateInputField.getText(),
                        eventDescriptionInputField.getText(),
                        eventTagsInputField.getText(),
                        nowFormatted,
                        eventAttachedMediaField.getText()
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
            eventCrafterController.switchToHomescreen();
        }

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        EventCrafterState state = (EventCrafterState) evt.getNewValue();

        signedInAs = state.getSignedInAs();

        // Update artistComboBox
        artistComboBox.removeAllItems();
        for (ArtistUser artist : state.getArtistUsers()) {
            artistComboBox.addItem(artist);
        }

        // Update venueComboBox
        venueComboBox.removeAllItems();
        for (VenueUser venue : state.getVenueUsers()) {
            venueComboBox.addItem(venue);
        }

        updateUserTypeView();
    }

    private void updateUserTypeView() {
        this.remove(artistComboBox);
        this.remove(venueComboBox);

        if (eventCrafterViewModel.getState().getSignedInAs() instanceof ArtistUser) {
            this.add(venueComboBox);
        } else if (eventCrafterViewModel.getState().getSignedInAs() instanceof VenueUser) {
            this.add(artistComboBox);
        }

        this.revalidate();
        this.repaint();
    }
}
