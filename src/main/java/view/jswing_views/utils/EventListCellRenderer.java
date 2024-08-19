package view.jswing_views.utils;

import entity.event.Event;

import javax.swing.*;
import java.awt.*;

/**
 * create event cell renderer
 */
public class EventListCellRenderer extends DefaultListCellRenderer {
    /**
     * access component
     * @param list         The JList we're painting.
     * @param value        The value returned by list.getModel().getElementAt(index).
     * @param index        The cells index.
     * @param isSelected   True if the specified cell was selected.
     * @param cellHasFocus True if the specified cell has the focus.
     * @return container component
     */
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof Event event) {
            setText(event.getTitle() + " - " + event.getArtist().getUsername() + " on " + event.getDateAndTimeString() + " at " + event.getVenue() + "\n"
                    + event.getDescription() + "\n" + event.getTagsString() + "\n" + event.getAttachedMedia() + "\n" + event.getPostDateString());
        }
        return this;
    }
}
