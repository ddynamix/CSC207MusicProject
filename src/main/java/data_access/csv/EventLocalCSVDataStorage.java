package data_access.csv;

import app.interface_adapter_tools.UserSession;
import data_access.EventAlreadyExistsException;
import data_access.EventDataAccessInterface;
import data_access.EventDoesntExistException;
import data_access.UserDataAccessInterface;
import entity.event.Event;
import entity.user.ArtistUser;
import entity.user.VenueUser;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Event DAO
 */
public class EventLocalCSVDataStorage implements EventDataAccessInterface {
    private final File csvFile;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<String, Event> events = new HashMap<>();

    private final UserDataAccessInterface userDataAccessObject;

    /**
     * create event DAO
     * @param csvPath local file path
     * @param userDataAccessObject user DAO
     * @throws IOException input output exception for types and params
     */
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

    /**
     * add event to CSV
     * @param event to be added
     */
    private void appendEventToCsv(Event event) {
        try (FileWriter fw = new FileWriter(csvFile, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(eventToString(event));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * convert event to string
     * @param event to be converted
     * @return formatted string
     */
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

    /**
     * convert header into string
     * @param headers to be converted
     * @return formatted string
     */
    private String headersToString(Map<String, Integer> headers) {
        return String.join(",", headers.keySet());
    }

    /**
     * convert the string to an arraylist
     * @param tags string read from CSV
     * @return list of tags
     */
    private ArrayList<String> stringToArrayList(String tags) {
        return new ArrayList<>(Arrays.asList(tags.split(";")));
    }

    /**
     * convert array list into a string
     * @param tags array to be converted
     * @return formatted string
     */
    private String arrayListToString(ArrayList<String> tags) {
        return String.join(";", tags);
    }

    /**
     * Check if event is in CSV
     * @param eventName title to search for
     * @return boolean
     */
    @Override
    public boolean eventExists(String eventName) {
        return events.containsKey(eventName);
    }

    /**
     * add event to CSV
     * @param event to be added
     * @throws EventAlreadyExistsException exception for event already in CSV
     */
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

    /**
     * remove event from CSV
     * @param event to be removed
     * @throws EventDoesntExistException exception for event not in CSV
     */
    @Override
    public void deleteEvent(Event event) throws EventDoesntExistException {
        if (eventExists(event.getTitle())) {
            try {
                events.remove(event.getTitle());
                deleteEventFromCsv(event.getTitle());
                UserSession.getInstance().getLoggedInUser().removeEvent(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Event does not exist");
            throw new EventDoesntExistException();
        }
    }

    /**
     * change data in event
     * @param event current version
     * @param title new title
     * @param description new description
     * @param date new date
     * @param tags new tags
     * @param media new media
     * @throws EventDoesntExistException exception for event not in CSV
     */
    @Override
    public void updateEvent(Event event, String title, String description, String date, String tags, String media) throws EventDoesntExistException {
        if (!eventExists(event.getTitle())) {
            System.out.println("Event does not exist");
        } else {
            try {
                deleteEventFromCsv(event.getTitle());
                if (!(title == null || title.isEmpty())) {
                    event.setTitle(title);
                }
                if (!(description == null || description.isEmpty())) {
                    event.setDescription(description);
                }
                if (!(date == null || date.isEmpty())) {
                    event.setDateAndTime(LocalDateTime.parse(date, formatter));
                }
                if (!(tags == null || tags.isEmpty())) {
                    event.setTags(stringToArrayList(tags));
                }
                if (!(media == null || media.isEmpty())) {
                    event.setAttachedMedia(media);
                }
                appendEventToCsv(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * remove event from CSV
     * @param eventName title of event
     * @throws IOException input output exception for types and parameters
     */
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

    /**
     * find Event for title
     * @param eventName title to be searched for
     * @return found Event
     */
    @Override
    public Event getEventFromTitle(String eventName) {
        if (eventExists(eventName)) {
            return events.get(eventName);
        } else {
            System.out.println("Event does not exist");
            return null;
        }
    }

    /**
     * access all the events
     * @return arraylist of events
     */
    @Override
    public ArrayList<Event> getEvents() {
        return new ArrayList<>(events.values());
    }
}
