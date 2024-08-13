package view.jswing_views.utils;

import entity.post.Post;

import javax.swing.*;
import java.awt.*;

/**
 * create post cell renderer
 */
public class PostListCellRenderer extends DefaultListCellRenderer {
    /**
     * Access component
     * @param list         The JList we're painting.
     * @param value        The value returned by list.getModel().getElementAt(index).
     * @param index        The cells index.
     * @param isSelected   True if the specified cell was selected.
     * @param cellHasFocus True if the specified cell has the focus.
     * @return the container component
     */
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof Post) {
            Post post = (Post) value;
            setText(post.getTitle() + " - " + post.getText() + " - " + post.getAttachedMedia() + " Posted: " + post.getTimePosted());
        }
        return this;
    }
}
