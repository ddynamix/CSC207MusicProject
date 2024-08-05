package use_case.edit_post;

import entity.post.Post;

public class EditPostInputData {
    private final Post postToAlter;

    private final String title;
    private final String text;
    private final String media;


    public EditPostInputData(Post postToAlter) {
        this.postToAlter = postToAlter;

        this.title = "";
        this.text = "";
        this.media = "";
    }

    public EditPostInputData(Post postToAlter, String title, String text, String media) {
        this.title = title;
        this.text = text;
        this.media = media;

        this.postToAlter = postToAlter;
    }

    public Post getPostToAlter() {
        return postToAlter;
    }

    public String getTitle() {
        return title;
    }

    public String getText() { return text;}

    public String getMedia() {
        return media;
    }
}
