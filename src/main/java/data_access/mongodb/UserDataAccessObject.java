package data_access.mongodb;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;
import data_access.UserAlreadyExistsException;
import data_access.UserDataAccessInterface;
import entity.user.*;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;

public class UserDataAccessObject implements UserDataAccessInterface {

    //TODO: implement the following exceptions
    public static class UserNotFoundException extends Exception {
        public UserNotFoundException() {
            super("User not found in the database");
        }
    }

    public static class DuplicateUsernameException extends Exception {
        public DuplicateUsernameException() {
            super("Username already exists in the database");
        }
    }

    public static class PasswordMismatchException extends Exception {
        public PasswordMismatchException() {
            super("Passwords do not match");
        }
    }

    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private MongoCollection mongoCollection;
    private User user;

    public UserDataAccessObject() {
        String connectionString = "mongodb+srv://tasnimreza:dbtestpass@cluster0.vlnfmzu.mongodb.net/?appName=Cluster0";

        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();

        this.mongoClient = MongoClients.create("mongodb+srv://tasnimreza:dbtestpass@cluster0.vlnfmzu.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0");
        this.mongoDatabase = mongoClient.getDatabase("userDataBase");
    }

    public MongoCollection getCollectionByType(User user) {
        if (user instanceof ArtistUser) {
            return mongoDatabase.getCollection("artistUser");
        } else if (user instanceof AudienceUser) {
            return mongoDatabase.getCollection("audienceUser");
        } else if (user instanceof VenueUser) {
            return mongoDatabase.getCollection("venueUser");
        }
        return null;
    }

    @Override
    public boolean userExistsInDatabase(String username) {
        Bson filter = eq("username", username);
        return mongoCollection.find(filter).iterator().hasNext();
    }

    @Override
    public void updateUsername(User user, String newUsername) throws UserNotFoundException {
        try (MongoClient mongoClient = MongoClients.create(System.getProperty("mongodb.uri"))) {
            MongoDatabase mongoDatabase = mongoClient.getDatabase("userDataBase");
            MongoCollection<Document> mongoCollection = getCollectionByType(user);
            Document query = new Document("username", newUsername);
            if (userExistsInDatabase(user.getUsername())) {
                Document update = new Document("$set", new Document("username", newUsername));
                mongoCollection.updateOne(query, update);
                System.out.println("Your username has been updated successfully.");
            } else {
                throw new UserNotFoundException();
            }
        }
    }

    @Override
    public void updatePassword(User user, String newPassword, String confirmPassword) throws PasswordMismatchException, UserNotFoundException {
        Document query = new Document("password", newPassword);
        try {
            Document update = new Document("$set", new Document("password", newPassword));
            this.mongoCollection = getCollectionByType(user);
            mongoCollection.updateOne(query, update);
            System.out.println("Your password has been updated successfully.");
            if (!newPassword.equals(confirmPassword)) {
                throw new PasswordMismatchException();
            } else if (!userExistsInDatabase(user.getUsername())) {
                throw new UserNotFoundException();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void updateEmail(User user, String newEmail) throws UserNotFoundException {
        try (MongoClient mongoClient = MongoClients.create(System.getProperty("mongodb.uri"))) {
            MongoDatabase mongoDatabase = mongoClient.getDatabase("userDataBase");
            this.mongoCollection = getCollectionByType(user);
            Document query = new Document("email", newEmail);
            if (userExistsInDatabase(user.getEmail())) {
                Document update = new Document("$set", new Document("username", newEmail));
                mongoCollection.updateOne(query, update);
                System.out.println("Your email has been updated successfully.");
            } else {
                throw new UserNotFoundException();
            }
        }
    }

    @Override
    public void create(User user) throws DuplicateUsernameException {
        if (userExistsInDatabase(user.getUsername())) {
            throw new DuplicateUsernameException();
        } else {
            Document document = new Document("username", user.getUsername())
                    .append("password", user.getPassword())
                    .append("email", user.getEmail())
                    .append("followers", user.getFollowers())
                    .append("following", user.getFollowing());
            InsertOneResult insertResult = mongoCollection.insertOne(document);
            user.setId(insertResult.getInsertedId().toString());
        }
    }

    @Override
    //implementing this as a delete user functionality.
    public void delete(User user) throws UserNotFoundException {
        if (userExistsInDatabase(user.getUsername())) {
            Bson filter = Filters.eq("username", user.getUsername());
            mongoCollection.deleteOne(filter);
        } else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public User getUserFromUsername(String username) {
        return null; // TODO: Implement
    }

    @Override
    public ArrayList<ArtistUser> getArtistUsers() {
        return null;
    }

    @Override
    public ArrayList<VenueUser> getVenueUsers() {
        return null;
    }

    @Override
    public ArrayList<AudienceUser> getAudienceUsers() {
        return null;
    }

    @Override
    public boolean passwordMatches(String username, String password) {
       return false;
    }

    public void appendToFollowers(User newFollower){

    }
}
