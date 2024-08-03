package use_case.postMaker;

import entity.user.User;

public class PostMakerInputData {
    private final String title;
    private final String text;
    private final User author;
    private final String attachedMedia;

    public PostMakerInputData(String title, String text, User author, String attachedMedia) {
        this.title = title;
        this.text = text;
        this.author = author;
        this.attachedMedia = attachedMedia;
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
