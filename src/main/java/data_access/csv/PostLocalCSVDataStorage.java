package data_access.csv;

import app.interface_adapter_tools.UserSession;

import data_access.PostDataAccessInterface;
import data_access.UserDataAccessInterface;
import data_access.PostDoesntExistException;

import entity.post.Post;
import entity.user.User;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Post DAO
 */
public class PostLocalCSVDataStorage implements PostDataAccessInterface {
    private final File csvFile;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<String, Post> posts = new HashMap<>();

    private final UserDataAccessInterface userDataAccessObject;

    /**
     * create DAO for posts
     * @param csvPath file path
     * @param userDataAccessObject user DAO
     * @throws IOException input output exception for false types and parameters
     */
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

    /**
     * create CSV file
     */
    public void createFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(csvFile))) {
            writer.println(headersToString(headers));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add post to CSV
     * @param post to be added
     */
    private void appendPostToCsv(Post post) {
        try (FileWriter fw = new FileWriter(csvFile, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(postToString(post));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * convert post object to string
     * @param post to be converted
     * @return formatted string
     */
    private String postToString(Post post) {
        return post.getTitle() + ","
                + post.getText() + ","
                + post.getAuthor() + ","
                + post.getTimePosted().format(formatter) + ","
                + post.getAttachedMedia();
    }

    /**
     * Convert header to string
     * @param headers to be converted
     * @return formatted string
     */
    private String headersToString(Map<String, Integer> headers) {
        return String.join(",", headers.keySet());
    }

    /**
     * find User object from username
     * @param author username of the user
     * @return User of given username
     */
    private User stringToUser(String author) {
        return userDataAccessObject.getUserFromUsername(author);
    }

    /**
     * Create post in CSV
     * @param post to be added
     */
    @Override
    public void createPost(Post post) {
        System.out.println(post.getTitle() + " " + post.getAuthor());
        appendPostToCsv(post);
        posts.put(post.getTitle(), post);
        post.getAuthor().addPost(post);
    }

    /**
     * check if post is in CSV
     * @param postTitle to be searched for
     * @return boolean
     */
    @Override
    public boolean postExists(String postTitle) {
        return posts.containsKey(postTitle);
    }

    /**
     * remove post from CSV
     * @param post to be removed
     * @throws PostDoesntExistException exception for post already not in CSV
     */
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

    /**
     * Change post in CSV
     * @param post original Post object
     * @param title new title
     * @param text new text content
     * @param media new media
     * @throws PostDoesntExistException exception for post not existing
     */
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

    /**
     * access all posts
     * @return arraylist of posts in database
     */
    @Override
    public ArrayList<Post> getPosts() {
        return new ArrayList<>(posts.values());
    }

    /**
     * remove post from CSV
     * @param postName post title to be removed
     * @throws IOException input output exception for types and parameters
     */
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

    /**
     * Find Post for its title
     * @param postName title of post
     * @return Post of given title
     */
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
