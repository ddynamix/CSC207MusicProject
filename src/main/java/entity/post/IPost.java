package entity.post;

import entity.user.User;

import java.time.LocalDateTime;

public interface IPost {
    public String getTitle();
    public String getText();
    public User getAuthor();
    public int getId();
    public int getRating();
    public LocalDateTime getTimePosted();
    public String getAttachedMedia(); // Change to media object

}
