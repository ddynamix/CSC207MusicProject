package use_case.edit_post;

import entity.post.Post;

public class EditPostInputData {
    private final Post postToAlter;

    private final String title;
    private final String text;
    private final String media;
    private final String postDate;


    public EditPostInputData(Post postToAlter) {
        this.postToAlter = postToAlter;

        this.title = "";
        this.text = "";
        this.postDate = "";
        this.media = "";
    }

    public EditPostInputData(Post postToAlter, String title, String text, String postDate, String media) {
        this.title = title;
        this.text = text;
        this.postDate = postDate;
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

    public String getPostDate() {return postDate.toString();}
}
