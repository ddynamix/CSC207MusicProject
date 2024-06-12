package entity.post;

import entity.user.User;

import java.time.LocalDateTime;

public class PostFactory {
    public Post createPost(String title, String text, User author, LocalDateTime timePosted, String attachedMedia) {
        int id = 0; //Placeholder value
        int rating = 0; //Placeholder value
        return new Post(title, text, author, timePosted, id, attachedMedia, rating);
    }
}