package data_access.csv;

import data_access.PostDataAccessInterface;
import data_access.UserDataAccessInterface;
import data_access.UsersPostsRelationalAccessInterface;
import entity.post.Post;
import entity.user.User;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * DAO for access to posts compared to user
 */
public class UsersPostsRelationalCSVDataStorage implements UsersPostsRelationalAccessInterface {
    private final File csvFile;
    private final Map<String, Integer> headers = new LinkedHashMap<>();

    // The keys are the users and the values are a list of posts that they are attending.
    private final HashMap<User, ArrayList<Post>> relationships = new HashMap<>();

    /**
     * create new DAO
     * @param csvPath string to local file
     * @param userDataAccess DAO for users
     * @param postDataAccess DAO for posts
     */
    public UsersPostsRelationalCSVDataStorage(String csvPath, UserDataAccessInterface userDataAccess, PostDataAccessInterface postDataAccess) {
        csvFile = new File(csvPath);
        headers.put("user_id", 0);
        headers.put("post_id", 1);

        if (csvFile.length() == 0) {
            createFile();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                // This will ensure the file is correctly formatted
                String header = reader.readLine();
                assert header.equals(headersToString(headers));

                // This will load all relationships into the map.
                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String userId = String.valueOf(col[headers.get("user_id")]);
                    String postId = String.valueOf(col[headers.get("post_id")]);

                    User user = userDataAccess.getUserFromUsername(userId);
                    Post post = postDataAccess.getPostFromTitle(postId);
                    if (!relationships.containsKey(user)) {
                        relationships.put(user, new ArrayList<>());
                    }
                    relationships.get(user).add(post);
                }

                applyPostsToUsers();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Create new CSV if not implemented
     */
    private void createFile() {
        if (csvFile.length() == 0) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(csvFile))) {
                writer.println(headersToString(headers));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Convert CSV entry into string
     * @param headers data
     * @return formatted string
     */
    private String headersToString(Map<String, Integer> headers) {
        return String.join(",", headers.keySet());
    }

    /**
     * add all posts to their user
     */
    private void applyPostsToUsers() {
        for (Map.Entry<User, ArrayList<Post>> entry : relationships.entrySet()) {
            User user = entry.getKey();
            ArrayList<Post> posts = entry.getValue();
            for (Post post : posts) {
                user.addPost(post);
            }
        }
    }

    /**
     * add a post to its user
     * @param user author
     * @param post object to add
     */
    @Override
    public void addPost(User user, Post post) {
        if (!relationships.containsKey(user)) {
            relationships.put(user, new ArrayList<>());
        }
        relationships.get(user).add(post);
        user.addPost(post);
        try (PrintWriter writer = new PrintWriter(new FileWriter(csvFile, true))) {
            writer.println(user.getUsername() + "," + post.getTitle());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * remove for CSV
     * @param user author of post
     * @param post object to be removed
     */
    @Override
    public void removePost(User user, Post post) {
        if (relationships.containsKey(user)) {
            relationships.get(user).remove(post);
            user.removePost(post);
            try (PrintWriter writer = new PrintWriter(new FileWriter(csvFile))) {
                writer.println(headersToString(headers));
                for (Map.Entry<User, ArrayList<Post>> entry : relationships.entrySet()) {
                    User u = entry.getKey();
                    ArrayList<Post> posts = entry.getValue();
                    for (Post e : posts) {
                        writer.println(u.getUsername() + "," + e.getTitle());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
