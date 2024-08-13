package entity.post;

import entity.user.User;

import java.time.LocalDateTime;

/**
 * interface for post object
 */
public interface IPost {
    public String getTitle();
    public String getText();
    public User getAuthor();
    public int getId();
    public LocalDateTime getTimePosted();
    public String getAttachedMedia(); // Change to media object

}
