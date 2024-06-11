package entity;

public class Post {
    private String title;
    private String text;
    private String author;
    private Integer rating = 0;
    private int id = 0;

    //private Media content;
    //private Event taggedEvent;

    public Post(String title, String text, String author) {
        this.title = title;
        this.text = text;
        this.author = author;
    }
}