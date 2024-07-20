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
        headers.put("type", 5);

        if (csvFile.length() == 0) {
            createFile();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                // This will ensure the file is correctly formatted
                String header = reader.readLine();
                assert header.equals("username,password,email,name,creation_time,type");

                // This will load all users into memory
                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String username = String.valueOf(col[headers.get("username")]);
                    String password = String.valueOf(col[headers.get("password")]);
                    String email = String.valueOf(col[headers.get("email")]);
                    String name = String.valueOf(col[headers.get("name")]);
                    String creationTime = String.valueOf(col[headers.get("creation_time")]);
                    String type = String.valueOf(col[headers.get("type")]);

                    if (type.equals("audience")) {
                        AudienceUser user = new AudienceUser(username, password, email, name, name);
                        accounts.put(username, user);
                    } else if (type.equals("artist")) {
                        ArtistUser user = new ArtistUser(username, password, email, name);
                        accounts.put(username, user);
                    } else if (type.equals("venue")) {
                        VenueUser user = new VenueUser(username, password, email, name, name);
                        accounts.put(username, user);
                    }

                }
            }
        }
    }

    @Override
    public void createFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(csvFile))) {
            writer.println("username,password,email,name,creation_time,type");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveAudienceUser(AudienceUser user) {
        if (!userExistsInDatabase(user.getUsername())) {
            accounts.put(user.getUsername(), user);
            appendUserToCsv(user.getUsername(), user.getPassword(), user.getEmail(), user.getFirstName(), "audience");
        }
    }

    @Override
    public void saveArtistUser(ArtistUser user) {
        if (!userExistsInDatabase(user.getUsername())) {
            accounts.put(user.getUsername(), user);
            appendUserToCsv(user.getUsername(), user.getPassword(), user.getEmail(), user.getStageName(), "artist");
        }
    }

    @Override
    public void saveVenueUser(VenueUser user) {
        if (!userExistsInDatabase(user.getUsername())) {
            accounts.put(user.getUsername(), user);
            appendUserToCsv(user.getUsername(), user.getPassword(), user.getEmail(), user.getVenueName(), "venue");
        }
    }

    private void appendUserToCsv(String username, String password, String email, String name, String type) {
        try (FileWriter fw = new FileWriter(csvFile, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(username + "," + password + "," + email + "," + name + "," + System.currentTimeMillis() + "," + type);
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
    public void delete(User user) {

    }

    @Override
    public String[] getUserData(User user) {
        return new String[0];
    }

    @Override
    public boolean passwordMatches(String username, String password) {
        return accounts.get(username).getPassword().equals(password);
    }

    @Override
    public void Throwable(UserDataAccessObject.UserNotFoundException userNotFoundException) {

    }

}
