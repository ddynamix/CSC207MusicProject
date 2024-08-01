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

import java.util.HashMap;
import java.util.Map;

@Override
    public Map<String, Event> getEvents() { //todo implement. return all events
        Event event = new Event();
        Map<String, Event> events = new Map<>("place", event);
        return events;
    }
}
