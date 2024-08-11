package app.interface_adapter_tools;

import entity.user.User;

import java.awt.*;

public class Theme {
    public static final Color LIGHT_BACKGROUND = Color.WHITE;
    public static final Color LIGHT_FOREGROUND = Color.BLACK;

    public static final Color DARK_BACKGROUND = Color.DARK_GRAY;
    public static final Color DARK_FOREGROUND = Color.WHITE;

    public class ThemeManager {
        private static boolean isDarkMode = false;

        public static void applyTheme(Component component) {
            if (isDarkMode) {
                component.setBackground(Theme.DARK_BACKGROUND);
                component.setForeground(Theme.DARK_FOREGROUND);
            } else {
                component.setBackground(Theme.LIGHT_BACKGROUND);
                component.setForeground(Theme.LIGHT_FOREGROUND);
            }

            if (component instanceof Container) {
                for (Component child : ((Container) component).getComponents()) {
                    applyTheme(child);
                }
            }
        }

        public static void toggleTheme() {
            isDarkMode = !isDarkMode;
        }

        public static boolean isDarkMode() {
            return isDarkMode;
        }
    }

}

