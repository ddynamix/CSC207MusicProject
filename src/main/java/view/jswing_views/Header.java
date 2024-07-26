package view.jswing_views;

import use_case.screen_switcher.interface_adapter.ScreenSwitcherController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Header extends JPanel implements ActionListener {
    private final ScreenSwitcherController controller;

    private JButton waffleButton;
    private JPopupMenu menu;

    public Header(ScreenSwitcherController controller) {
        this.controller = controller;

        // Add menu items
        ArrayList<JMenuItem> options = new ArrayList<>();
        options.add(new JMenuItem("Home"));
        options.add(new JMenuItem("My Profile"));
        options.add(new JMenuItem("My Events"));
        options.add(new JMenuItem("My Artists"));
        options.add(new JMenuItem("My Venues"));
        options.add(new JMenuItem("My Followers"));
        options.add(new JMenuItem("Sign Out"));

        // Create menu and add items to it
        this.menu = createMenu(options);

        // Create the button graphics
        this.waffleButton = createWaffleButton();

        // Add mouse listener to the button to show the menu
        this.waffleButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                menu.show(waffleButton, e.getX(), e.getY());
            }
        });

        // Set up the panel layout
        this.setLayout(new FlowLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(this.waffleButton);
        this.add(buttonPanel);
    }

    private JPopupMenu createMenu(ArrayList<JMenuItem> items) {
        JPopupMenu newMenu = new JPopupMenu();
        for (JMenuItem item : items) {
            item.addActionListener(this);
            newMenu.add(item);
        }
        return newMenu;
    }

    private static JButton createWaffleButton() {
        Icon waffleIcon = new Icon() {
            private final int width = 100;
            private final int height = 100;

            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.BLACK);
                // Draw three horizontal lines
                for (int i = 0; i < 3; i++) {
                    int lineY = y + i * 6;
                    g2.fillRect(x, lineY, 20, 2);
                }
            }

            @Override
            public int getIconWidth() {
                return width;
            }

            @Override
            public int getIconHeight() {
                return height;
            }
        };

        return new JButton(waffleIcon);
    }

    public ArrayList<JMenuItem> getMenuItems() {
        ArrayList<JMenuItem> menuItems = new ArrayList<>();
        for (Component component : menu.getComponents()) {
            if (component instanceof JMenuItem) {
                menuItems.add((JMenuItem) component);
            }
        }
        return menuItems;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(getMenuItems().get(0))) {  // Home
            controller.switchToHome();
        } else if (evt.getSource().equals(getMenuItems().get(1))) {  // My Profile
            // not implemented
        } else if (evt.getSource().equals(getMenuItems().get(2))) {  // My Events
            controller.switchToMyEvents();
        } else if (evt.getSource().equals(getMenuItems().get(3))) {  // My Artists
            // not implemented
        } else if (evt.getSource().equals(getMenuItems().get(4))) {  // My Venues
            // not implemented
        } else if (evt.getSource().equals(getMenuItems().get(5))) {  // My Followers
            // not implemented
        } else if (evt.getSource().equals(getMenuItems().get(6))) {  // Sign Out
            controller.switchToSplash();
        }
    }
}
