package dataaccess;

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
import entity.user.*;
import org.bson.Document;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Filters.eq;

public abstract class UserDataAccessObject implements UserDataAccessInterface {

    //TODO: implement the following exceptions
    public static class UserNotFoundException extends Exception{
        public UserNotFoundException() {
            super("User not found in the database");
        }
    }

    public static class DuplicateUsernameException extends Exception{
        public DuplicateUsernameException() {
            super("Username already exists in the database");
        }
    }

    public static class PasswordMismatchException extends Exception{
        public PasswordMismatchException() {
            super("Passwords do not match");
        }
    }

    private MongoClient mongoClient;
    //todo: we just instantiate users directly, so we don't need a factory. check implementation
//    private UserFactory userFactory;
    private MongoDatabase mongoDatabase;
    private MongoCollection mongoCollection;
    private User user;

    public UserDataAccessObject(User user) {
        this.user= user;
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

        if (user instanceof ArtistUser) {
            this.mongoCollection = mongoDatabase.getCollection("artistUser");
        } else if (user instanceof AudienceUser) {
            this.mongoCollection = mongoDatabase.getCollection("audienceUser");
        } else if (user instanceof VenueUser) {
            this.mongoCollection = mongoDatabase.getCollection("venueUser");
        }
    }

    @Override
    public boolean userExistsInDatabase(String username){
        Bson filter = eq("username",username);
        return mongoCollection.find(filter).iterator().hasNext();
    }

    @Override
    public void updateUsername(User user, String newUsername)  throws UserNotFoundException{
        try (MongoClient mongoClient = MongoClients.create(System.getProperty("mongodb.uri"))) {
            MongoDatabase mongoDatabase = mongoClient.getDatabase("userDataBase");
            MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("audienceUser");
            //assumption that only audience will change usernames
            Document query = new Document("username",newUsername);
            if (userExistsInDatabase(user.getUsername())) {
                Document update = new Document("$set",new Document("username", newUsername));
                mongoCollection.updateOne(query,update);
                System.out.println("Your username has been updated successfully.");
            } else {
                throw new UserNotFoundException();
            }
        }
    }

    @Override
    public void updatePassword(User user, String newPassword, String confirmPassword) throws PasswordMismatchException, UserNotFoundException {
        Document query = new Document("password",newPassword);
        try {
            Document update = new Document("$set",new Document("password", newPassword));
            mongoCollection.updateOne(query,update);
            System.out.println("Your password has been updated successfully.");
            if (!newPassword.equals(confirmPassword)) {
                throw new PasswordMismatchException();
            } else if (!userExistsInDatabase(user.getUsername())) {
                throw new UserNotFoundException();
            }
        }
        catch (PasswordMismatchException e) {
            //how do catch blocks work lol
        }
        catch (UserNotFoundException e) {
        }
    }


    @Override
    public void updateEmail(User user, String newEmail) throws UserNotFoundException{
        try (MongoClient mongoClient = MongoClients.create(System.getProperty("mongodb.uri"))) {
            MongoDatabase mongoDatabase = mongoClient.getDatabase("userDataBase");
            //currently setting up with audience user for the time being
            MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("audienceUser");
            Document query = new Document("email", newEmail);
            if (userExistsInDatabase(user.getEmail())) {
                Document update = new Document("$set",new Document("username", newEmail));
                mongoCollection.updateOne(query,update);
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
        try {
            if (userExistsInDatabase(user.getUsername())) {
                Bson filter = Filters.eq("username", user.getUsername());
                mongoCollection.deleteOne(filter);
            }
        } catch (Exception e) {
            throw new UserNotFoundException();
        }
    }

/*
    //yo did i accidentally delete smth here ????? it looks off - tas
    @Override
    public String[] getUserData(User user){
            return new String[0];
        }

    public void Throwable;(UserNotFoundException userNotFoundException){
        System.out.println("User was not found in the database");
    }
*/
    }
