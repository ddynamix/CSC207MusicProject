package dataaccess;

import entity.user.ArtistUser;
import entity.user.AudienceUser;
import entity.user.User;
import entity.user.VenueUser;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class TEMPFileAccessDataStorage implements UserDataAccessInterface {

    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, User> accounts = new HashMap<>();

    public TEMPFileAccessDataStorage(String csvPath) throws IOException {

        csvFile = new File(csvPath);
        headers.put("username", 0);
        headers.put("password", 1);
        headers.put("email", 2);
        headers.put("name", 3);
        headers.put("creation_time", 4);

        if (csvFile.length() == 0) {
            createFile();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                // This will ensure the file is correctly formatted
                String header = reader.readLine();
                assert header.equals("username,password,email,name,creation_time");

                // This will load all users into memory
                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String username = String.valueOf(col[headers.get("username")]);
                    String password = String.valueOf(col[headers.get("password")]);
                    String email = String.valueOf(col[headers.get("email")]);
                    String name = String.valueOf(col[headers.get("name")]);
                    String creationTime = String.valueOf(col[headers.get("creation_time")]);

                    User user = new User(name, username, password, email);
                    accounts.put(username, user);

                }
            }
        }
    }

    public void createFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(csvFile))) {
            writer.println("username,password,email,name,creation_time");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void appendUserToCsv(String username, String password, String email, String name) {
        try (FileWriter fw = new FileWriter(csvFile, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(username + "," + password + "," + email + "," + name + "," + System.currentTimeMillis());
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }
    }

    @Override
    public boolean userExistsInDatabase(String username) {
        return accounts.containsKey(username);
    }

    @Override
    public void updateUsername(User user, String newUsername) {

    }

    @Override
    public void updatePassword(User user, String newPassword, String confirmPassword) {

    }

    @Override
    public void updateEmail(User user, String newEmail) {

    }

    @Override
    public void create(User user) throws UserDataAccessObject.DuplicateUsernameException {
        if (!userExistsInDatabase(user.getUsername())) {
            accounts.put(user.getUsername(), user);
            appendUserToCsv(user.getUsername(), user.getPassword(), user.getEmail(), "name");
        }
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public boolean passwordMatches(String username, String password) {
        return accounts.get(username).getPassword().equals(password);
    }

    @Override
    public void Throwable(UserDataAccessObject.UserNotFoundException userNotFoundException) {

    }

}
