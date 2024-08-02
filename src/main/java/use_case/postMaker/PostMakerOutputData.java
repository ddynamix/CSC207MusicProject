package use_case.postMaker;

import entity.event.Event;
import entity.post.Post;

import java.util.ArrayList;

public class PostMakerOutputData {
    private final ArrayList<Post> posts;

    public PostMakerOutputData(ArrayList<Post> posts) {
        this.posts = posts;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }
}
