package data_access.csv;

import com.opencsv.exceptions.CsvValidationException;
import data_access.UserAlreadyExistsException;
import data_access.UserDataAccessInterface;
import data_access.mongodb.UserDataAccessObject;
import entity.user.ArtistUser;
import entity.user.AudienceUser;
import entity.user.User;
import entity.user.VenueUser;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * User local CSV DAO
 */
public class UserLocalCSVDataStorage implements UserDataAccessInterface {

    private final File csvFile;
    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final HashMap<String, User> accounts = new HashMap<>();
    private final String file_name;

    /**
     * create instance of user DAO
     * @param csvPath file path of data
     * @throws IOException input output exception for types and parameters
     */
    public UserLocalCSVDataStorage(String csvPath) throws IOException {

        file_name = csvPath;
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
                assert header.equals(headersToString(headers));

                // This will load all users into memory as User objects
                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String username = String.valueOf(col[headers.get("username")]);
                    String password = String.valueOf(col[headers.get("password")]);
                    String email = String.valueOf(col[headers.get("email")]);
                    String name = String.valueOf(col[headers.get("name")]);
                    String type = String.valueOf(col[headers.get("type")]);

                    if (type.equals("artistuser")) {
                        User user = new ArtistUser(name, username, password, email);
                        accounts.put(username, user);
                    } else if (type.equals("venueuser")) {
                        User user = new VenueUser(name, username, password, email);
                        accounts.put(username, user);
                    } else {
                        User user = new AudienceUser(name, username, password, email);
                        accounts.put(username, user);
                    }
                }
            }
        }
    }

    /**
     * create new CSV file
     */
    public void createFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(csvFile))) {
            writer.println(headersToString(headers));
        } catch (IOException e) {
            e.printStackTrace();
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

    @SuppressWarnings("unchecked")
    @Override
    public <T extends User> T getUserFromUsername(String username) {
        // printHashMap(accounts);
        if (userExistsInDatabase(username)) {
            return (T) accounts.get(username);
        } else {
            System.err.println("User not found in database.");
            return null;
        }
    }

    private String headersToString(Map<String, Integer> headers) {
        return String.join(",", headers.keySet());
    }

    @Override
    public boolean userExistsInDatabase(String username) {
        return accounts.containsKey(username);
    }

    @Override
    public void create(User user) throws UserAlreadyExistsException {
        if (!userExistsInDatabase(user.getUsername())) {
            accounts.put(user.getUsername(), user);
            appendUserToCsv(user.getUsername(), user.getPassword(), user.getEmail(), user.getName(), user.getClass().getSimpleName().toLowerCase());
        } else {
            throw new UserAlreadyExistsException();
        }
    }


    @Override
    public boolean passwordMatches(String username, String password) {
        return accounts.get(username).getPassword().equals(password);
    }

    @Override
    public void updateUser(User userToAlter, String email, String username, String name) {
        String old_username = userToAlter.getUsername();
        if (!userExistsInDatabase(username)) {
//            userToAlter.setName(name);
//            userToAlter.setUsername(username);
//            userToAlter.setEmail(email);

            List<String[]> allRows = new ArrayList<>();
            boolean isUpdated = false;
            // Initialize readers and writers
            try {
                CSVReader reader = new CSVReader(new FileReader(this.file_name));
                String[] nextLine;

                while ((nextLine = reader.readNext()) != null) {
                    // Check if the current row contains the username

                    if (nextLine[0].equals(old_username)) {
                        // Replace the row with the new data
                        if (!username.isEmpty()) {
                            nextLine[0] = username;
                        }

                        if (!email.isEmpty()){
                            nextLine[2] = email;
                        }
                        if (!name.isEmpty()){
                            nextLine[3] = name;
                        }

                        for (String value : nextLine) {
                            System.out.print(value + " ");
                        }
                        System.out.println();
                        allRows.add(nextLine);
                        isUpdated = true;
                    } else {
                        allRows.add(nextLine);
                    }
                }
                reader.close();
            } catch (FileNotFoundException ex) {
                System.out.println("File not found.");
            } catch (CsvValidationException e) {
                System.out.println("Leave me alone.");
            } catch (IOException e) {
                System.out.println("Please help.");
            }


            // Read all rows from the CSV


            if (isUpdated) {
                // Write back the updated rows to the CSV
                try {
                    CSVWriter writer = new CSVWriter(new FileWriter(this.file_name),CSVWriter.DEFAULT_SEPARATOR,  // Separator
                            CSVWriter.NO_QUOTE_CHARACTER, // No quotes
                            CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                            CSVWriter.DEFAULT_LINE_END);
                    writer.writeAll(allRows);
                    writer.close();
                } catch (IOException ex){
                    System.out.println("Cannot write.");
                }

            } else {
                System.out.println("Cringe.");
            }
            userToAlter.setName(name);
            userToAlter.setUsername(username);
            userToAlter.setEmail(email);
        } else {
            System.out.println("What you doing bruv.");
        }
    }

    @Override
    public void delete(User original) throws UserDataAccessObject.UserNotFoundException {

    }

    @Override
    public ArrayList<ArtistUser> getArtistUsers() {
        ArrayList<ArtistUser> artistUsers = new ArrayList<>();
        for (User user : accounts.values()) {
            if (user instanceof ArtistUser) {
                artistUsers.add((ArtistUser) user);
            }
        }
        return artistUsers;
    }

    @Override
    public ArrayList<VenueUser> getVenueUsers() {
        ArrayList<VenueUser> venueUsers = new ArrayList<>();
        for (User user : accounts.values()) {
            if (user instanceof VenueUser) {
                venueUsers.add((VenueUser) user);
            }
        }
        return venueUsers;
    }

    @Override
    public ArrayList<AudienceUser> getAudienceUsers() {
        ArrayList<AudienceUser> audienceUsers = new ArrayList<>();
        for (User user : accounts.values()) {
            if (user instanceof AudienceUser) {
                audienceUsers.add((AudienceUser) user);
            }
        }
        return audienceUsers;
    }

    @Override
    public ArrayList<User> getAllUsers() {
        return new ArrayList<>(accounts.values());
    }

    private <K, V> void printHashMap(HashMap<K, V> map) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }
}
