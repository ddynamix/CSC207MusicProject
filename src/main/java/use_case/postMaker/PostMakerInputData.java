package use_case.postMaker;

import entity.user.User;

/**
 * input data for post use case
 */
public class PostMakerInputData {
    private final String title;
    private final String text;
    private final User author;
    private final String attachedMedia;

    /**
     * create instance of input data for post use case
     * @param title of post
     * @param text of post
     * @param author of post
     * @param attachedMedia of post
     */
    public PostMakerInputData(String title, String text, User author, String attachedMedia) {
        this.title = title;
        this.text = text;
        this.author = author;
        this.attachedMedia = attachedMedia;
    }

    /**
     * access text
     * @return text
     */
    public String getText() {
        return text;
    }

    /**
     * access author
     * @return User author
     */
    public User getAuthor() {
        return author;
    }

    /**
     * access title
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * access media
     * @return media
     */
    public String getAttachedMedia() {
        return attachedMedia;
    }
}
