package view.jswing_views.utils;

import javax.swing.*;
import java.awt.*;

/**
 * create image panel
 */
public class SplashBackgroundPanel extends JPanel {
    private Image backgroundImage;

    /**
     * create instance of background panel
     * @param backgroundImage image to display
     */
    public SplashBackgroundPanel(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            // Get the dimensions of the panel and the image
            int panelWidth = getWidth();
            int panelHeight = getHeight();
            int imgWidth = backgroundImage.getWidth(null);
            int imgHeight = backgroundImage.getHeight(null);

            // Calculate the scale factor to maintain aspect ratio and fill the panel
            double scale = Math.max((double) panelWidth / imgWidth, (double) panelHeight / imgHeight);

            // Calculate the new image size
            int newImgWidth = (int) (imgWidth * scale);
            int newImgHeight = (int) (imgHeight * scale);

            // Calculate the x and y coordinates for the image to be centered
            int x = (panelWidth - newImgWidth) / 2;
            int y = (panelHeight - newImgHeight) / 2;

            // Draw the image
            g.drawImage(backgroundImage, x, y, newImgWidth, newImgHeight, this);
        }
    }
}
