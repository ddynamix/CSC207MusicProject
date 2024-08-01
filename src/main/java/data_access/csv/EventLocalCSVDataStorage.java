package data_access.csv;

import data_access.EventAlreadyExistsException;
import data_access.EventDataAccessInterface;
import data_access.EventDoesntExistException;
import data_access.UserDataAccessInterface;
import entity.event.Event;
import entity.user.ArtistUser;
import entity.user.User;
import entity.user.VenueUser;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class EventLocalCSVDataStorage implements EventDataAccessInterface {
    private final File csvFile;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<String, Event> events = new HashMap<>();

    private final UserDataAccessInterface userDataAccessObject;

    public EventLocalCSVDataStorage(String csvPath, UserDataAccessInterface userDataAccessObject) throws IOException {
        this.userDataAccessObject = userDataAccessObject;

        csvFile = new File(csvPath);
        headers.put("title", 0);
        headers.put("artist", 1);
        headers.put("venue", 2);
        headers.put("dateAndTime", 3);
        headers.put("description", 4);
        headers.put("tags", 5);
        headers.put("postDate", 6);
        headers.put("attachedMedia", 7);

        if (csvFile.length() == 0) {
            createFile();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                // This will ensure the file is correctly formatted
                String header = reader.readLine();
                assert header.equals(headersToString(headers));

                // This will load all events into memory as Event objects
                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String title = String.valueOf(col[headers.get("title")]);
                    ArtistUser artist = userDataAccessObject.getUserFromUsername(String.valueOf(col[headers.get("artist")]));
                    VenueUser venue = userDataAccessObject.getUserFromUsername(String.valueOf(col[headers.get("venue")]));
                    LocalDateTime dateAndTime = LocalDateTime.parse(String.valueOf(col[headers.get("dateAndTime")]), formatter);
                    String description = String.valueOf(col[headers.get("description")]);
                    ArrayList<String> tags = stringToArrayList(String.valueOf(col[headers.get("tags")]));
                    LocalDateTime postDate = LocalDateTime.parse(String.valueOf(col[headers.get("postDate")]), formatter);
                    String attachedMedia = String.valueOf(col[headers.get("attachedMedia")]);

                    Event event = new Event(title, artist, venue, dateAndTime, description, tags, postDate, attachedMedia);
                    events.put(title, event);

                    artist.addEvent(event);
                    venue.addEvent(event);
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

    private void appendEventToCsv(Event event) {
        try (FileWriter fw = new FileWriter(csvFile, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(eventToString(event));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String eventToString(Event event) {
        return event.getTitle() + ","
                + event.getArtist().getUsername() + ","
                + event.getVenue() + ","
                + event.getDateAndTime().format(formatter) + ","
                + event.getDescription() + ","
                + arrayListToString(event.getTags()) + ","
                + event.getPostDate().format(formatter) + ","
                + event.getAttachedMedia();
    }

    private String headersToString(Map<String, Integer> headers) {
        return String.join(",", headers.keySet());
    }

    private ArrayList<String> stringToArrayList(String tags) {
        return new ArrayList<>(Arrays.asList(tags.split(";")));
    }

    private String arrayListToString(ArrayList<String> tags) {
        return String.join(";", tags);
    }

    @Override
    public boolean eventExists(String eventName) {
        return events.containsKey(eventName);
    }

    @Override
    public void createEvent(Event event) throws EventAlreadyExistsException {
        if (!eventExists(event.getTitle())) {
            System.out.println(event.getArtist().getUsername());
            appendEventToCsv(event);
            events.put(event.getTitle(), event);
            event.getArtist().addEvent(event);
            event.getVenue().addEvent(event);
        } else {
            System.out.println("Event already exists");
            throw new EventAlreadyExistsException();
        }
    }

    @Override
    public void deleteEvent(Event event) throws EventDoesntExistException {
        if (eventExists(event.getTitle())) {
            try {
                events.remove(event.getTitle());
                deleteEventFromCsv(event.getTitle());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Event does not exist");
            throw new EventDoesntExistException();
        }
    }

    public void deleteEventFromCsv(String eventName) throws IOException {
        Map<String, Event> tempEvents = new HashMap<>();

        // Load existing events except the one to be deleted
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String line;
            reader.readLine(); // Skip header
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (!data[headers.get("title")].equals(eventName)) {
                    String title = data[headers.get("title")];
                    ArtistUser artist = userDataAccessObject.getUserFromUsername(data[headers.get("artist")]);
                    VenueUser venue = userDataAccessObject.getUserFromUsername(data[headers.get("venue")]);
                    LocalDateTime dateAndTime = LocalDateTime.parse(data[headers.get("dateAndTime")], formatter);
                    String description = data[headers.get("description")];
                    ArrayList<String> tags = stringToArrayList(data[headers.get("tags")]);
                    LocalDateTime postDate = LocalDateTime.parse(data[headers.get("postDate")], formatter);
                    String attachedMedia = data[headers.get("attachedMedia")];

                    Event event = new Event(title, artist, venue, dateAndTime, description, tags, postDate, attachedMedia);
                    tempEvents.put(title, event);
                }
            }
        }

        // Rewrite the CSV without the deleted event
        try (PrintWriter writer = new PrintWriter(new FileWriter(csvFile))) {
            writer.println(headersToString(headers));
            for (Event event : tempEvents.values()) {
                writer.println(eventToString(event));
            }
        }
    }

    @Override
    public Event getEventFromTitle(String eventName) {
        if (eventExists(eventName)) {
            return events.get(eventName);
        } else {
            System.out.println("Event does not exist");
            return null;
        }
    }

    @Override
    public ArrayList<Event> getEvents() {
        return new ArrayList<>(events.values());
    }
}
