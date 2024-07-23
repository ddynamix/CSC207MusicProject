package view;

import entity.event.Event;

import javax.swing.*;
import java.awt.*;

public class EventListCellRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof Event) {
            Event event = (Event) value;
            setText(event.getTitle() + " - " + event.getArtist().getUsername() + " on " + event.getDateAndTimeString() + " at " + event.getVenue() + "\n"
                    + event.getDescription() + "\n" + event.getTagsString() + "\n" + event.getAttachedMedia() + "\n" + event.getPostDateString());
        }
        return this;
    }
}
