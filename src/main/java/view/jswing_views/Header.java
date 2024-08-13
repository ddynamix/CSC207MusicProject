package view.jswing_views;

import app.interface_adapter_tools.Theme;
import use_case.screen_switcher.interface_adapter.ScreenSwitcherController;
import use_case.sign_out.interface_adapter.SignOutController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static app.interface_adapter_tools.Theme.ThemeManager.toggleTheme;

/**
 * create header JPanel to be added to frame view
 */
public class Header extends JPanel implements ActionListener {
    private final ScreenSwitcherController screenSwitcherController;
    private final SignOutController signOutController;

    private JButton waffleButton;
    private JPopupMenu menu;
    JButton colourMode;

    /**
     * create header panel
     * @param screenSwitcherController controller for switcher
     * @param signOutController controller for sign out use case
     */
    public Header(ScreenSwitcherController screenSwitcherController, SignOutController signOutController) {
        this.screenSwitcherController = screenSwitcherController;
        this.signOutController = signOutController;

        // Add menu items
        ArrayList<JMenuItem> options = new ArrayList<>();
        options.add(new JMenuItem("Home"));
        options.add(new JMenuItem("My Profile"));
        options.add(new JMenuItem("My Events"));
        options.add(new JMenuItem("Explore Events"));
        options.add(new JMenuItem("Following"));
        options.add(new JMenuItem("Followers"));
        options.add(new JMenuItem("Explore Users"));

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
        waffleButton.setToolTipText("Click to view different options");

        Dimension height = this.getPreferredSize();
        height.height = 50;
        this.setMaximumSize(height);

        // Set up the panel layout
        this.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        this.add(this.waffleButton);

        colourMode = new JButton("Toggle Colour Mode");
        colourMode.addActionListener(e -> {
            System.out.println("Toggle Theme button clicked"); // Debugging
            toggleTheme();
            app.Main.themeUpdate();
            Theme.ThemeManager.applyTheme(this);
            this.revalidate();  // Revalidate the component hierarchy
            this.repaint();     // Repaint to apply new colors
        });
        this.add(colourMode);
        Theme.ThemeManager.applyTheme(this);
    }

    /**
     * create menu for each of the functions in menu
     * @param items MenuItems to be added
     * @return menu
     */
    private JPopupMenu createMenu(ArrayList<JMenuItem> items) {
        JPopupMenu newMenu = new JPopupMenu();
        for (JMenuItem item : items) {
            item.addActionListener(this);
            newMenu.add(item);
        }
        return newMenu;
    }

    /**
     * Create button and icon with the menu
     * @return button
     */
    private static JButton createWaffleButton() {
        final int WIDTH = 30;
        final int HEIGHT = 30;

        Icon waffleIcon = new Icon() {
            private final int iconWidth = 20;
            private final int iconeight = 20;

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
                return iconWidth;
            }

            @Override
            public int getIconHeight() {
                return iconeight;
            }
        };

        JButton button = new JButton(waffleIcon);
        button.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        return button;
    }

    /**
     * access menu items
     * @return list of items
     */
    public ArrayList<JMenuItem> getMenuItems() {
        ArrayList<JMenuItem> menuItems = new ArrayList<>();
        for (Component component : menu.getComponents()) {
            if (component instanceof JMenuItem) {
                menuItems.add((JMenuItem) component);
            }
        }
        return menuItems;
    }

    /**
     * @param evt the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(getMenuItems().get(0))) {  // Home
            screenSwitcherController.switchToHome();
        } else if (evt.getSource().equals(getMenuItems().get(1))) {  // My Profile
            screenSwitcherController.switchToMyProfile();
        } else if (evt.getSource().equals(getMenuItems().get(2))) {  // My Events
            screenSwitcherController.switchToMyEvents();
        } else if (evt.getSource().equals(getMenuItems().get(3))) {  // Explore Events
            screenSwitcherController.switchToSearchEvents();
        } else if (evt.getSource().equals(getMenuItems().get(4))) {  // Following
            screenSwitcherController.switchToIsFollowing();
        } else if (evt.getSource().equals(getMenuItems().get(5))) {  // My Followers
            screenSwitcherController.switchToMyFollowers();
        } else if (evt.getSource().equals(getMenuItems().get(6))) {  // Explore Users
            screenSwitcherController.switchToSearchUsers();
        } else if (evt.getSource().equals(getMenuItems().get(7))) {  // SignOut
            signOutController.executeSignOut();
            screenSwitcherController.switchToSplash();
        }

        if (evt.getSource().equals(colourMode)) { // Dark Mode
            toggleTheme();
        }
    }
}
