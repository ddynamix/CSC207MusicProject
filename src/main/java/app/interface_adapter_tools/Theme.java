package app.interface_adapter_tools;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Singleton pattern for changing the Theme: colour changer across all views
 */
public class Theme {
    public static ArrayList<Component> overridenComponents = new ArrayList<>(); // Add components that need specific colours here.

    public static final Color LIGHT_BACKGROUND = Color.WHITE;
    public static final Color LIGHT_FOREGROUND = Color.BLACK;

    public static final Color DARK_BACKGROUND = Color.DARK_GRAY;
    public static final Color DARK_FOREGROUND = Color.WHITE;

    /**
     * Internal static class for changing the theme
     */
    public static class ThemeManager {
        private static boolean isDarkMode = false;

        /**
         * Make given component comply
         * @param component to have theme applied to
         */
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
                    if (!overridenComponents.contains(child)) {
                        applyTheme(child);
                    }
                }
            }
        }

        /**
         * Change to the opposite theme
         */
        public static void toggleTheme() {
            isDarkMode = !isDarkMode;
        }

        /**
         * Adds a component to the list of components that should be overriden
         *
         * @param component the component to be overriden
         */
        public static void addOverridenComponent(Component component) {
            overridenComponents.add(component);
        }
    }
}
