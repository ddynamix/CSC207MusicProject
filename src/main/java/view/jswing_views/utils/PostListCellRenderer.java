package view.jswing_views.utils;

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
        if (value instanceof PostListJPanel) {
            // Ensure the panel is displayed correctly
            return (PostListJPanel) value;
        }
        // Default rendering if not a PostListJPanel
        return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
    }
}
