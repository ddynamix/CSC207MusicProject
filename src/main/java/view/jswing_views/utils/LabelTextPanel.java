package view.jswing_views.utils;

import javax.swing.*;

/**
 * A panel containing a label and a text field.
 */
public class LabelTextPanel extends JPanel {
    /**
     * create panel
     * @param label text instructions
     * @param textField empty field
     */
    public LabelTextPanel(JLabel label, JTextField textField) {
        this.add(label);
        this.add(textField);
    }
}
