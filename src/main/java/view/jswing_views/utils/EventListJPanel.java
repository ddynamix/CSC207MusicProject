package view.jswing_views.utils;

import entity.event.Event;

import javax.swing.*;
import java.awt.*;

public class EventListJPanel extends JPanel {
    private final Event event;

    public EventListJPanel(Event event) {
        this.event = event;

        String title = event.getTitle();
        String artist = event.getArtist().getName();
        String venue = event.getVenue().getName();
        String date = event.getDateAndTimeString();
        String description = event.getDescription();
        String tags = event.getTagsString();
        // implement media attachments

        this.setPreferredSize(new Dimension(200, 150));
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.GRAY);
        GridBagConstraints c = new GridBagConstraints();

        JLabel titleLabel = new JLabel(title);
        titleLabel.setBackground(Color.GRAY);
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 0.8;
        c.weighty = 0.2;
        c.anchor = GridBagConstraints.PAGE_START;
        c.insets = new Insets(5, 5, 0, 0);
        this.add(titleLabel, c);

        JLabel artistLabel = new JLabel(artist);
        artistLabel.setBackground(Color.GRAY);
        artistLabel.setForeground(Color.DARK_GRAY);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.2;
        c.weighty = 0.2;
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        c.insets = new Insets(5, 5, 0, 0);
        this.add(artistLabel, c);

        JLabel venueLabel = new JLabel(venue);
        venueLabel.setBackground(Color.GRAY);
        venueLabel.setForeground(Color.DARK_GRAY);
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0.8;
        c.weighty = 0.2;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(5, 5, 0, 0);
        this.add(venueLabel, c);

        JLabel dateLabel = new JLabel(date);
        dateLabel.setBackground(Color.GRAY);
        dateLabel.setForeground(Color.DARK_GRAY);
        c.gridx = 2;
        c.gridy = 1;
        c.weightx = 0.8;
        c.weighty = 0.2;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(5, 5, 0, 0);
        this.add(dateLabel, c);

        JLabel descriptionLabel = new JLabel(description);
        descriptionLabel.setBackground(Color.GRAY);
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 1;
        c.weighty = 0.8;
        c.anchor = GridBagConstraints.LAST_LINE_START;
        c.insets = new Insets(5, 5, 0, 0);
        this.add(descriptionLabel, c);

        JLabel tagsLabel = new JLabel(tags);
        tagsLabel.setBackground(Color.GRAY);
        c.gridx = 2;
        c.gridy = 2;
        c.weightx = 0.3;
        c.weighty = 0.8;
        c.anchor = GridBagConstraints.LAST_LINE_END;
        c.insets = new Insets(5, 5, 0, 0);
        this.add(tagsLabel, c);
    }

    public Event getEvent() {
        return event;
    }
}
