package data_access.csv;

import data_access.EventDataAccessInterface;
import data_access.UserDataAccessInterface;
import data_access.UsersEventsRelationalAccessInterface;
import entity.event.Event;
import entity.user.User;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class UsersEventsRelationalCSVDataStorage implements UsersEventsRelationalAccessInterface {
    private final File csvFile;
    private final Map<String, Integer> headers = new LinkedHashMap<>();

    // The keys are the users and the values are a list of events that they are attending.
    private final HashMap<User, ArrayList<Event>> relationships = new HashMap<>();

    public UsersEventsRelationalCSVDataStorage(String csvPath, UserDataAccessInterface userDataAccess, EventDataAccessInterface eventDataAccess) {
        csvFile = new File(csvPath);
        headers.put("user_id", 0);
        headers.put("event_id", 1);

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
                    String eventId = String.valueOf(col[headers.get("event_id")]);

                    User user = userDataAccess.getUserFromUsername(userId);
                    Event event = eventDataAccess.getEventFromTitle(eventId);
                    if (!relationships.containsKey(user)) {
                        relationships.put(user, new ArrayList<>());
                    }
                    relationships.get(user).add(event);
                }

                applyEventsToUsers();

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

    private void applyEventsToUsers() {
        for (Map.Entry<User, ArrayList<Event>> entry : relationships.entrySet()) {
            User user = entry.getKey();
            ArrayList<Event> events = entry.getValue();
            for (Event event : events) {
                user.addEvent(event);
            }
        }
    }

    @Override
    public void addEvent(User user, Event event) {
        if (!relationships.containsKey(user)) {
            relationships.put(user, new ArrayList<>());
        }
        relationships.get(user).add(event);
        user.addEvent(event);
         try (PrintWriter writer = new PrintWriter(new FileWriter(csvFile, true))) {
            writer.println(user.getUsername() + "," + event.getTitle());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeEvent(User user, Event event) {
        if (relationships.containsKey(user)) {
            relationships.get(user).remove(event);
            user.removeEvent(event);
            try (PrintWriter writer = new PrintWriter(new FileWriter(csvFile))) {
                writer.println(headersToString(headers));
                for (Map.Entry<User, ArrayList<Event>> entry : relationships.entrySet()) {
                    User u = entry.getKey();
                    ArrayList<Event> events = entry.getValue();
                    for (Event e : events) {
                        writer.println(u.getUsername() + "," + e.getTitle());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
