package use_case.edit_post;

import entity.post.Post;

/**
 * input data for edit post use case
 */
public class EditPostInputData {
    private final Post postToAlter;

    private final String title;
    private final String text;
    private final String media;


    /**
     * create instance of input data for edit post use case
     * @param postToAlter data
     */
    public EditPostInputData(Post postToAlter) {
        this.postToAlter = postToAlter;

        this.title = "";
        this.text = "";
        this.media = "";
    }

    /**
     * update data
     * @param postToAlter current post
     * @param title of post
     * @param text of post
     * @param media of post
     */
    public EditPostInputData(Post postToAlter, String title, String text, String media) {
        this.title = title;
        this.text = text;
        this.media = media;

        this.postToAlter = postToAlter;
    }

    /**
     * access current post
     * @return current post
     */
    public Post getPostToAlter() {
        return postToAlter;
    }

    /**
     * access title
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * access text
     * @return text
     */
    public String getText() { return text;}

    /**
     * access media
     * @return media
     */
    public String getMedia() {
        return media;
    }
}
