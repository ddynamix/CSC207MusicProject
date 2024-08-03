package view.jswing_views.utils;

import entity.post.Post;

import javax.swing.*;
import java.awt.*;

public class PostListCellRenderer extends DefaultListCellRenderer {
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
