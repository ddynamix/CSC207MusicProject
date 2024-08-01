package dataaccess;

import entity.user.AudienceUser;
import entity.user.User;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class TEMPFileAccessDataStorage {

    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, User> accounts = new HashMap<>();

    public TEMPFileAccessDataStorage(String csvPath) throws IOException {

        csvFile = new File(csvPath);
        headers.put("username", 0);
        headers.put("password", 1);
        headers.put("creation_time", 2);

        if (csvFile.length() == 0) {
            create();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                // TODO clean this up by creating a new Exception subclass and handling it in the UI.
                assert header.equals("username,password,creation_time");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String username = String.valueOf(col[headers.get("username")]);
                    String password = String.valueOf(col[headers.get("password")]);
                    String email = String.valueOf(col[headers.get("email")]);
                    String firstName = String.valueOf(col[headers.get("first_name")]);
                    String lastName = String.valueOf(col[headers.get("last_name")]);

                    AudienceUser user = new AudienceUser(username, password, email, firstName, lastName);
                    accounts.put(username, user);
                }
            }
        }
    }

    public void create(User user) {
        accounts.put(user.getUsername(), user);
        this.create();
    }

    private void create() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean userExistsInDatabase(String identifier) {
        return accounts.containsKey(identifier);
    }

}
