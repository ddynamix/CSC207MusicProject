package use_case.postMaker;

import entity.user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class PostMakerInputData {
    private final String title;
    private final String text;
    private final User author;
    private final LocalDateTime postDate;
    private final String attachedMedia;

    public PostMakerInputData(String title, String text, User author, LocalDateTime postDate, String attachedMedia) {
        this.title = title;
        this.text = text;
        this.author = author;
        this.postDate = postDate;
        this.attachedMedia = attachedMedia;
    }

    public LocalDateTime getPostDate() {
        return postDate;
    }

    public String getText() {
        return text;
    }

    public User getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getAttachedMedia() {
        return attachedMedia;
    }
}
