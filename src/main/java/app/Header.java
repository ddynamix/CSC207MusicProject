package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Header extends JPanel implements ActionListener {
    private JButton waffleButton;
    private ArrayList<JMenuItem> options = new ArrayList<>();
    private JPopupMenu menu;
    private JLabel title;
    public String code;

    public Header(String title) {
        this.waffleButton = createWaffleButton();
        this.title = new JLabel(title);
        this.code = "";

        // Add menu items
        this.options.add(new JMenuItem("Home"));
        this.options.add(new JMenuItem("My Profile"));
        this.options.add(new JMenuItem("My Events"));
        this.options.add(new JMenuItem("My Artists"));
        this.options.add(new JMenuItem("My Venues"));
        this.options.add(new JMenuItem("My Followers"));
        this.options.add(new JMenuItem("Sign Out"));

        // Create menu and add items to it
        this.menu = createMenu(this.options);

        // Set up the panel layout
        this.setLayout(new FlowLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(this.waffleButton);

        this.add(this.title, BorderLayout.WEST);
        this.add(buttonPanel, BorderLayout.EAST);

        // Add mouse listener to the button to show the menu
        this.waffleButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                menu.show(waffleButton, e.getX(), e.getY());
            }
        });

        this.setSize(200, 200);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
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
            private final int width = 20;
            private final int height = 20;

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

        if (evt.getSource().equals(getMenuItems().get(0))){
            code = "home";
        } else if (evt.getSource().equals(getMenuItems().get(1))){
            code = "profile";
        } else if (evt.getSource().equals(getMenuItems().get(2))){
            code = "events";
        } else if (evt.getSource().equals(getMenuItems().get(3))){
            code = "artists";
        } else if (evt.getSource().equals(getMenuItems().get(4))){
            code = "venues";
        } else if (evt.getSource().equals(getMenuItems().get(5))){
            code = "followers";
        } else if (evt.getSource().equals(getMenuItems().get(6))){
            code = "sign out";
        }
    }
}
