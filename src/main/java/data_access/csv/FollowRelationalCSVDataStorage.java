package data_access.csv;

import data_access.FollowRelationalAccessInterface;
import data_access.UserDataAccessInterface;
import entity.user.User;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FollowRelationalCSVDataStorage implements FollowRelationalAccessInterface {
    private final File csvFile;
    private final Map<String, Integer> headers = new LinkedHashMap<>();

    //  The keys are the followees and the values are a list of people that follow them.
    private final HashMap<User, ArrayList<User>> relationships = new HashMap<>();

    private final UserDataAccessInterface userDataAccess;

    public FollowRelationalCSVDataStorage(String csvPath, UserDataAccessInterface userDataAccess) throws IOException {
        csvFile = new File(csvPath);
        headers.put("follower_id", 0);
        headers.put("followee_id", 1);

        this.userDataAccess = userDataAccess;

        if (csvFile.length() == 0) {
            createFile();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                // This will ensure the file is correctly formatted
                String header = reader.readLine();
                assert header.equals(headersToString(headers));

                // This will load all users into memory as User objects
                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String followeeId = String.valueOf(col[headers.get("followee_id")]);
                    String followerId = String.valueOf(col[headers.get("follower_id")]);

                    User followee = userDataAccess.getUserFromUsername(followeeId);
                    User follower = userDataAccess.getUserFromUsername(followerId);
                    if (!relationships.containsKey(followee)) {
                        relationships.put(followee, new ArrayList<>());
                    }
                    relationships.get(followee).add(follower);
                }

                applyFollowingToUsers();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void createFile() {
        if (csvFile.length() == 0) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(csvFile))) {
                writer.println(headersToString(headers));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String headersToString(Map<String, Integer> headers) {
        return String.join(",", headers.keySet());
    }

    private void applyFollowingToUsers() {
        for (Map.Entry<User, ArrayList<User>> entry : relationships.entrySet()) {
            User followee = entry.getKey();
            ArrayList<User> followers = entry.getValue();
            for (User follower : followers) {
                followee.addFollower(follower);
                follower.addFollowing(followee);
            }
        }
    }

    @Override
    public void addFollower(User follower, User followee) {
        if (!relationships.containsKey(followee) || !relationships.get(followee).contains(follower)) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(csvFile, true))) {
                if (!relationships.containsKey(followee)) {
                    relationships.put(followee, new ArrayList<>());
                }
                relationships.get(followee).add(follower);
                follower.addFollowing(followee);
                followee.addFollower(follower);
                writer.println(follower.getUsername() + "," + followee.getUsername());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("You already follow this user.");
        }
    }

    @Override
    public void removeFollower(User follower, User followee) {
        if (relationships.containsKey(followee) && relationships.get(followee).contains(follower)) {
            relationships.get(followee).remove(follower);
            follower.removeFollowing(followee);
            followee.removeFollower(follower);
            try (PrintWriter writer = new PrintWriter(new FileWriter(csvFile))) {
                writer.println(headersToString(headers));
                for (Map.Entry<User, ArrayList<User>> entry : relationships.entrySet()) {
                    for (User user : entry.getValue()) {
                        writer.println(user.getUsername() + "," + entry.getKey().getUsername());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("You do not follow this user.");
        }
    }
}
