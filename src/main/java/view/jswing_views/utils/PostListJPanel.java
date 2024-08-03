package view.jswing_views.utils;

import entity.post.Post;

import javax.swing.*;
import java.awt.*;

public class PostListJPanel extends JPanel {
    private final Post post;

    public PostListJPanel(Post post) {
        this.post = post;

        String title = post.getTitle();
        String text = post.getText();
        String postDate = post.getDateAndTimeString();
        String media = post.getAttachedMedia();

        this.setPreferredSize(new Dimension(200, 150));
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.GRAY);
        GridBagConstraints c = new GridBagConstraints();

        JLabel titleLabel = new JLabel(title);
        titleLabel.setBackground(Color.GRAY);
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 0.8;
        c.weighty = 0.2;
        c.anchor = GridBagConstraints.PAGE_START;
        c.insets = new Insets(5, 5, 0, 0);
        this.add(titleLabel, c);

        JLabel textLabel = new JLabel(text);
        textLabel.setBackground(Color.GRAY);
        textLabel.setForeground(Color.DARK_GRAY);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.2;
        c.weighty = 0.2;
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        c.insets = new Insets(5, 5, 0, 0);
        this.add(textLabel, c);

        JLabel mediaLabel = new JLabel(media);
        mediaLabel.setBackground(Color.GRAY);
        mediaLabel.setForeground(Color.DARK_GRAY);
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0.8;
        c.weighty = 0.2;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(5, 5, 0, 0);
        this.add(mediaLabel, c);

        JLabel dateLabel = new JLabel(postDate);
        dateLabel.setBackground(Color.GRAY);
        dateLabel.setForeground(Color.DARK_GRAY);
        c.gridx = 2;
        c.gridy = 1;
        c.weightx = 0.8;
        c.weighty = 0.2;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(5, 5, 0, 0);
        this.add(dateLabel, c);
    }

    public Post getPost() {
        return post;
    }
}
