package view.jswing_views.utils;

import javax.swing.*;
import java.awt.*;

/**
 * JOptionPane for dialogues
 */
public class DialogHelper {
    /**
     * Display dialog
     * @param parentComponent component spawning dialogue
     * @param message custom instruction
     */
    public void showMessageDialog(Component parentComponent, Object message) {
        JOptionPane.showMessageDialog(parentComponent, message);
    }
}
