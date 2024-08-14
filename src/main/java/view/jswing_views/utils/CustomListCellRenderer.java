package view.jswing_views.utils;

import javax.swing.*;
import java.awt.*;

/**
 * create list cell renderer
 */
public class CustomListCellRenderer extends JPanel implements ListCellRenderer<JPanel> {

    /**
     * create instance of the panel
     * @param list         The JList we're painting.
     * @param value        The value returned by list.getModel().getElementAt(index).
     * @param index        The cells index.
     * @param isSelected   True if the specified cell was selected.
     * @param cellHasFocus True if the specified cell has the focus.
     * @return container component
     */
    @Override
    public Component getListCellRendererComponent(JList<? extends JPanel> list, JPanel value, int index, boolean isSelected, boolean cellHasFocus) {
        // Set the background and foreground colors for the selected and non-selected states
        if (isSelected) {
            value.setBackground(list.getSelectionBackground());
            value.setForeground(list.getSelectionForeground());
        } else {
            value.setBackground(list.getBackground());
            value.setForeground(list.getForeground());
        }

        value.setOpaque(true);
        return value;
    }
}
