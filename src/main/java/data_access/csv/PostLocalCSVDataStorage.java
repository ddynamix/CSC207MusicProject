package data_access.csv;

import app.interface_adapter_tools.UserSession;

import data_access.PostDataAccessInterface;
import data_access.UserDataAccessInterface;
import data_access.PostDoesntExistException;

import entity.event.Event;
import entity.post.Post;
import entity.user.User;
import org.mockito.internal.matchers.Null;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PostLocalCSVDataStorage implements PostDataAccessInterface {
    private final File csvFile;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<String, Post> posts = new HashMap<>();

    private final UserDataAccessInterface userDataAccessObject;

    public PostLocalCSVDataStorage(String csvPath, UserDataAccessInterface userDataAccessObject) throws IOException {
        this.userDataAccessObject = userDataAccessObject;
        csvFile = new File(csvPath);

        headers.put("title", 0);
        headers.put("text", 1);
        headers.put("author", 2);
        headers.put("postDate", 3);
        headers.put("attachedMedia", 4);

        if (headers.size() != 5){
            throw new ArrayIndexOutOfBoundsException("The headers array is improperly initialized - should be 5 elements");
        } else if (csvFile.length() == 0) {
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

//                    if (headers.containsKey("title")){System.out.println("title " + col[0] + ".");}
//                    if (headers.containsKey("text")) {System.out.println("text " + col[1] + ".");}
//                    if (headers.containsKey("author")){System.out.println("author " + col[2] + ".");}
//                    if (headers.containsKey("postDate")){System.out.println("postDate " + col[3] + ".");}
//                    if (headers.containsKey("attachedMedia")){System.out.println("attachedMedia " + col[4] + ".");}

                    if (col.length != 5){
                        System.out.println("Length = " + col.length + "\n" + Arrays.toString(col));
                        throw new ArrayIndexOutOfBoundsException("it should have a length of 5" + col);
                    }
                    String title = String.valueOf(col[headers.get("title")]);
                    String text = String.valueOf(col[headers.get("text")]);
                    User author = stringToUser(String.valueOf(col[headers.get("author")]));
                    String attachedMedia = String.valueOf(col[headers.get("attachedMedia")]);

                    Post post = new Post(title, text, author, attachedMedia);
                    posts.put(title, post);
                    author.addPost(post); //a
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
        System.out.println(post.getTitle() + " " + post.getAuthor());
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
    public void updatePost(Post post, String title, String text, String media) throws PostDoesntExistException {
        try {
            deletePostFromCsv(post.getTitle());
            if (!(title == null || title.isEmpty())) {
                post.setTitle(title);
            }
            if (!(text == null || text.isEmpty())) {
                post.setText(text);
            }
            if (!(media == null || media.isEmpty())) {
                post.setAttachedMedia(media);
            }
            appendPostToCsv(post);
        } catch (IOException e) {
            e.printStackTrace();
        } catch(NullPointerException e) {
            System.out.println("Post does not exist");
        }

    }

    @Override
    public ArrayList<Post> getPosts() {
        return new ArrayList<>(posts.values());
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

}
