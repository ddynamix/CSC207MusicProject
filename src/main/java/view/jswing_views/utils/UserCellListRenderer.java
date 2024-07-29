package view.jswing_views.utils;

import javax.swing.*;
import java.awt.*;

public class UserCellListRenderer extends JPanel implements ListCellRenderer<JPanel> {

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
