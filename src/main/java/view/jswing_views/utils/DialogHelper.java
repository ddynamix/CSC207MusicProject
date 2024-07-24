package view.jswing_views.utils;

import javax.swing.*;
import java.awt.*;

public class DialogHelper {
    public void showMessageDialog(Component parentComponent, Object message) {
        JOptionPane.showMessageDialog(parentComponent, message);
    }
}
