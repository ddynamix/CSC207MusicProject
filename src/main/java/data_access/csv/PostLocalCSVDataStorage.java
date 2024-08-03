package data_access.csv;

import app.interface_adapter_tools.UserSession;

import data_access.PostDataAccessInterface;
import data_access.UserDataAccessInterface;
import data_access.PostDoesntExistException;

import entity.post.Post;
import entity.user.User;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PostLocalCSVDataStorage implements PostDataAccessInterface {
    private final File csvFile;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<String, Post> posts = new HashMap<>();

    private final UserDataAccessInterface userDataAccessObject;

    public PostLocalCSVDataStorage(String csvPath, UserDataAccessInterface userDataAccessObject) throws IOException {

        csvFile = new File(csvPath);
        this.userDataAccessObject = userDataAccessObject;
        headers.put("title", 0);
        headers.put("text", 1);
        headers.put("author", 2);
        headers.put("postData", 3);
        headers.put("attachedMedia", 4);

        if (csvFile.length() == 0) {
            createFile();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                // This will ensure the file is correctly formatted
                String header = reader.readLine();
                assert header.equals(headersToString(headers));

                // This will load all posts into memory as Post objects
                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String title = String.valueOf(col[headers.get("title")]);
                    String text = String.valueOf(col[headers.get("text")]);
                    User author = stringToUser(String.valueOf(col[headers.get("author")]));
                    String attachedMedia = String.valueOf(col[headers.get("attachedMedia")]);

                    Post post = new Post(title, text, author, attachedMedia);
                    posts.put(title, post);
                }
            }
        }
    }

    public void createFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(csvFile))) {
            writer.println(headersToString(headers));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void appendPostToCsv(Post post) {
        try (FileWriter fw = new FileWriter(csvFile, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(postToString(post));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String postToString(Post post) {
        return post.getTitle() + ","
                + post.getText() + ","
                + post.getAuthor() + ","
                + post.getTimePosted().format(formatter) + ","
                + post.getAttachedMedia();
    }

    private String headersToString(Map<String, Integer> headers) {
        return String.join(",", headers.keySet());
    }

    private User stringToUser(String author) {
        return userDataAccessObject.getUserFromUsername(author);
    }

    @Override
    public void createPost(Post post) {
        appendPostToCsv(post);
        posts.put(post.getTitle(), post);
        post.getAuthor().addPost(post);
    }

    @Override
    public boolean postExists(String postTitle) {
        return posts.containsKey(postTitle);
    }

    @Override
    public void deletePost(Post post) throws PostDoesntExistException {
        if (postExists(post.getTitle())) {
            try {
                posts.remove(post.getTitle());
                deletePostFromCsv(post.getTitle());
                UserSession.getInstance().getLoggedInUser().removePost(post);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Post does not exist");
            throw new PostDoesntExistException();
        }
    }

    @Override
    public void updatePost(Post post, String title, String text, String postDate, String media) throws PostDoesntExistException {
        if (!postExists(post.getTitle())) {
            System.out.println("Post does not exist");
        } else {
            try {
                deletePostFromCsv(post.getTitle());
                if (!(title == null || title.isEmpty())) {
                    post.setTitle(title);
                }
                if (!(text == null || text.isEmpty())) {
                    post.setText(text);
                }
                if (!(postDate == null || postDate.isEmpty())) {
                    post.setPostDate(postDate);
                }
                if (!(media == null || media.isEmpty())) {
                    post.setAttachedMedia(media);
                }
                appendPostToCsv(post);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void deletePostFromCsv(String postName) throws IOException {
        Map<String, Post> tempPosts = new HashMap<>();

        // Load existing posts except the one to be deleted
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String line;
            reader.readLine(); // Skip header
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (!data[headers.get("title")].equals(postName)) {
                    String title = data[headers.get("title")];
                    String text = data[headers.get("text")];
                    User author = userDataAccessObject.getUserFromUsername(data[headers.get("author")]);
                    LocalDateTime postDate = LocalDateTime.parse(data[headers.get("postData")], formatter);
                    String attachedMedia = data[headers.get("attachedMedia")];

                    Post post = new Post(title, text, author, attachedMedia);
                    tempPosts.put(title, post);
                }
            }
        }

        // Rewrite the CSV without the deleted post
        try (PrintWriter writer = new PrintWriter(new FileWriter(csvFile))) {
            writer.println(headersToString(headers));
            for (Post post : tempPosts.values()) {
                writer.println(postToString(post));
            }
        }
    }

    @Override
    public Post getPostFromTitle(String postName) {
        if (postExists(postName)) {
            return posts.get(postName);
        } else {
            System.out.println("Post does not exist");
            return null;
        }
    }

    @Override
    public ArrayList<Post> getPosts() {
        return new ArrayList<>(posts.values());
    }
}
