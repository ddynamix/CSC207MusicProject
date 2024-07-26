package view.jswing_views;

import entity.user.ArtistUser;
import entity.user.User;
import entity.user.VenueUser;
import use_case.eventcrafter.interface_adapter.EventCrafterController;
import use_case.eventcrafter.interface_adapter.EventCrafterState;
import use_case.eventcrafter.interface_adapter.EventCrafterViewModel;
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

public class EventCrafterView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "eventcrafter view";
    private final EventCrafterViewModel eventCrafterViewModel;
    private final EventCrafterController eventCrafterController;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    final JPanel header;

    final JButton postEvent;
    final JButton cancel;

    final JTextField eventTitleInputField = new JTextField(15);
    final JTextField eventDescriptionInputField = new JTextField(15);
    final JTextField eventDateInputField = new JTextField(15);
    final JTextField eventTagsInputField = new JTextField(15);
    final JTextField eventAttachedMediaField = new JTextField(15);
    JPanel creationPanel;
    JComboBox<ArtistUser> artistComboBox;
    JComboBox<VenueUser> venueComboBox;

    User signedInAs = null;

    public EventCrafterView(EventCrafterViewModel eventCrafterViewModel, EventCrafterController eventCrafterController, Header headerOriginal) {
        this.eventCrafterViewModel = eventCrafterViewModel;
        this.eventCrafterController = eventCrafterController;
        this.eventCrafterViewModel.addPropertyChangeListener(this);
        this.header = headerOriginal;

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel title = new JLabel(eventCrafterViewModel.TITLE_LABEL);

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

        LabelTextPanel eventTitleInfo = new LabelTextPanel(new JLabel(eventCrafterViewModel.EVENT_NAME_LABEL), eventTitleInputField);
        eventTitleInfo.setAlignmentX(JComponent.RIGHT_ALIGNMENT);
        creationPanel.add(eventTitleInfo);
        artistComboBox = new JComboBox<>(eventCrafterViewModel.getState().getArtistUsers().toArray(new ArtistUser[0]));
        venueComboBox = new JComboBox<>(eventCrafterViewModel.getState().getVenueUsers().toArray(new VenueUser[0]));
        artistComboBox.setAlignmentX(JComponent.RIGHT_ALIGNMENT);
        venueComboBox.setAlignmentX(JComponent.RIGHT_ALIGNMENT);
        if (signedInAs instanceof ArtistUser) {
            creationPanel.add(venueComboBox);
        } else if (signedInAs instanceof VenueUser) {
            creationPanel.add(artistComboBox);
        }
        LabelTextPanel eventDescriptionInfo = new LabelTextPanel(new JLabel(eventCrafterViewModel.EVENT_DESCRIPTION_LABEL), eventDescriptionInputField);
        eventDescriptionInfo.setAlignmentX(JComponent.RIGHT_ALIGNMENT);
        creationPanel.add(eventDescriptionInfo);
        LabelTextPanel eventDateInfo = new LabelTextPanel(new JLabel(eventCrafterViewModel.EVENT_DATE_LABEL), eventDateInputField);
        eventDateInfo.setAlignmentX(JComponent.RIGHT_ALIGNMENT);
        creationPanel.add(eventDateInfo);
        LabelTextPanel eventTagsInfo = new LabelTextPanel(new JLabel(eventCrafterViewModel.EVENT_TAGS_LABEL), eventTagsInputField);
        eventTagsInfo.setAlignmentX(JComponent.RIGHT_ALIGNMENT);
        creationPanel.add(eventTagsInfo);
        LabelTextPanel eventAttachedMediaInfo = new LabelTextPanel(new JLabel(eventCrafterViewModel.EVENT_ATTACHED_MEDIA_LABEL), eventAttachedMediaField);
        eventAttachedMediaInfo.setAlignmentX(JComponent.RIGHT_ALIGNMENT);
        creationPanel.add(eventAttachedMediaInfo);

        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0;
        c.weighty = 1;
        c.gridwidth = 3;
        c.insets = new Insets(10, 5, 10, 5);
        c.fill = GridBagConstraints.BOTH;
        this.add(creationPanel, c);

        JPanel buttons = new JPanel();
        postEvent = new JButton(eventCrafterViewModel.POST_EVENT_BUTTON_LABEL);
        postEvent.addActionListener(this);
        cancel = new JButton(eventCrafterViewModel.CANCEL_BUTTON_LABEL);
        cancel.addActionListener(this);
        buttons.add(postEvent);
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
            eventCrafterController.switchToEventScreen();
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
            creationPanel.add(venueComboBox);
            venueComboBox.setAlignmentX(JComponent.RIGHT_ALIGNMENT);
        } else if (eventCrafterViewModel.getState().getSignedInAs() instanceof VenueUser) {
            creationPanel.add(artistComboBox);
            artistComboBox.setAlignmentX(JComponent.RIGHT_ALIGNMENT);
        }

        creationPanel.revalidate();
        creationPanel.repaint();
        this.revalidate();
        this.repaint();
    }
}
