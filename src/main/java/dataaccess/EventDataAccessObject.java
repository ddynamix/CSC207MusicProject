package dataaccess;

import com.mongodb.client.*;
import entity.event.Event;
import org.bson.conversions.Bson;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;
import entity.user.*;
import org.bson.Document;
import org.bson.conversions.Bson;
import static com.mongodb.client.model.Filters.eq;
import java.util.Map;

public class EventDataAccessObject implements EventDataAccessInterface {

    public static class EventAlreadyExistsException extends Exception {
        public EventAlreadyExistsException() {
            super("Event already exists in the database");
        }
    }

    public static class EventDoesntExistException extends Exception {
        public EventDoesntExistException() {
            super("Event does not exist in the database");
        }
    }

    public MongoClient mongoClient;
    public MongoDatabase mongoDatabase;
    public MongoCollection mongoCollection;

    public EventDataAccessObject() {
        String connectionString = "mongodb+srv://tasnimreza:dbtestpass@cluster0.vlnfmzu.mongodb.net/?appName=Cluster0";

        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();

        this.mongoClient = MongoClients.create("mongodb+srv://tasnimreza:dbtestpass@cluster0.vlnfmzu.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0");
        this.mongoDatabase = mongoClient.getDatabase("contentDataBase");
        this.mongoCollection = mongoDatabase.getCollection("eventContent");
    }

    @Override
    public boolean eventExists(String eventName) {
        Bson filter = eq("eventName", eventName);
        return mongoCollection.find(filter).iterator().hasNext();
    }

    @Override
    public void createEvent(Event event) throws EventAlreadyExistsException {
        if (eventExists(event.getTitle())) {
            throw new EventAlreadyExistsException();
        } else{
            Document document = new Document("eventName", event.getTitle())
                    .append("eventDescription", event.getDescription())
                    .append("eventDate", event.getDateAndTime())
                    .append("eventOrganizer", event.getTags())
                    .append("eventAttendees", event.getPostDate())
                    .append("eventVenue", event.getVenue())
                    .append("eventMedia", event.getAttachedMedia());
            InsertOneResult insertResult = mongoCollection.insertOne(document);
            event.setId(insertResult.getInsertedId().toString());
            mongoCollection.insertOne(document);
        }
    }

    @Override
    public void deleteEvent(Event event) throws EventDoesntExistException {
        if (!eventExists(event.getTitle())) {
            throw new EventDoesntExistException();
        } else {
            Bson filter = eq("eventName", event.getTitle());
            mongoCollection.deleteOne(filter);
        }
    }

    @Override
    public Event getEventFromTitle(String eventName) throws EventDoesntExistException {
        if (!eventExists(eventName)) {
            throw new EventDoesntExistException();
        } else {
            Bson filter = eq("eventName", eventName);
            MongoCursor iterator = mongoCollection.find(filter).iterator();
            //todo convert iterator to event
            return event;;
        }
    }

    }
    @Override
    public Map<String, Event> getEvents() { //todo implement. return all events

    }
}