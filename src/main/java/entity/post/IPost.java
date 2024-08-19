package entity.post;

import entity.user.User;

import java.time.LocalDateTime;

/**
 * interface for post object
 */
public interface IPost {
    String getTitle();
    String getText();
    User getAuthor();
    int getId();
    LocalDateTime getTimePosted();
    String getAttachedMedia(); // Change to media object

}
