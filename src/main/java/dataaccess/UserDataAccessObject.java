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

    public static class UserNotFoundException extends Exception{

    }

    private MongoClient mongoClient;
    private UserFactory userFactory;
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
    public void updateUsername(User user, String newUsername) {
        try (MongoClient mongoClient = MongoClients.create(System.getProperty("mongodb.uri"))) {
            MongoDatabase mongoDatabase = mongoClient.getDatabase("userDataBase");
            MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("audienceUser");
            //assumption that only audience will change usernames
            Document query = new Document("username",newUsername);
            if (userExistsInDatabase(user.getUsername())) {
                Document update = new Document("$set",new Document("username", newUsername));
                mongoCollection.updateOne(query,update);
                System.out.println("Your username has been updated successfully.");;
            } else {
                System.out.println("User does not exist in the database");
            }
        };

    }

    @Override
    public void updatePassword(User user, String newPassword, String confirmPassword) {
        if (userExistsInDatabase(user.getUsername()) && newPassword.equals(confirmPassword)) {

        } else {
            System.out.println("User does not exist in the database");
        }
    }

    @Override
    public void updateEmail(User user, String newEmail) {
        try (MongoClient mongoClient = MongoClients.create(System.getProperty("mongodb.uri"))) {
            MongoDatabase mongoDatabase = mongoClient.getDatabase("userDataBase");
            //currently setting up with audience user for the time being
            MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("audienceUser");
            Document query = new Document("email", newEmail);
            if (userExistsInDatabase(user.getEmail())) {
                Document update = new Document("$set",new Document("username", newEmail));
                mongoCollection.updateOne(query,update);
                System.out.println("Your email has been updated successfully.");;
            } else {
                System.out.println("Your email does not exist in the database.");
            }

    }
    }



    @Override
    public void create(String username, String password, String email, String firstName, String lastName) {
        if (userExistsInDatabase(username)) {
//            throw new DuplicateUsernameException();
        } else {
            user = userFactory.createUser(username, password, email, firstName, lastName);
            Document document = new Document("username", user.getUsername())
                    .append("password", user.getPassword())
                    .append("email", user.getEmail())
                    .append("firstName", user.getFirstName())
                    .append("lastName", user.getLastName());
            InsertOneResult insertResult = mongoCollection.insertOne(document);
            user.setId(insertResult.getInsertedId().toString());
        }
    }

    @Override
    //implementing this as a delete user functionality.
    public void delete(User user) {

        if (userExistsInDatabase(user.getUsername())) {
            Bson filter = Filters.eq("username", user.getUsername());
            mongoCollection.deleteOne(filter);
        }

    //yo did i accidentally delete smth here ????? it looks off - tas
    @Override
    public String[] getUserData(User user) {
        return new String[0];
    }

    @Override
    public void Throwable(UserNotFoundException userNotFoundException){
        System.out.println("User was not found in the database");
    }
}
